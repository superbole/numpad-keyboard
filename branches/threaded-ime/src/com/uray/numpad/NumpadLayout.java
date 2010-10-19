package com.uray.numpad;

import android.view.View;

public interface NumpadLayout 
{	
	interface NumpadLayoutListener
	{
		void onLayoutChange(NumpadLayout layout);
	}
	NumpadKey getKey(int index);
	NumpadKey getKey(float x, float y);
	void setParentView(View view);
	void setListener(NumpadLayoutListener listener);
	void setConstraint(int w, int h);
	int countKey();
	int getMinWidth();
	int getMinHeight();
}
