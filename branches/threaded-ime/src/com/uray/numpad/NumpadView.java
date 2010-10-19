package com.uray.numpad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumpadView extends View 
                        implements View.OnClickListener                         
{
	public interface OnKeyboardActionListener 
	{        
        void onPress(int primaryCode);
        void onRelease(int primaryCode);
        void onKey(int primaryCode, int[] keyCodes);
        void onText(CharSequence text);
    }
	
    private static final int NOT_A_KEY = -1;    
    private Keyboard mKeyboard;
    private int mKeyTextColor = 0xFF000000;
    private float mBackgroundDimAmount = 0.5f;
    private boolean mMiniKeyboardOnScreen;
    private Map<Key,View> mMiniKeyboardCache;
    private Key[] mKeys;
    private OnKeyboardActionListener mKeyboardActionListener;
    private static final int MSG_REPEAT = 3;
    private static final int MSG_LONGPRESS = 4;
    private static final int DEBOUNCE_TIME = 70;
    private int mVerticalCorrection = 0;
    private int mLastX;
    private int mLastY;
    private Paint mPaint;
    private Rect mPadding;
    private int mPaddingLeft = 0;
    private int mPaddingRight = 0;
    private int mPaddingTop = 0;
    private int mPaddingBottom = 0;
    private long mDownTime;
    private long mLastMoveTime;
    private int mLastKey;
    private int mLastCodeX;
    private int mLastCodeY;
    private int mCurrentKey = NOT_A_KEY;
    private long mLastKeyTime;
    private long mCurrentKeyTime;
    private int[] mKeyIndices = new int[12];
    private int mRepeatKeyIndex = NOT_A_KEY;
    private boolean mAbortKey;
    private Key mInvalidatedKey;
    private Rect mClipRegion = new Rect(0, 0, 0, 0);
    private StateListDrawable mKeyBackground;
    private static final int REPEAT_INTERVAL = 50; // ~20 keys per second
    private static final int REPEAT_START_DELAY = 400;
    private static final int LONGPRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private static int MAX_NEARBY_KEYS = 12;
    private int[] mDistances = new int[MAX_NEARBY_KEYS];
    private long mLastTapTime;
    private boolean mDrawPending;
    private Rect mDirtyRect = new Rect();
    private Bitmap mBuffer;
    private boolean mKeyboardChanged;
    private Canvas mCanvas;
    
    private int[] keyStateDefault = {android.R.attr.state_enabled,
            android.R.attr.state_active,
            android.R.attr.state_focused,
            android.R.attr.state_long_pressable,
            android.R.attr.state_single,
            android.R.attr.state_window_focused,
            android.R.attr.state_empty};
    private int[] keyStatePressed = {android.R.attr.state_pressed};
    private int[] keyStateCheckable = {android.R.attr.state_checkable};
    private int[] keyStateChecked = {android.R.attr.state_checked};
    
    Handler mHandler = new Handler() 
    {
        @Override public void handleMessage(Message msg) 
        {
            switch (msg.what) 
            {
                case MSG_REPEAT:
                    if (repeatKey()) {
                        Message repeat = Message.obtain(this, MSG_REPEAT);
                        sendMessageDelayed(repeat, REPEAT_INTERVAL);                        
                    }
                    break;
            }
        }
    };
	
    public NumpadView(Context context, AttributeSet attrs) 
    {
        this(context, attrs, 0);
    }
    
    public NumpadView(Context context, AttributeSet attrs, int defStyle) 
    {
        super(context, attrs, defStyle);
        mKeyBackground = new StateListDrawable();
        mKeyBackground.addState(keyStateDefault, getResources().getDrawable(R.drawable.keybackground));
        mKeyBackground.addState(keyStatePressed, getResources().getDrawable(R.drawable.keybgpress));
        mKeyBackground.addState(keyStateCheckable, getResources().getDrawable(R.drawable.keybgcheckable));
        mKeyBackground.addState(keyStateChecked, getResources().getDrawable(R.drawable.keybgcheck));

        int keyTextSize = 0;
        
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(keyTextSize);
        mPaint.setTextAlign(Align.CENTER);
        mPaint.setAlpha(255);

        mPadding = new Rect(0, 0, 0, 0);
        mMiniKeyboardCache = new HashMap<Key,View>();
        mKeyBackground.getPadding(mPadding);
    }

    public void setOnKeyboardActionListener(OnKeyboardActionListener listener) 
    {
        mKeyboardActionListener = listener;
    }
    
    protected OnKeyboardActionListener getOnKeyboardActionListener() 
    {
        return mKeyboardActionListener;
    }

    public void setKeyboard(Keyboard keyboard) 
    {
        removeMessages();
        mKeyboard = keyboard;
        List<Key> keys = mKeyboard.getKeys();
        mKeys = keys.toArray(new Key[keys.size()]);
        requestLayout();
        mKeyboardChanged = true;
        invalidateAllKeys();
        mMiniKeyboardCache.clear(); // Not really necessary to do every time, but will free up views
        // Switching to a different keyboard should abort any pending keys so that the key up
        // doesn't get delivered to the old or new keyboard
        mAbortKey = true; // Until the next ACTION_DOWN
    }

    public Keyboard getKeyboard() 
    {
        return mKeyboard;
    }

    public void onClick(View v) 
    {
    }

    @Override public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
    {
        if (mKeyboard == null) 
        {
            setMeasuredDimension(mPaddingLeft + mPaddingRight, mPaddingTop + mPaddingBottom);
        } 
        else 
        {
            int width = mKeyboard.getMinWidth() + mPaddingLeft + mPaddingRight;
            if (MeasureSpec.getSize(widthMeasureSpec) < width + 10) 
            {
                width = MeasureSpec.getSize(widthMeasureSpec);
            }
            setMeasuredDimension(width, mKeyboard.getHeight() + mPaddingTop + mPaddingBottom);
        }
    }

    @Override public void onSizeChanged(int w, int h, int oldw, int oldh) 
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mBuffer = null;
    }

    @Override public void onDraw(Canvas canvas) 
    {
        super.onDraw(canvas);
        if (mDrawPending || mBuffer == null || mKeyboardChanged) 
        {
            onBufferDraw();
        }
        canvas.drawBitmap(mBuffer, 0, 0, null);
    }

    private void onBufferDraw() 
    {
        if (mBuffer == null || mKeyboardChanged) 
        {
            if (mBuffer == null || mKeyboardChanged &&
                    (mBuffer.getWidth() != getWidth() || mBuffer.getHeight() != getHeight())) 
            {
                final int width = Math.max(1, getWidth());
                final int height = Math.max(1, getHeight());
                mBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mBuffer);
            }
            invalidateAllKeys();
            mKeyboardChanged = false;
        }
        final Canvas canvas = mCanvas;
        canvas.clipRect(mDirtyRect, Op.REPLACE);
        
        if (mKeyboard == null) return;
        
        final Paint paint = mPaint;
        final Drawable keyBackground = mKeyBackground;
        final Rect clipRegion = mClipRegion;
        final Rect padding = mPadding;
        final int kbdPaddingLeft = mPaddingLeft;
        final int kbdPaddingTop = mPaddingTop;
        final Key[] keys = mKeys;
        final Key invalidKey = mInvalidatedKey;

        paint.setColor(mKeyTextColor);
        boolean drawSingleKey = false;
        if (invalidKey != null && canvas.getClipBounds(clipRegion)) 
        {
          // Is clipRegion completely contained within the invalidated key?
          if (invalidKey.x + kbdPaddingLeft - 1 <= clipRegion.left &&
              invalidKey.y + kbdPaddingTop - 1 <= clipRegion.top &&
              invalidKey.x + invalidKey.width + kbdPaddingLeft + 1 >= clipRegion.right &&
              invalidKey.y + invalidKey.height + kbdPaddingTop + 1 >= clipRegion.bottom) 
          {
              drawSingleKey = true;
          }
        }
        canvas.drawColor(0x00000000, PorterDuff.Mode.CLEAR);
        final int keyCount = keys.length;
        for (int i = 0; i < keyCount; i++) 
        {
            final Key key = keys[i];
            if (drawSingleKey && invalidKey != key) 
            {
                continue;
            }
            if(key.sticky)
            {
            	keyBackground.setState(this.keyStateCheckable);
            }
            else 
            {
            	keyBackground.setState(this.keyStateDefault);
            }
            
            final Rect bounds = keyBackground.getBounds();
            if (key.width != bounds.right || key.height != bounds.bottom) 
            {
                keyBackground.setBounds(0, 0, key.width, key.height);
            }
            canvas.translate(key.x + kbdPaddingLeft, key.y + kbdPaddingTop);
            keyBackground.draw(canvas);
            
            final int drawableX = (key.width - padding.left - padding.right 
                                  - key.icon.getIntrinsicWidth()) / 2 + padding.left;
            final int drawableY = (key.height - padding.top - padding.bottom 
                                  - key.icon.getIntrinsicHeight()) / 2 + padding.top;
            canvas.translate(drawableX, drawableY);
            key.icon.setBounds(0, 0, 
                    key.icon.getIntrinsicWidth(), key.icon.getIntrinsicHeight());
            key.icon.draw(canvas);
            canvas.translate(-drawableX, -drawableY);
            canvas.translate(-key.x - kbdPaddingLeft, -key.y - kbdPaddingTop);
        }
        mInvalidatedKey = null;
        // Overlay a dark rectangle to dim the keyboard
        if (mMiniKeyboardOnScreen) 
        {
            paint.setColor((int) (mBackgroundDimAmount * 0xFF) << 24);
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        }
        
        mDrawPending = false;
        mDirtyRect.setEmpty();
    }

    private int getKeyIndices(int x, int y, int[] allKeys) 
    {
        final Key[] keys = mKeys;
        int primaryIndex = NOT_A_KEY;
        int closestKey = NOT_A_KEY;
        java.util.Arrays.fill(mDistances, Integer.MAX_VALUE);
        int [] nearestKeyIndices = mKeyboard.getNearestKeys(x, y);
        final int keyCount = nearestKeyIndices.length;
        for (int i = 0; i < keyCount; i++) 
        {
            final Key key = keys[nearestKeyIndices[i]];
            boolean isInside = key.isInside(x,y);
            if (isInside) 
            {
                primaryIndex = nearestKeyIndices[i];
            }
        }
        if (primaryIndex == NOT_A_KEY) {
            primaryIndex = closestKey;
        }
        return primaryIndex;
    }

    private void detectAndSendKey(int index, int x, int y, long eventTime) 
    {
        if (index != NOT_A_KEY && index < mKeys.length) 
        {
            final Key key = mKeys[index];
            if (key.text != null) 
            {
                mKeyboardActionListener.onText(key.text);
                mKeyboardActionListener.onRelease(NOT_A_KEY);
            } 
            else 
            {
                int code = key.codes[0];
                //TextEntryState.keyPressedAt(key, x, y);
                int[] codes = new int[MAX_NEARBY_KEYS];
                Arrays.fill(codes, NOT_A_KEY);
                getKeyIndices(x, y, codes);
                mKeyboardActionListener.onKey(code, codes);
                mKeyboardActionListener.onRelease(code);
            }
            mLastTapTime = eventTime;
        }
    }

    public void invalidateAllKeys() 
    {
        mDirtyRect.union(0, 0, getWidth(), getHeight());
        mDrawPending = true;
        invalidate();
    }

    public void invalidateKey(int keyIndex) 
    {
        if (mKeys == null) return;
        if (keyIndex < 0 || keyIndex >= mKeys.length) 
        {
            return;
        }
        final Key key = mKeys[keyIndex];
        mInvalidatedKey = key;
        mDirtyRect.union(key.x + mPaddingLeft, key.y + mPaddingTop, 
                key.x + key.width + mPaddingLeft, key.y + key.height + mPaddingTop);
        onBufferDraw();
        invalidate(key.x + mPaddingLeft, key.y + mPaddingTop, 
                key.x + key.width + mPaddingLeft, key.y + key.height + mPaddingTop);
    }

    @Override public boolean onTouchEvent(MotionEvent me) 
    {
        int touchX = (int) me.getX() - mPaddingLeft;
        int touchY = (int) me.getY() + mVerticalCorrection - mPaddingTop;
        final int action = me.getAction();
        final long eventTime = me.getEventTime();

        int keyIndex = getKeyIndices(touchX, touchY, null);

        // Ignore all motion events until a DOWN.
        if (mAbortKey
                && action != MotionEvent.ACTION_DOWN && action != MotionEvent.ACTION_CANCEL) 
        {
            return true;
        }
        
        // Needs to be called after the gesture detector gets a turn, as it may have
        // displayed the mini keyboard
        if (mMiniKeyboardOnScreen && action != MotionEvent.ACTION_CANCEL) 
        {
            return true;
        }

        switch (action) 
        {
            case MotionEvent.ACTION_DOWN:
                mAbortKey = false;
                mLastCodeX = touchX;
                mLastCodeY = touchY;
                mLastKeyTime = 0;
                mCurrentKeyTime = 0;
                mLastKey = NOT_A_KEY;
                mCurrentKey = keyIndex;
                mDownTime = me.getEventTime();
                mLastMoveTime = mDownTime;
                mKeyboardActionListener.onPress(keyIndex != NOT_A_KEY ? 
                        mKeys[keyIndex].codes[0] : 0);
                if (mCurrentKey >= 0 && mKeys[mCurrentKey].repeatable)
                {
                    mRepeatKeyIndex = mCurrentKey;
                    Message msg = mHandler.obtainMessage(MSG_REPEAT);
                    mHandler.sendMessageDelayed(msg, REPEAT_START_DELAY);
                    repeatKey();
                    // Delivering the key could have caused an abort
                    if (mAbortKey) 
                    {
                        mRepeatKeyIndex = NOT_A_KEY;
                        break;
                    }
                }
                if (mCurrentKey != NOT_A_KEY) 
                {
                    Message msg = mHandler.obtainMessage(MSG_LONGPRESS, me);
                    mHandler.sendMessageDelayed(msg, LONGPRESS_TIMEOUT);
                }
                //showPreview(keyIndex);
                break;

            case MotionEvent.ACTION_MOVE:
                boolean continueLongPress = false;
                if (keyIndex != NOT_A_KEY) 
                {
                    if (mCurrentKey == NOT_A_KEY) 
                    {
                        mCurrentKey = keyIndex;
                        mCurrentKeyTime = eventTime - mDownTime;
                    } 
                    else 
                    {
                        if (keyIndex == mCurrentKey) 
                        {
                            mCurrentKeyTime += eventTime - mLastMoveTime;
                            continueLongPress = true;
                        } 
                        else if (mRepeatKeyIndex == NOT_A_KEY) 
                        {
                            mLastKey = mCurrentKey;
                            mLastCodeX = mLastX;
                            mLastCodeY = mLastY;
                            mLastKeyTime =
                                    mCurrentKeyTime + eventTime - mLastMoveTime;
                            mCurrentKey = keyIndex;
                            mCurrentKeyTime = 0;
                        }
                    }
                }
                if (!continueLongPress) 
                {
                    // Cancel old longpress
                    mHandler.removeMessages(MSG_LONGPRESS);
                    // Start new longpress if key has changed
                    if (keyIndex != NOT_A_KEY) 
                    {
                        Message msg = mHandler.obtainMessage(MSG_LONGPRESS, me);
                        mHandler.sendMessageDelayed(msg, LONGPRESS_TIMEOUT);
                    }
                }
                mLastMoveTime = eventTime;
                break;

            case MotionEvent.ACTION_UP:
                removeMessages();
                if (keyIndex == mCurrentKey) 
                {
                    mCurrentKeyTime += eventTime - mLastMoveTime;
                } 
                else 
                {
                    //resetMultiTap();
                    mLastKey = mCurrentKey;
                    mLastKeyTime = mCurrentKeyTime + eventTime - mLastMoveTime;
                    mCurrentKey = keyIndex;
                    mCurrentKeyTime = 0;
                }
                if (mCurrentKeyTime < mLastKeyTime && mCurrentKeyTime < DEBOUNCE_TIME
                        && mLastKey != NOT_A_KEY) 
                {
                    mCurrentKey = mLastKey;
                    touchX = mLastCodeX;
                    touchY = mLastCodeY;
                }
                Arrays.fill(mKeyIndices, NOT_A_KEY);
                // If we're not on a repeating key (which sends on a DOWN event)
                if (mRepeatKeyIndex == NOT_A_KEY && !mMiniKeyboardOnScreen && !mAbortKey) 
                {
                    detectAndSendKey(mCurrentKey, touchX, touchY, eventTime);
                }
                invalidateKey(keyIndex);
                mRepeatKeyIndex = NOT_A_KEY;
                break;
            case MotionEvent.ACTION_CANCEL:
                removeMessages();
                mAbortKey = true;
                invalidateKey(mCurrentKey);
                break;
        }
        mLastX = touchX;
        mLastY = touchY;
        return true;
    }

    private boolean repeatKey() 
    {
        Key key = mKeys[mRepeatKeyIndex];
        detectAndSendKey(mCurrentKey, key.x, key.y, mLastTapTime);
        return true;
    }

    public void closing() 
    {
        removeMessages();
        
        mBuffer = null;
        mCanvas = null;
        mMiniKeyboardCache.clear();
    }

    private void removeMessages() 
    {
        mHandler.removeMessages(MSG_REPEAT);
        mHandler.removeMessages(MSG_LONGPRESS);
    }

    @Override public void onDetachedFromWindow() 
    {
        super.onDetachedFromWindow();
        closing();
    }
}

