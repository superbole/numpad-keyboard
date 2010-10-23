package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Paint;

public class NumpadGlyphDel implements NumpadGlyph
{
	@Override public void drawCanvas(Canvas canvas, char text,Paint paint,
			 float posX, float posY, 
          float x, float y, 
          float w, float h)
	{
		float gx = x + posX*w + 0.5f;
		float gy = y + posY*h - 5.5f;
		float verts[] = { gx       , gy,
				          gx+ 8.0f , gy-8.0f,
				          
				          gx+ 8.0f , gy-8.0f,
				          gx+24.0f , gy-8.0f,
				          
				          gx+24.0f , gy-8.0f,
				          gx+24.0f , gy+8.0f,
				          
				          gx+24.0f , gy+8.0f,
				          gx+ 8.0f , gy+8.0f,
				          
				          gx+ 8.0f , gy+8.0f,
				          gx       , gy     ,
				          
				          gx+14.0f , gy     ,
				          gx+18.0f , gy-4.0f,
				          
				          gx+14.0f , gy     ,
				          gx+18.0f , gy+4.0f,
				          
				          gx+14.0f , gy     ,
				          gx+10.0f , gy-4.0f,
				          
				          gx+14.0f , gy     ,
				          gx+10.0f , gy+4.0f };
		canvas.drawLines(verts, paint);
	}

}
