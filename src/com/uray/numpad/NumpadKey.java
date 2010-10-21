package com.uray.numpad;

import java.util.ArrayList;
import com.uray.numpad.glyph.NumpadGlyph;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

public class NumpadKey extends View
{
	public class KeyValue
	{
		public String label;
		public String value;
		public int keyCode;
		public final static int keyCodeChars = -1;
		public final static int keyCodeDel = -2;
		public final static int keyCodeAlt = -3;
		public final static int keyCodeShift = -4;
	}

	public class KeyMap
	{
		public KeyValue longPressMap;
		public KeyValue[] defaultTapMap;
		public KeyValue[] shiftTapMap;
		public KeyValue[] altTapMap;
	}

	
	private float posX;
	private float posY;
	private float keyWidth;
	private float keyHeight;
	private Rect padding;
	private int state;
	private int id;
	private NumpadRenderer renderer;
	private NumpadLayout keyLayout;
	private boolean isCanvasNeedRedraw = true;
	private Bitmap buffer;
	private Canvas bufferCanvas;
	public ArrayList<NumpadGlyph> glyph = new ArrayList<NumpadGlyph>();
	
	public NumpadKey(Context context,NumpadRenderer renderer,NumpadLayout keyLayout,
			        float x,float y,float w, float h)
	{
		super(context);
		this.setVisibility(View.INVISIBLE);
		this.setClickable(false);
      	this.setFocusable(false);
      	this.setFocusableInTouchMode(false);
      	this.setHapticFeedbackEnabled(false);
      	this.setWillNotDraw(false);
      	
		this.renderer = renderer;
		this.keyLayout = keyLayout;
		this.setKeyPosX(x);
		this.setKeyPosY(y);
		this.setKeyWidth(w);
		this.setKeyHeight(h);
		
		int myWidth  = (int)(this.keyWidth*(float)this.keyLayout.getMinWidth());
		int myHeight = (int)(this.keyHeight*(float)this.keyLayout.getMinHeight());
		this.setMinimumHeight(myHeight);
		this.setMinimumWidth(myWidth);
		
		this.buffer = Bitmap.createBitmap(myWidth,myHeight, Bitmap.Config.ARGB_8888);
		this.bufferCanvas = new Canvas(this.buffer);
	}
	
	@Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int width = (int)(this.keyWidth*(float)this.keyLayout.getWidth());
		if (MeasureSpec.getSize(widthMeasureSpec) < width + 10) 
		{
			width = MeasureSpec.getSize(widthMeasureSpec);
		}
		this.setMeasuredDimension(width, (int)(this.keyHeight*(float)this.keyLayout.getHeight()));
	}
	
	@Override public void onDraw(Canvas canvas)
	{
		if(this.isCanvasNeedRedraw)
		{
			this.renderer.renderKey(this.bufferCanvas, this);
			this.isCanvasNeedRedraw = false;
		}
		canvas.drawBitmap(this.buffer, 0, 0, null);
	}

	public void setKeyId(int id)
	{
		this.id = id;
	}

	public int getKeyId()
	{
		return id;
	}

	public void setKeyState(int state)
	{
		this.state = state;
	}

	public int getKeyState()
	{
		return state;
	}

	public void setKeyHeight(float height)
	{
		this.keyHeight = height;
	}

	public float getKeyHeight()
	{
		return this.keyHeight;
	}

	public void setKeyWidth(float width)
	{
		this.keyWidth = width;
	}

	public float getKeyWidth()
	{
		return this.keyWidth;
	}

	public void setKeyPosY(float posY)
	{
		this.posY = posY;
	}

	public float getKeyPosY()
	{
		return posY;
	}

	public void setKeyPosX(float posX)
	{
		this.posX = posX;
	}

	public float getKeyPosX()
	{
		return posX;
	}

	public void setKeyPadding(Rect padding)
	{
		this.padding = padding;
	}

	public Rect getKeyPadding()
	{
		return padding;
	}


}
