package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Paint;

public class NumpadGlyphTab implements NumpadGlyph
{
	@Override public void drawCanvas(Canvas canvas, char text,Paint paint,
			 float posX, float posY, 
          float x, float y, 
          float w, float h)
	{
		float gx = x + posX*w + 0.5f;
		float gy = y + posY*h - 0.5f;
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
		canvas.drawLines(verts, paint);

	}

}
