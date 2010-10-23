package com.uray.numpad;

import android.graphics.Canvas;

public interface NumpadRenderer
{
	public void renderKey(Canvas canvas, NumpadKey key);
	public void renderLayout(Canvas canvas, NumpadLayout layout);
}
