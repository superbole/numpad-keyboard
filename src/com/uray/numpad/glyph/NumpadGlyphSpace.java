package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class NumpadGlyphSpace implements NumpadGlyph
{
	public Paint paint;
	public float posX;
	public float posY;
	public NumpadGlyphSpace()
	{
		
	}
	public NumpadGlyphSpace(float posX,float posY,Paint paint)
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
			this.paint.setStrokeWidth(2.0f);
			this.paint.setStrokeMiter(1.0f);
		}
	}
	@Override
	public void drawCanvas(Canvas canvas, float x, float y, float w, float h)
	{
		float gx = x + this.posX*w + 0.5f;
		float gy = y + this.posY*h - 3.5f;
		float verts[] = { gx       , gy,
				          gx+35.0f , gy,
				          
				          gx       , gy,
				          gx       , gy-6.0f,
				          
				          gx+35.0f , gy,
				          gx+35.0f , gy-6.0f};
		canvas.drawLines(verts, this.paint);
	}

}
