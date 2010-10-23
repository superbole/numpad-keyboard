package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

public class NumpadGlyphCapslock implements NumpadGlyph
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
		Typeface typeface = paint.getTypeface();
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextSize(11.0f);
		canvas.drawText("A", gx+ 4.0f, gy+12.5f, paint);
		paint.setTextSize(fontsize);
		paint.setTypeface(typeface);
	}

}
