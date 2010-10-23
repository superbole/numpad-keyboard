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
	public abstract NumpadKey getKey(int index);
	public abstract NumpadKey getKey(int x, int y);
	public abstract void setConstraint(int w, int h);
	public abstract int countKey();
	public int width;
	public int height;
	public void layoutKeys(int l, int t,int r,int b)
	{
		for(int i=0 ; i<this.countKey() ; i++)
		{
			NumpadKey key = this.getKey(i);
			int px = (int)(key.posX*(float)this.width);
			int py = (int)(key.posY*(float)this.height);
			int pr = px + (int)(key.keyWidth*(float)this.width);
			int pb = py + (int)(key.keyHeight*(float)this.height);
			key.layout(px, py, pr, pb);
		}
	}
}
