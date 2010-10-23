package com.uray.numpad.glyph;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface NumpadGlyph
{
	public void drawCanvas(Canvas canvas, char text,Paint paint,float posX, float posY,float x, float y, float w, float h);
}
