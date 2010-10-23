package com.uray.numpad;

import android.os.Handler;
import android.os.SystemClock;
import android.view.View;

import com.uray.numpad.NumpadView.TouchListener;

public class NumpadLogic implements TouchListener
{
	interface TextInputListener
	{
		void onInsertInput(String text);
		void onReplaceInput(String text);
		void onInsertInput(char code);
		void onReplaceInput(char code);
		void onDeleteInput();
		void onKeyCancel();
		void onKeyReturn();
		void onVibrate(int ms);
	}    
    private class LongPressHandler implements Runnable
    {
    	private NumpadLogic logic;
    	LongPressHandler(NumpadLogic logic)
    	{
    		this.logic = logic;
    	}
		@Override public void run()
		{
			if( logic.keyLastDown != null )
			{
				logic.keyLastLongPress = logic.keyLastDown;
				logic.sendKeyValue(logic.keyLastDown, logic.keyLastDown.keymap.longPressMap, false);
			}
		}    	
    }
    private class DelRepeatHandler implements Runnable
    {
    	private NumpadLogic logic; 	
    	DelRepeatHandler(NumpadLogic logic)
    	{
    		this.logic = logic;
    	}
		@Override public void run()
		{
			if(logic.repeatDel == true)
			{
				logic.listener.onDeleteInput();
				logic.handler.postDelayed(this, logic.tapRepeatDelay);					
				if(logic.tapRepeatDelay > 80)
				{
					logic.tapRepeatDelay -= 20;
				}
			}
		}    	
    }
	
	public TextInputListener listener;
	public boolean shiftState = false;
	public boolean shiftLock = false;
	public boolean altState = false;
	public boolean numlockState = false;
	public boolean repeatDel = false;
	public long keyLastUpTime;
	public int keyTapIndex;
	public NumpadKey keyLastDown;
	public NumpadKey keyLastUp;
	public NumpadKey keyLastLongPress;
	public NumpadKey shiftKey;
	public LongPressHandler longPressHandler;
	public DelRepeatHandler delRepeatHandler;
	public static final long longPressTime = 500;
	public static final long tapTimeoutTime = 750;
	public long tapRepeatDelay = 200;
	public Handler handler = new Handler();	
	
	NumpadLogic(TextInputListener listener)
	{
		this.listener = listener;
		this.longPressHandler = new LongPressHandler(this);
		this.delRepeatHandler = new DelRepeatHandler(this);
	}
	
	@Override public void onKeyDown(NumpadKey key) 
	{
		key.setVisibility(View.VISIBLE);
		this.keyLastDown = key;
		if(key.keymap.defaultTapMap[0].keyCode == NumpadKeymap.keyCodeDel)
		{
			this.repeatDel = true;
			this.tapRepeatDelay = 240;
			this.handler.postDelayed(this.delRepeatHandler, this.tapRepeatDelay);
		}
		else
		{
			this.handler.postDelayed(this.longPressHandler, NumpadLogic.longPressTime);		
		}
	}
	
	@Override public void onKeyUp(NumpadKey key) 
	{
		key = this.keyLastDown;
		this.keyLastDown = null;
		this.repeatDel = false;
		this.handler.removeCallbacks(this.longPressHandler);		
		long currentTime = SystemClock.elapsedRealtime();
		if(this.keyLastLongPress == key)
		{
			this.keyLastLongPress = null;
		}
		else
		{
			NumpadKeyValue[] tapMap = key.keymap.defaultTapMap;
			if(this.shiftState)
			{
				tapMap = key.keymap.shiftTapMap;
			}
			if(this.altState)
			{
				tapMap = key.keymap.altTapMap;
			}
		
			if(this.numlockState && key.keymap.longPressMap != null)
			{
				this.sendKeyValue(key,key.keymap.longPressMap, false);
			}
			else
			{
				boolean replace = false;		
				if(this.keyLastUp == key)
				{
					if(currentTime - this.keyLastUpTime < NumpadLogic.tapTimeoutTime)
					{
						if(this.keyTapIndex < tapMap.length-1)
						{
							this.keyTapIndex++;
						}
						else
						{
							this.keyTapIndex = 0;
						}
						replace = true;
					}
					else
					{
						this.keyTapIndex = 0;
					}
				}
				else if(this.keyLastUp != null)
				{
					this.keyTapIndex = 0;
				}
				this.sendKeyValue(key, tapMap[this.keyTapIndex], replace);
			}									
		}
		
		this.keyLastUpTime = currentTime;
		this.keyLastUp = key;				
	}
	
	public void sendKeyValue(NumpadKey key,NumpadKeyValue value,boolean replace)
	{
		char text = value.value;
		int keyCode = value.keyCode;
		if(keyCode == NumpadKeymap.keyCodeChars)
		{
			if(replace)
			{
				this.listener.onReplaceInput(text);
				this.listener.onVibrate(24);
			}
			else
			{
				this.listener.onInsertInput(text);
				this.listener.onVibrate(24);
			}
			if(!this.shiftLock)
			{
				this.shiftState = false;
				if(this.shiftKey != null)
				{
					this.shiftKey.setVisibility(View.INVISIBLE);
				}
			}			
		}
		else if(keyCode == NumpadKeymap.keyCodeShiftLock)
		{
			this.shiftLock = true;
			this.shiftState = true;
			this.listener.onVibrate(30);
		}
		else if(keyCode == NumpadKeymap.keyCodeNumLock)
		{
			this.numlockState = !this.numlockState;
			this.listener.onVibrate(30);
		}
		else if(keyCode == NumpadKeymap.keyCodeCancel)
		{
			this.listener.onKeyCancel();
			this.listener.onVibrate(30);
		}
		else if(keyCode == NumpadKeymap.keyCodeTapEnd)
		{
			this.keyTapIndex = 0;
			this.listener.onVibrate(30);
		}
		else if(keyCode == NumpadKeymap.keyCodeShift)
		{
			if(this.shiftLock)
			{
				this.shiftLock = false;
			}
			this.shiftState = !this.shiftState;
			this.shiftKey = key;
			this.listener.onVibrate(30);
		}
		else if(keyCode == NumpadKeymap.keyCodeAlt)
		{
			this.altState = !this.altState;
			this.numlockState = false;
			this.listener.onVibrate(30);
		}
		else if(keyCode == NumpadKeymap.keyCodeTapReturn)
		{
			this.listener.onKeyReturn();
			this.listener.onVibrate(30);
		}
		else if(keyCode == NumpadKeymap.keyCodeDel)
		{
			this.listener.onDeleteInput();
			this.listener.onVibrate(30);
		}
		
		if( (keyCode == NumpadKeymap.keyCodeAlt)       && (this.altState==false)     ||
			(keyCode == NumpadKeymap.keyCodeNumLock)   && (this.numlockState==false) ||
			(keyCode == NumpadKeymap.keyCodeShift)     && (this.shiftState==false)   ||
			(keyCode == NumpadKeymap.keyCodeShiftLock) && (this.shiftLock==false)    ||
			(key.isToggleKey == false))
		{
			key.setVisibility(View.INVISIBLE);
		}
	}
}
