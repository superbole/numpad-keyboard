package com.uray.numpad.impl;

import com.uray.numpad.NumpadKey;
import com.uray.numpad.NumpadLayout;
import com.uray.numpad.NumpadRenderer;
import com.uray.numpad.glyph.NumpadGlyph;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;

public class NumpadDefaultRenderer implements NumpadRenderer
{
	private int bgColor = Color.argb(128, 132, 132, 132);
	private Bitmap buffer;
	private Canvas bufferCanvas;
	private Paint paint = new Paint();	
	private Rect gradBound = new Rect();
	private GradientDrawable gradPaint; 
	private GradientDrawable gradPaintPressed; 
	
	public NumpadDefaultRenderer()
	{
		this.paint.setAntiAlias(true);
		this.paint.setDither(true);
		this.paint.setSubpixelText(true);	
		int gradColor[] = 
		{ 
				Color.argb(192, 172, 172, 172),
				Color.argb(192, 130, 130, 130)            
		};	
		this.gradPaint = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,gradColor);
		this.gradPaint.setGradientType(GradientDrawable.LINEAR_GRADIENT);
		this.gradPaint.setDither(true);
		
		int gradColorPressed[] = 
		{ 
				Color.argb(192, 64, 64, 64),  
				Color.argb(192, 128, 128, 128)			          
		};
		this.gradPaintPressed = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,gradColorPressed);
		this.gradPaintPressed.setGradientType(GradientDrawable.LINEAR_GRADIENT);
		this.gradPaintPressed.setDither(true);
	}
	
	private void renderBuffer(NumpadLayout layout)
	{
		bufferCanvas.drawColor(this.bgColor);
		paint.setARGB(192, 168, 168, 168);
						
		int w = this.buffer.getWidth();
		int h = this.buffer.getHeight();
		for(int i=0 ; i<layout.countKey() ; i++)
		{						
			NumpadKey key = layout.getKey(i);
			float posx = key.getKeyPosX()  * w+1.0f;
			float posy = key.getKeyPosY()  * h+1.0f;
			float keyw = key.getKeyWidth() * w-2.0f;
			float keyh = key.getKeyHeight()* h-2.0f;
			this.gradBound.set((int)posx,(int)posy,(int)(posx+keyw),(int)(posy+keyh));
			this.gradPaint.setBounds(this.gradBound);
			this.gradPaint.draw(this.bufferCanvas);
					
			for(int g=0 ; g<key.glyph.size() ; g++)
			{				
				NumpadGlyph glyph = key.glyph.get(g);
				glyph.drawCanvas(this.bufferCanvas, posx,posy,keyw,keyh);										
			}
		}
	}
	
	@Override public void renderLayout(Canvas canvas, NumpadLayout layout)
	{		
		if( (this.buffer == null) || 
			(canvas.getWidth() != this.buffer.getWidth() ) ||
			(canvas.getHeight()!= this.buffer.getHeight()) )
		{
			this.buffer = Bitmap.createBitmap(canvas.getWidth(),canvas.getHeight(),Bitmap.Config.ARGB_8888);
			this.bufferCanvas = new Canvas(this.buffer);
		}
		this.renderBuffer(layout);
		this.redrawLayout(canvas);
	}

	@Override public void redrawLayout(Canvas canvas)
	{
		canvas.drawBitmap(this.buffer, 0, 0, null);
	}

	@Override public void renderKey(Canvas canvas, NumpadKey key)
	{
		this.gradBound.set(0,0,canvas.getWidth(),canvas.getHeight());
		this.gradPaintPressed.setBounds(this.gradBound);
		this.gradPaintPressed.draw(canvas);		
		for(int g=0 ; g<key.glyph.size() ; g++)
		{				
			NumpadGlyph glyph = key.glyph.get(g);
			glyph.drawCanvas(canvas, 0.0f,0.0f,canvas.getWidth(),canvas.getHeight());										
		}
	}
}
