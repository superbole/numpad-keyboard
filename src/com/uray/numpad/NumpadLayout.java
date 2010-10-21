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
	public abstract int getMinWidth();
	public abstract int getMinHeight();
	public abstract int getHeight();
	public abstract int getWidth();
	public void layoutKeys(int l, int t,int r,int b)
	{
		for(int i=0 ; i<this.countKey() ; i++)
		{
			NumpadKey key = this.getKey(i);
			int px = (int)(key.getKeyPosX()*(float)this.getWidth());
			int py = (int)(key.getKeyPosY()*(float)this.getHeight());
			int pr = px + (int)(key.getKeyWidth()*(float)this.getWidth());
			int pb = py + (int)(key.getKeyHeight()*(float)this.getHeight());
			key.layout(px, py, pr, pb);
		}
	}
}
