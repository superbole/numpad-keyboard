package com.uray.numpad;

import com.uray.numpad.impl.NumpadDefaultRenderer;
import com.uray.numpad.impl.NumpadPortraitLayout;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.inputmethodservice.InputMethodService;
import android.os.Vibrator;

public class NumpadService extends InputMethodService 
                           implements NumpadLogic.TextInputListener
{	
	private NumpadView keyview;
	private NumpadRenderer keyRenderer;
	private NumpadLogic logic;
	private NumpadLayout layout;
	private InputConnection connection;
	private Vibrator vibrator;
	private int selectionStart;
	private int selectionEnd;

	@Override public void onInitializeInterface() 
  	{		
		this.keyRenderer = new NumpadDefaultRenderer();		
		this.keyview     = new NumpadView(this);
      	this.keyview.setRenderer(this.keyRenderer);	
      	
      	this.layout = new NumpadPortraitLayout(this.keyview,this.keyRenderer);
      	this.keyview.setKeyLayout(this.layout);
      	this.logic  = new NumpadLogic(this,this.layout);
      	this.keyview.setTouchListener(this.logic);	
		this.setInputView(this.keyview);
		this.vibrator = (Vibrator)this.keyview.getContext().getSystemService(VIBRATOR_SERVICE);
  	}
	
	@Override public void onStartInput(EditorInfo attribute, boolean restarting) 
	{
		super.onStartInput(attribute, restarting);
		this.connection = this.getCurrentInputConnection();
	}
	
	@Override public void onInsertInput(String text) 
	{
        this.connection.commitText(text, text.length());
	}

	@Override public void onReplaceInput(String text)
	{
		this.connection.deleteSurroundingText(1, 0);
		this.connection.commitText(text, text.length());
	}
	
	@Override public void onInsertInput(char code)
	{
		this.sendKeyChar(code);	
	}

	@Override public void onReplaceInput(char code)
	{
		this.connection.deleteSurroundingText(1, 0);
		this.sendKeyChar(code);	
	}

	@Override public void onDeleteInput()
	{
		if(this.selectionStart != this.selectionEnd)
		{
			this.connection.setSelection(this.selectionStart, this.selectionStart);
			this.connection.deleteSurroundingText(0, this.selectionEnd - this.selectionStart);
		}
		else
		{
			this.connection.deleteSurroundingText(1, 0);
		}				
	}
	
	@Override public void onUpdateSelection(int oldSelStart, int oldSelEnd,
											int newSelStart, int newSelEnd,
											int candidatesStart, int candidatesEnd) 
	{
		super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd,
								candidatesStart, candidatesEnd);
	
		this.selectionStart = newSelStart;
		this.selectionEnd   = newSelEnd;
	}

	@Override public void onKeyCancel()
	{
        this.requestHideSelf(0);		
	}

	@Override public void onKeyReturn()
	{
		this.sendKeyChar('\n');			
	}

	@Override public void onVibrate(int ms)
	{
		this.vibrator.vibrate(26);		
	}
}