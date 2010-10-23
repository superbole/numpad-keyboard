package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Paint;

public class NumpadGlyphSpace implements NumpadGlyph
{
	@Override public void drawCanvas(Canvas canvas, char text,Paint paint,
			 float posX, float posY, 
          float x, float y, 
          float w, float h)
	{
		float gx = x + posX*w + 0.5f;
		float gy = y + posY*h - 3.5f;
		float verts[] = { gx       , gy,
				          gx+35.0f , gy,
				          
				          gx       , gy,
				          gx       , gy-6.0f,
				          
				          gx+35.0f , gy,
				          gx+35.0f , gy-6.0f};
		canvas.drawLines(verts, paint);
	}

}
