package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Paint;

public class NumpadGlyphReturn implements NumpadGlyph
{
	@Override public void drawCanvas(Canvas canvas, char text,Paint paint,
			 float posX, float posY, 
          float x, float y, 
          float w, float h)
	{
		float gx = x + posX*w + 0.5f;
		float gy = y + posY*h - 0.5f;
		float verts[] = { gx       , gy,
				          gx+ 7.0f , gy- 7.0f,
				          
				          gx+ 7.0f , gy- 7.0f,
				          gx+ 7.0f , gy- 2.0f,
				          
				          gx+ 7.0f , gy- 2.0f,
				          gx+14.0f , gy- 2.0f,
				          
				          gx+14.0f , gy- 2.0f,
				          gx+14.0f , gy-10.0f,
				          
				          gx+14.0f , gy-10.0f,
				          gx+19.0f , gy-10.0f,
				          
				          gx+19.0f , gy-10.0f,
				          gx+19.0f , gy+ 2.0f,
				          
				          gx+19.0f , gy+ 2.0f,
				          gx+ 7.0f , gy+ 2.0f,
				          
				          gx+ 7.0f , gy+ 2.0f,
				          gx+ 7.0f , gy+ 7.0f,
				          
				          gx+ 7.0f , gy+ 7.0f,
				          gx       , gy,};
		canvas.drawLines(verts, paint);
	}

}
