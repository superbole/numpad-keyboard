package com.uray.numpad;

import com.uray.numpad.NumpadView.TouchListener;

public class NumpadLogic implements TouchListener
{
	interface TextInputListener
	{
		void onInsertInput(String text);
		void onReplaceInput(String text);
		void onDeleteInput();
	}
	
	NumpadLogic(TextInputListener listener)
	{
		
	}
	
	@Override public void onKeyDown(NumpadKey key) 
	{
		
	}
	
	@Override public void onKeyUp(NumpadKey key) 
	{
		
	}
}
