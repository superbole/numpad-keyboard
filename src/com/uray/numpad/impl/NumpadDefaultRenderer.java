package com.uray.numpad.impl;

import com.uray.numpad.NumpadKey;
import com.uray.numpad.NumpadKeyValue;
import com.uray.numpad.NumpadKeymap;
import com.uray.numpad.NumpadLayout;
import com.uray.numpad.NumpadRenderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;

public class NumpadDefaultRenderer implements NumpadRenderer
{
	private Paint paint = new Paint();	
	private Rect gradBound = new Rect();
	private GradientDrawable gradPaint; 
	private GradientDrawable gradPaintPressed; 
	private Paint letterPaint = new Paint();
	private Paint symbolPaint = new Paint();
	private Paint numberPaint = new Paint();
	public static int longpressLabelColor = Color.argb(255, 32, 32, 32);
	public static int symbolLabelColor = Color.argb(255,64,64,64);
	public static int letterLabelColor = Color.WHITE;
	
	public NumpadDefaultRenderer()
	{
		this.numberPaint.setTextSize(32.0f);
		this.numberPaint.setAntiAlias(true);
		this.numberPaint.setDither(true);
		this.numberPaint.setSubpixelText(true);	
		this.numberPaint.setStrokeWidth(2.0f);
		this.numberPaint.setColor(NumpadDefaultRenderer.longpressLabelColor);
		
		this.letterPaint.setTextSize(21.0f);
		this.letterPaint.setAntiAlias(true);
		this.letterPaint.setDither(true);
		this.letterPaint.setSubpixelText(true);	
		this.letterPaint.setStrokeWidth(2.0f);
		this.letterPaint.setColor(NumpadDefaultRenderer.letterLabelColor);
		
		this.symbolPaint.setTextSize(18.0f);
		this.symbolPaint.setAntiAlias(true);
		this.symbolPaint.setDither(true);
		this.symbolPaint.setSubpixelText(true);
		this.symbolPaint.setTypeface(Typeface.MONOSPACE);
		this.symbolPaint.setStrokeWidth(2.0f);
		this.symbolPaint.setColor(NumpadDefaultRenderer.symbolLabelColor);
		
		this.paint.setAntiAlias(true);
		this.paint.setDither(true);
		this.paint.setSubpixelText(true);	
		int gradColor[] = 
		{ 
				Color.argb(255, 172, 172, 172),
				Color.argb(255, 110, 110, 110)            
		};	
		this.gradPaint = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,gradColor);
		this.gradPaint.setGradientType(GradientDrawable.LINEAR_GRADIENT);
		this.gradPaint.setDither(true);
		
		int gradColorPressed[] = 
		{ 
				Color.argb(255, 64, 64, 64),  
				Color.argb(255, 128, 128, 128)			          
		};
		this.gradPaintPressed = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,gradColorPressed);
		this.gradPaintPressed.setGradientType(GradientDrawable.LINEAR_GRADIENT);
		this.gradPaintPressed.setDither(true);
	}	
	
	@Override public void renderLayout(Canvas canvas, NumpadLayout layout)
	{		
		int w = layout.width;
		int h = layout.height;
		for(NumpadKey key : layout.keys)
		{						
			if(key != null)
			{
				float posx = key.posX     * w;
				float posy = key.posY     * h;
				float keyw = key.keyWidth * w;
				float keyh = key.keyHeight* h;
				if( (key.edgeFlag & NumpadKey.edgeLeft) != 0)
				{
					posx += 1.0f;
				}
				if( (key.edgeFlag & NumpadKey.edgeRight) != 0)
				{
					keyw -= 1.0f;
					if( (key.edgeFlag & NumpadKey.edgeLeft) != 0)
					{
						keyw -= 1.0f;
					}
				}
				if( (key.edgeFlag & NumpadKey.edgeTop) != 0)
				{
					posy += 1.0f;
				}
				if( (key.edgeFlag & NumpadKey.edgeBottom) != 0)
				{
					keyh -= 1.0f;
					if( (key.edgeFlag & NumpadKey.edgeTop) != 0)
					{
						keyh -= 1.0f;
					}
				}
				this.gradBound.set((int)posx,(int)posy,(int)(posx+keyw),(int)(posy+keyh));
				this.gradPaint.setBounds(this.gradBound);
				this.gradPaint.draw(canvas);
						
				if(key.keymap != null)
				{
					this.renderKeyMap(canvas, key.keymap, posx, posy, keyw, keyh);
				}
			}
		}
	}
	
	public void renderKeyMap(Canvas canvas, NumpadKeymap keymap,
			                 float posx,float posy, float keyw,float keyh)
	{
		if(keymap.longPressMap != null)
		{
			float lx = keymap.longPressMap.labelPosX;
			float ly = keymap.longPressMap.labelPosY;
			if(keymap.longPressMap.label != null)
			{
				keymap.longPressMap.label.drawCanvas(canvas, keymap.longPressMap.value,this.numberPaint, lx, ly, posx,posy,keyw,keyh);
			}
		}
		if(keymap.defaultTapMap != null)
		{
			for(int k=0 ; k<keymap.defaultTapMap.length ; k++)
			{
				NumpadKeyValue keyValue = keymap.defaultTapMap[k];
				if(keyValue != null && keyValue.label != null)
				{
					float lx = keyValue.labelPosX;
					float ly = keyValue.labelPosY;					
					keyValue.label.drawCanvas(canvas, keyValue.value,this.letterPaint,lx, ly, posx,posy,keyw,keyh);
				}							
			}
		}
		if(keymap.shiftTapMap != null)
		{
			for(int k=0 ; k<keymap.shiftTapMap.length ; k++)
			{
				NumpadKeyValue keyValue = keymap.shiftTapMap[k];
				if(keyValue != null && keyValue.label != null)
				{
					float lx = keyValue.labelPosX;
					float ly = keyValue.labelPosY;					
					keyValue.label.drawCanvas(canvas, keyValue.value,this.letterPaint,lx, ly, posx,posy,keyw,keyh);
				}							
			}
		}
		if(keymap.altTapMap != null)
		{
			for(int k=0 ; k<keymap.altTapMap.length ; k++)
			{
				NumpadKeyValue keyValue = keymap.altTapMap[k];
				if(keyValue != null && keyValue.label != null)
				{
					float lx = keyValue.labelPosX;
					float ly = keyValue.labelPosY;					
					keyValue.label.drawCanvas(canvas, keyValue.value,this.symbolPaint,lx, ly, posx,posy,keyw,keyh);
				}							
			}
		}
	}

	@Override public void renderKey(Canvas canvas, NumpadKey key)
	{
		this.gradBound.set(0,0,canvas.getWidth(),canvas.getHeight());
		this.gradPaintPressed.setBounds(this.gradBound);
		this.gradPaintPressed.draw(canvas);	
		if(key.keymap != null)
		{
			this.renderKeyMap(canvas, key.keymap, 0.0f, 0.0f, canvas.getWidth(),canvas.getHeight());
		}
	}
}
