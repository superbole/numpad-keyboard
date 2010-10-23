package com.uray.numpad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

public class NumpadKey extends View
{
	private NumpadRenderer renderer;
	private boolean isCanvasNeedRedraw = true;
	private Bitmap buffer;
	private Canvas bufferCanvas;
	
	public final static int edgeLeft = 1;
	public final static int edgeTop = 2;
	public final static int edgeBottom = 4;
	public final static int edgeRight = 8;
	public final int id;
	
	public float posX;
	public float posY;
	public float keyWidth;
	public float keyHeight;
	public int edgeFlag = 0;
	public boolean isToggleKey = false;
	public NumpadKeymap keymap;		
	public NumpadLayout keyLayout;
	//public ArrayList<NumpadGlyph> glyph = new ArrayList<NumpadGlyph>();
	
	public NumpadKey(int id,Context context,NumpadRenderer renderer,NumpadLayout keyLayout,
			        float x,float y,float w, float h)
	{
		super(context);
		this.id = id;
		this.setVisibility(View.INVISIBLE);
		this.setClickable(false);
      	this.setFocusable(false);
      	this.setFocusableInTouchMode(false);
      	this.setHapticFeedbackEnabled(false);
      	this.setWillNotDraw(false);
      	
		this.renderer = renderer;
		this.keyLayout = keyLayout;
		this.posX = x;
		this.posY = y;
		this.keyWidth = w;
		this.keyHeight = h;
		
		int myWidth  = (int)(this.keyWidth*(float)this.keyLayout.width);
		int myHeight = (int)(this.keyHeight*(float)this.keyLayout.height);
		this.setMinimumHeight(myHeight);
		this.setMinimumWidth(myWidth);
		
		this.buffer = Bitmap.createBitmap(myWidth,myHeight, Bitmap.Config.ARGB_8888);
		this.bufferCanvas = new Canvas(this.buffer);
	}
	
	@Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int width = (int)(this.keyWidth*(float)this.keyLayout.width);
		if (MeasureSpec.getSize(widthMeasureSpec) < width + 10) 
		{
			width = MeasureSpec.getSize(widthMeasureSpec);
		}
		this.setMeasuredDimension(width, (int)(this.keyHeight*(float)this.keyLayout.height));
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
}
