package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Paint;

public class NumpadGlyphNumlock implements NumpadGlyph
{
	@Override public void drawCanvas(Canvas canvas, char text,Paint paint,
			 float posX, float posY, 
          float x, float y, 
          float w, float h)
	{
		float gx = x + posX*w + 0.5f;
		float gy = y + posY*h - 0.5f;
		float verts[] = { gx       , gy,
				          gx+16.0f , gy,
				          
				          gx+16.0f , gy,
				          gx+16.0f , gy+ 16.0f,
				          
				          gx+16.0f , gy+ 16.0f,
				          gx       , gy+ 16.0f,
				          
				          gx       , gy+ 16.0f,
				          gx       , gy      };
		canvas.drawLines(verts, paint);
		float fontsize = paint.getTextSize();
		paint.setTextSize(7.0f);
		canvas.drawText("1", gx+ 2.0f, gy+ 7.0f, paint);
		canvas.drawText("2", gx+ 6.0f, gy+10.0f, paint);
		canvas.drawText("3", gx+10.0f, gy+13.0f, paint);
		paint.setTextSize(fontsize);
	}
}
