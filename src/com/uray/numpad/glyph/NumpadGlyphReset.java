package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Paint;

public class NumpadGlyphReset implements NumpadGlyph
{
	@Override public void drawCanvas(Canvas canvas, char text,Paint paint,
			 float posX, float posY, 
          float x, float y, 
          float w, float h)
	{
		float gx = x + posX*w;
		float gy = y + posY*h;
		canvas.drawCircle(gx+ 2.5f, gy-2.5f, 2.5f, paint);
		canvas.drawCircle(gx+12.5f, gy-2.5f, 2.5f, paint);
		canvas.drawCircle(gx+22.5f, gy-2.5f, 2.5f, paint);
		canvas.drawLine  (gx+ 2.5f, gy-12.5f, gx+22.5f, gy-12.5f, paint);
		canvas.drawLine  (gx+ 2.5f, gy-12.5f, gx+ 2.5f, gy- 6.0f, paint);
		canvas.drawLine  (gx+22.5f, gy-12.5f, gx+22.5f, gy- 6.0f, paint);
		canvas.drawLine  (gx+ 2.5f, gy- 6.0f, gx+ 5.5f, gy- 9.0f, paint);
		canvas.drawLine  (gx+ 2.5f, gy- 6.0f, gx- 0.5f, gy- 9.0f, paint);
	}
}
