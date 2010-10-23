package com.uray.numpad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class NumpadView extends ViewGroup
{
	public TouchListener touchListener;
	private NumpadLayout layout;	
	private boolean isCanvasNeedRedraw;
	private Bitmap buffer;
	private Canvas bufferCanvas;
	public NumpadRenderer renderer;
	
	public interface TouchListener
	{
		void onKeyDown(NumpadKey key);
		void onKeyUp(NumpadKey key);
	}
	
	public NumpadView(Context context) 
	{
		super(context);
		this.setClickable(true);
      	this.setFocusable(true);
      	this.setFocusableInTouchMode(true);
      	this.setHapticFeedbackEnabled(false);
      	this.setVisibility(ViewGroup.VISIBLE);
      	this.setWillNotDraw(false);
	}  
	
	void setKeyLayout(NumpadLayout layout)
	{
		this.layout = layout;
		this.isCanvasNeedRedraw = true;		
		this.invalidate();
		
		for(NumpadKey key : layout.keys)
		{
			this.addView(key,new ViewGroup.LayoutParams(key.getWidth(), key.getHeight()));	
			this.bringChildToFront(key);
			key.invalidate();
		}
	}
	
	@Override public void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		this.layout.setConstraint(w,h);
	}
	
	@Override public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
		int width = this.layout.width;
		if (MeasureSpec.getSize(widthMeasureSpec) < width + 10) 
		{
			width = MeasureSpec.getSize(widthMeasureSpec);
		}
		setMeasuredDimension(width, this.layout.height);
	}
	
	@Override public void onDraw(Canvas canvas) 
	{
		if(this.isCanvasNeedRedraw)
		{
			if( (this.buffer == null) || 
				(canvas.getWidth() != this.buffer.getWidth() ) ||
				(canvas.getHeight()!= this.buffer.getHeight()) )
			{
				this.buffer = Bitmap.createBitmap(this.getWidth(),this.getHeight(),Bitmap.Config.ARGB_8888);
				this.bufferCanvas = new Canvas(this.buffer);
			}
			this.renderer.renderLayout(this.bufferCanvas, this.layout);
			this.isCanvasNeedRedraw = false;
		}
		canvas.drawBitmap(this.buffer, 0, 0, null);
	}
	
	@Override public void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		this.layout.layoutKeys(left, top, right, bottom);
	}
	
	@Override public boolean onTouchEvent(MotionEvent me) 
	{
		NumpadKey key = this.layout.getKey((int)me.getX(), (int)me.getY());		
		if(key != null)
		{
			if(me.getAction() == MotionEvent.ACTION_UP)
			{
				this.touchListener.onKeyUp(key);				
			}
			else if(me.getAction() == MotionEvent.ACTION_DOWN)
			{
				this.touchListener.onKeyDown(key);				
			}
		}
		return true;
	}

	public void setRenderer(NumpadRenderer renderer)
	{
		this.renderer = renderer;
		this.isCanvasNeedRedraw = true;		
		this.invalidate();
	}	
}

