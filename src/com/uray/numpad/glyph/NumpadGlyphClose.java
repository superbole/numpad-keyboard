package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Paint;

public class NumpadGlyphClose implements NumpadGlyph
{
	@Override public void drawCanvas(Canvas canvas, char text,Paint paint,
			 float posX, float posY, 
          float x, float y, 
          float w, float h)
	{
		float gx = x + posX*w + 0.5f;
		float gy = y + posY*h - 0.5f;
		float verts[] = { gx       , gy,
				          gx+5.0f  , gy+5.0f,
				          
				          gx       , gy,
				          gx+5.0f  , gy-5.0f,
				          
				          gx       , gy,
				          gx-5.0f  , gy+5.0f,
				          
				          gx       , gy,
				          gx-5.0f  , gy-5.0f,};
		canvas.drawLines(verts, paint);

	}

}
