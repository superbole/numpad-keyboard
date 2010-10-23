package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Paint;

public class NumpadGlyphText implements NumpadGlyph
{
	@Override public void drawCanvas(Canvas canvas, char text,Paint paint,
			 float posX, float posY, 
          float x, float y, 
          float w, float h)
	{
		float gx = x + posX*w;
		float gy = y + posY*h;
		canvas.drawText(String.valueOf(text), gx, gy, paint);		
	}
}
