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
    Handler handler = new Handler();
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
				logic.onLongPressKey(logic.keyLastDown);
			}
		}    	
    }
    private class DelRepeatHandler implements Runnable
    {
    	NumpadLogic logic;
    	NumpadKey key;    	
    	DelRepeatHandler(NumpadLogic logic)
    	{
    		this.logic = logic;
    	}
		@Override public void run()
		{
			if(logic.keyLastDown == this.key)
			{
				logic.listener.onDeleteInput();
				logic.handler.postDelayed(this, logic.tapRepeatDelay);	
				logic.tapRepeatDelay -= 25;
				if(logic.tapRepeatDelay < 75)
				{
					logic.tapRepeatDelay = 75;
				}
			}
		}    	
    }
	
	private TextInputListener listener;
	private boolean shiftState = false;
	private boolean shiftLock = false;
	private boolean altState = false;
	private boolean numlockState = false;
	private long keyLastUpTime[];
	private int keyTapIndex[];
	private NumpadKey keyLastDown;
	private NumpadKey keyLastUp;
	private NumpadKey keyLastLongPress;
	private NumpadKey shiftKey;
	private LongPressHandler longPressHandler;
	private DelRepeatHandler delRepeatHandler;
	private static final long longPressTime = 500;
	private static final long tapTimeoutTime = 750;
	private long tapRepeatDelay = 200;
	
	NumpadLogic(TextInputListener listener,NumpadLayout layout)
	{
		this.listener = listener;
		this.longPressHandler = new LongPressHandler(this);
		this.delRepeatHandler = new DelRepeatHandler(this);
		this.keyLastUpTime    = new long[layout.countKey()];
		this.keyTapIndex      = new int[layout.countKey()];
	}
	
	@Override public void onKeyDown(NumpadKey key) 
	{
		key.setVisibility(View.VISIBLE);
		this.keyLastDown = key;
		if(key.keymap.defaultTapMap[0].keyCode == NumpadKeymap.keyCodeDel)
		{
			this.delRepeatHandler.key = key;
			this.tapRepeatDelay = 200;
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
					if(currentTime - this.keyLastUpTime[key.id] < NumpadLogic.tapTimeoutTime)
					{
						this.keyTapIndex[key.id]++;
						if(this.keyTapIndex[key.id] >= tapMap.length)
						{
							this.keyTapIndex[key.id] = 0;
						}
						replace = true;
					}
					else
					{
						this.keyTapIndex[key.id] = 0;
					}
				}
				else if(this.keyLastUp != null)
				{
					this.keyTapIndex[this.keyLastUp.id] = 0;
				}
				this.sendKeyValue(key, tapMap[this.keyTapIndex[key.id]], replace);
			}									
		}
		
		this.keyLastUpTime[key.id] = currentTime;
		this.keyLastUp = key;				
	}
	
	public void onLongPressKey(NumpadKey key)
	{
		this.keyLastLongPress = key;
		this.sendKeyValue(key, key.keymap.longPressMap, false);
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
			this.keyTapIndex[this.keyLastUp.id] = 0;
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
