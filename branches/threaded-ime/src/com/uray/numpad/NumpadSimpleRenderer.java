package com.uray.numpad;

import android.graphics.Canvas;

public class NumpadSimpleRenderer implements NumpadRenderer
{
	public NumpadSimpleRenderer()
	{
		
	}
	
	@Override public void render(Canvas canvas, NumpadLayout layout)
	{
		canvas.drawARGB(128, 255, 192, 128);
	}
}
