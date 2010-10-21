package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class NumpadGlyphReset implements NumpadGlyph
{
	public Paint paint;
	public float posX;
	public float posY;
	public NumpadGlyphReset()
	{
		
	}
	public NumpadGlyphReset(float posX,float posY,Paint paint)
	{		
		this.paint = paint;
		this.posX = posX;
		this.posY = posY;
		if(this.paint == null)
		{
			this.paint = new Paint();
			this.paint.setAntiAlias(true);
			this.paint.setDither(true);
			this.paint.setSubpixelText(true);	
			this.paint.setColor(Color.WHITE);
			this.paint.setTextSize(32.0f);		
		}
	}
	
	public void drawCanvas(Canvas canvas, float x, float y, float w, float h)
	{
		float gx = x + this.posX*w;
		float gy = y + this.posY*h;
		canvas.drawCircle(gx+ 2.5f, gy-2.5f, 2.5f, paint);
		canvas.drawCircle(gx+12.5f, gy-2.5f, 2.5f, paint);
		canvas.drawCircle(gx+22.5f, gy-2.5f, 2.5f, paint);
		canvas.drawLine  (gx+ 2.5f, gy-12.5f, gx+22.5f, gy-12.5f, paint);
		canvas.drawLine  (gx+ 2.5f, gy-12.5f, gx+ 2.5f, gy- 6.0f, paint);
		canvas.drawLine  (gx+22.5f, gy-12.5f, gx+22.5f, gy- 6.0f, paint);
		canvas.drawLine  (gx+ 2.5f, gy- 6.0f, gx+ 5.5f, gy- 9.0f, paint);
		canvas.drawLine  (gx+ 2.5f, gy- 6.0f, gx- 0.5f, gy- 9.0f, paint);
	}

}
