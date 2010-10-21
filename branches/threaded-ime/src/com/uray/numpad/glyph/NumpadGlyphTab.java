package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class NumpadGlyphTab implements NumpadGlyph
{
	public Paint paint;
	public float posX;
	public float posY;
	public NumpadGlyphTab()
	{
		
	}
	public NumpadGlyphTab(float posX,float posY,Paint paint)
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
			this.paint.setTextSize(7.0f);		
			this.paint.setStrokeWidth(2.0f);
			this.paint.setStrokeMiter(0.0f);
			this.paint.setTypeface(Typeface.DEFAULT_BOLD);
		}
	}
	@Override
	public void drawCanvas(Canvas canvas, float x, float y, float w, float h)
	{
		float gx = x + this.posX*w + 0.5f;
		float gy = y + this.posY*h - 0.5f;
		float verts[] = { gx       , gy,
				          gx+14.0f , gy,
				          
				          gx+14.0f , gy,
				          gx+10.0f , gy- 4.0f,
				          
				          gx+14.0f , gy,
				          gx+10.0f , gy+ 4.0f,
				          
				          gx+ 2.0f , gy+ 7.0f,
				          gx+16.0f , gy+ 7.0f,
				          
				          gx+ 2.0f , gy+ 7.0f,
				          gx+ 6.0f , gy+ 3.0f,
				          
				          gx+ 2.0f , gy+ 7.0f,
				          gx+ 6.0f , gy+11.0f,
				          
				          gx+16.0f , gy- 4.0f,
				          gx+16.0f , gy+ 4.0f,
				          
				          gx       , gy+ 3.0f,
				          gx       , gy+11.0f,};
		canvas.drawLines(verts, this.paint);

	}

}
