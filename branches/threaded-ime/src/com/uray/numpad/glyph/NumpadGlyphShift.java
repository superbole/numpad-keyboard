package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Paint;

public class NumpadGlyphShift implements NumpadGlyph
{
	@Override public void drawCanvas(Canvas canvas, char text,Paint paint,
			 float posX, float posY, 
          float x, float y, 
          float w, float h)
	{
		float gx = x + posX*w + 0.5f;
		float gy = y + posY*h - 3.5f;
		float verts[] = { gx       , gy,
				          gx+ 9.0f , gy-9.0f,
				          
				          gx+ 9.0f , gy-9.0f,
				          gx+18.0f , gy,
				          
				          gx+18.0f , gy,
				          gx+12.0f , gy,
				          
				          gx+12.0f , gy,
				          gx+12.0f , gy+4.0f,
				          
				          gx+12.0f , gy+4.0f,
				          gx+ 6.0f , gy+4.0f,
				          
				          gx+ 6.0f , gy+4.0f,
				          gx+ 6.0f , gy,
				          
				          gx+ 6.0f , gy,
				          gx, gy,
				          
				          gx+ 6.0f , gy+6.0f,
				          gx+12.0f , gy+6.0f,
				          
				          gx+12.0f , gy+6.0f,
				          gx+12.0f , gy+7.0f,
				          
				          gx+12.0f , gy+7.0f,
				          gx+ 6.0f , gy+7.0f,
				          
				          gx+ 6.0f , gy+7.0f,				          
				          gx+ 6.0f , gy+6.0f};
		canvas.drawLines(verts, paint);

	}

}
