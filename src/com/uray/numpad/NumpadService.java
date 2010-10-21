package com.uray.numpad;

import com.uray.numpad.impl.NumpadDefaultRenderer;
import com.uray.numpad.impl.NumpadPortraitLayout;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.inputmethodservice.InputMethodService;

public class NumpadService extends InputMethodService 
                           implements NumpadLogic.TextInputListener
{	
	private NumpadView keyview;
	private NumpadRenderer keyRenderer;
	private NumpadLogic logic;
	private NumpadLayout layout;
	private InputConnection connection;
	int selectionStart;
	int selectionEnd;

	@Override public void onInitializeInterface() 
  	{
		this.logic       = new NumpadLogic(this);
		this.keyRenderer = new NumpadDefaultRenderer();				
		this.keyview     = new NumpadView(this);
      	this.keyview.setTouchListener(this.logic);
      	this.keyview.setRenderer(this.keyRenderer);	
      	
      	this.layout = new NumpadPortraitLayout(this.keyview,this.keyRenderer);
      	this.keyview.setKeyLayout(this.layout);

		this.setInputView(this.keyview);
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
	
//	private Vibrator vibrator;
//	private String inputBuffer;
//	
//	private String longPressChar[] = {"1","2","3","4","5","6","7","8","9","@","0"};
//	private String pressChar[][] = { {""},
//			                       {"a","b","c"},
//			                       {"d","e","f"},
//			                       {"g","h","i"},
//			                       {"j","k","l"},
//			                       {"m","n","o"},
//			                       {"p","q","r","s"},
//			                       {"t","u","v"},
//			                       {"w","x","y","z"},
//			                       {"?","!"},
//			                       {" ",",","."} };
//	private String pressSym[][]  = {{"€","£","¥"},
//						            {"$","#","%"},
//						            {"&","*","^"},
//						            {"-","<",";"},
//						            {"+",">","_"},
//						            {"=","\\","|"},
//						            {"\"","(","~"},
//						            {"\"",")","["},
//						            {":","{","]"},
//						            {"/","}"},
//						            {"`","©","÷","±","°"} };
//	
//	private int pressCounter[]    = {0,0,0,0,0,0,0,0,0,0,0};
//	private int pressCharSize[]   = {1,3,3,3,3,3,4,3,4,2,3};
//	private int pressSymCounter[] = {0,0,0,0,0,0,0,0,0,0,0};
//	private int pressSymSize[]    = {3,3,3,3,3,3,3,3,3,2,5};
//	private int lastPressIndex    = 0;
//	private int selectionBegin    = 0;
//	private int selectionEnd      = 0;
//	
//	private boolean keyShifted  = false;
//	private boolean keyNumeric  = false;
//	private boolean keySymbol   = false;
//	
//	private static final int keyTimeout      = 750;
//	private static final int longPressTime   = 650;
//	private long lastKeyHitTime = 0;
//	private long keyPressTime   = 0;
//	
//    @Override public void onInitializeInterface() 
//    {
//    	this.keyboard = new Keyboard(this,R.xml.keylayout);  
//    }
//    
//    @Override public View onCreateInputView() 
//    {
//    	LayoutInflater inflater = this.getLayoutInflater();
//        this.keyview = (NumpadView)inflater.inflate(R.layout.main, null);
//        this.keyview.setOnKeyboardActionListener(this);
//        this.keyview.setKeyboard(this.keyboard);     
//        this.vibrator = (Vibrator)this.keyview.getContext().getSystemService(VIBRATOR_SERVICE);
//        return this.keyview;
//    }
//    
//    @Override public void onStartInput(EditorInfo attribute, boolean restarting) 
//    {
//        super.onStartInput(attribute, restarting);
//        this.connection = this.getCurrentInputConnection();
//    }
//    
//    @Override public void onUpdateSelection(int oldSelStart, int oldSelEnd,
//                                            int newSelStart, int newSelEnd,
//                                            int candidatesStart, int candidatesEnd) 
//    {
//        super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd,
//                                candidatesStart, candidatesEnd);
//        
//        this.selectionBegin = newSelStart;
//        this.selectionEnd   = newSelEnd;
//    }
//    
//	public void onKey(int primaryCode, int[] keyCodes) 
//	{ 		
//		if (primaryCode == Keyboard.KEYCODE_DELETE)
//		{
//			if(this.selectionBegin != this.selectionEnd)
//			{
//				this.connection.setSelection(this.selectionBegin, this.selectionBegin);
//				this.connection.deleteSurroundingText(0, this.selectionEnd - this.selectionBegin);
//			}
//			else
//			{
//				this.connection.deleteSurroundingText(1, 0);
//			}			
//		}
//		else if(primaryCode == Keyboard.KEYCODE_CANCEL)
//		{
//	        this.requestHideSelf(0);
//	        this.keyview.closing();
//		}
//		else if(primaryCode == Keyboard.KEYCODE_MODE_CHANGE)
//		{
//			this.keySymbol = !this.keySymbol;
//		}
//		else if(primaryCode == Keyboard.KEYCODE_SHIFT)
//		{
//			this.keyShifted = !this.keyShifted;
//		}
//		else if(primaryCode == Keyboard.KEYCODE_DONE)
//		{
//	        this.requestHideSelf(0);
//	        this.keyview.closing();
//		}
//		else if(primaryCode == 10)
//		{
//			this.connection.commitText("\n", 1);
//		}
//		else if(primaryCode == 9)
//		{
//			this.keyNumeric = !this.keyNumeric;
//		}
//		else
//		{
//			long currentTime = SystemClock.elapsedRealtime();
//			long deltaTime   = currentTime - this.lastKeyHitTime;	
//			long keydownTime = currentTime - this.keyPressTime;
//			int keyIndex     = primaryCode-48;
//			boolean isResetCounter = (deltaTime > NumpadService.keyTimeout    ) || 
//			                         (keyIndex != this.lastPressIndex);	
//			boolean isNumericMode  = (keydownTime > NumpadService.longPressTime) || 
//			                         this.keyNumeric;
//			boolean isReplaceLastChar = (!isResetCounter && !isNumericMode);
//			this.lastKeyHitTime = currentTime;
//			if(primaryCode >= 48 && primaryCode <= 58)
//			{
//				if(isNumericMode)
//				{
//					this.inputBuffer = this.longPressChar[keyIndex];
//				}
//				else if(this.keySymbol)
//				{
//					this.inputBuffer = keyToSym(keyIndex,isResetCounter);
//				}
//				else
//				{
//					this.inputBuffer = keyToChar(keyIndex,isResetCounter);
//				}				
//				if(this.inputBuffer.length() > 0)
//				{
//					if(this.selectionBegin != this.selectionEnd)
//					{
//						this.connection.setSelection(this.selectionBegin, this.selectionBegin);
//						this.connection.deleteSurroundingText(0, this.selectionEnd - this.selectionBegin);
//					}
//					if(isReplaceLastChar)
//					{
//						this.connection.deleteSurroundingText(1, 0);
//					}
//					this.connection.commitText(this.inputBuffer, this.inputBuffer.length());	
//				}	
//			}				
//		}
//	}
//	
//	private String keyToSym(int index,boolean resetCounter) 
//    {
//    	if(resetCounter)
//    	{
//    		this.pressSymCounter[this.lastPressIndex] = 0;
//    		this.pressSymCounter[index] = 0;
//    	}
//    	
//    	String result = this.pressSym[index][this.pressSymCounter[index]];	
//    	this.lastPressIndex = index;
//    	this.pressSymCounter[index]++;    	
//    	if(this.pressSymCounter[index] == this.pressSymSize[index])
//    	{
//    		this.pressSymCounter[index] = 0; //repeat
//    	}    	
//		return result;
//	}
//	
//    private String keyToChar(int index,boolean resetCounter) 
//    {
//    	if(resetCounter || index == 0)
//    	{
//    		this.pressCounter[this.lastPressIndex] = 0;    		
//    	}
//
//    	String result = this.pressChar[index][this.pressCounter[index]];	
//    	if(index>0 && Character.isLetter(result.charAt(0)) && this.keyShifted)
//    	{
//    		result = Character.toString(Character.toUpperCase(result.charAt(0)));
//    	}
//    	
//    	this.lastPressIndex = index;
//    	this.pressCounter[index]++;    	
//    	if(this.pressCounter[index] == this.pressCharSize[index])
//    	{
//    		this.pressCounter[index] = 0; //repeat
//    	}
//		return result;
//	}
//
//    public void onPress(int primaryCode) 
//    {
//    	this.vibrator.cancel();
//    	this.keyPressTime = SystemClock.elapsedRealtime();
//    }  
//    
//    public void onRelease(int primaryCode) 
//    { 
//    	this.vibrator.vibrate(22);
//    }
//    
//	public void onText(CharSequence text) { } 
}
