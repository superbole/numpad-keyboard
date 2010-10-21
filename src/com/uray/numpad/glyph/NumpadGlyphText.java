package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class NumpadGlyphText implements NumpadGlyph
{
	public float posX;
	public float posY;
	public String text;
	public Paint paint;
	public NumpadGlyphText()
	{
	}
	public NumpadGlyphText(float posX,float posY,String text,Paint paint)
	{		
		this.paint = paint;
		this.posX = posX;
		this.posY = posY;
		this.text = text;
		if(this.paint == null)
		{
			this.paint = new Paint();
			this.paint.setAntiAlias(true);
			this.paint.setDither(true);
			this.paint.setSubpixelText(true);	
			this.paint.setColor(Color.argb(255, 0, 0, 0));
			this.paint.setTextSize(32.0f);		
		}
	}
	
	@Override public void drawCanvas(Canvas canvas, float x, float y, float w, float h)
	{
		float gx = x + this.posX*w;
		float gy = y + this.posY*h;
		canvas.drawText(this.text, gx, gy, this.paint);		
	}
}
