package com.uray.numpad;

import android.view.ViewGroup;

import com.uray.numpad.NumpadRenderer;

public abstract class NumpadLayout 
{	
	public ViewGroup parentView;
	public NumpadRenderer renderer;
	public NumpadLayout(ViewGroup parentView,NumpadRenderer renderer)
	{
		this.parentView = parentView;
		this.renderer = renderer;
	}
	public abstract NumpadKey getKey(int x, int y);
	public abstract void setConstraint(int w, int h);
	public int width;
	public int height;
	public NumpadKey[] keys;
	public void layoutKeys(int l, int t,int r,int b)
	{
		for(NumpadKey key : this.keys)
		{
			int px = (int)(key.posX*(float)this.width);
			int py = (int)(key.posY*(float)this.height);
			int pr = px + (int)(key.keyWidth*(float)this.width);
			int pb = py + (int)(key.keyHeight*(float)this.height);
			key.layout(px, py, pr, pb);
		}
	}
}
