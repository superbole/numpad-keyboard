package com.uray.numpad;

import com.uray.numpad.glyph.NumpadGlyph;

public class NumpadKeyValue
{
	public NumpadGlyph label;
	public float labelPosX;
	public float labelPosY;
	public char value;
	public int keyCode;
	public NumpadKeyValue(){};
	public NumpadKeyValue(NumpadGlyph label,float x,float y,char value,int keyCode)
	{
		this.label = label;
		this.value = value;
		this.keyCode = keyCode;
		this.labelPosX = x;
		this.labelPosY = y;
	}
}