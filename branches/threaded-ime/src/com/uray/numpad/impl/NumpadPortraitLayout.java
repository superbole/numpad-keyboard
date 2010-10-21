package com.uray.numpad.impl;

import com.uray.numpad.NumpadKey;
import com.uray.numpad.NumpadLayout;
import com.uray.numpad.NumpadRenderer;
import com.uray.numpad.glyph.*;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.ViewGroup;

public class NumpadPortraitLayout extends NumpadLayout
{
	NumpadKey[] keys = new NumpadKey[15];	
	private int width  = 320;
	private int height = 240;
	private int colPosPx[] = new int[5];
	private int rowPosPx[] = new int[5];
	private float colPos[] = new float[5];
	private float rowPos[] = new float[5];
	private int keyPosMap[][] = new int[4][4];
	private Paint letterPaint;
	private Paint numberPaint;
	private Paint symbolPaint;
	public static int longpressLabelColor = Color.argb(255, 32, 32, 32);
	public static int symbolLabelColor = Color.argb(255,80,80,80);
	public static int letterLabelColor = Color.WHITE;
	public NumpadPortraitLayout(ViewGroup parentView,NumpadRenderer renderer)
	{
		super(parentView,renderer);
		this.colPos[0] = 0.0000f;
		this.colPos[1] = 0.2500f;
		this.colPos[2] = 0.5000f;
		this.colPos[3] = 0.7500f;
		this.colPos[4] = 1.0000f;
		
		this.rowPos[0] = 0.0000f;
		this.rowPos[1] = 0.2677f;
		this.rowPos[2] = 0.5333f;
		this.rowPos[3] = 0.8000f;
		this.rowPos[4] = 1.0000f;
		
		this.keys[0] = new NumpadKey(parentView.getContext(),renderer,this,this.colPos[1], this.rowPos[3], this.colPos[3]-this.colPos[1], this.rowPos[4]-this.rowPos[3]);		
		this.keys[1] = new NumpadKey(parentView.getContext(),renderer,this,this.colPos[0], this.rowPos[0], this.colPos[1]-this.colPos[0], this.rowPos[1]-this.rowPos[0]);
		this.keys[2] = new NumpadKey(parentView.getContext(),renderer,this,this.colPos[1], this.rowPos[0], this.colPos[2]-this.colPos[1], this.rowPos[1]-this.rowPos[0]);
		this.keys[3] = new NumpadKey(parentView.getContext(),renderer,this,this.colPos[2], this.rowPos[0], this.colPos[3]-this.colPos[2], this.rowPos[1]-this.rowPos[0]);
		this.keys[4] = new NumpadKey(parentView.getContext(),renderer,this,this.colPos[0], this.rowPos[1], this.colPos[1]-this.colPos[0], this.rowPos[2]-this.rowPos[1]);
		this.keys[5] = new NumpadKey(parentView.getContext(),renderer,this,this.colPos[1], this.rowPos[1], this.colPos[2]-this.colPos[1], this.rowPos[2]-this.rowPos[1]);
		this.keys[6] = new NumpadKey(parentView.getContext(),renderer,this,this.colPos[2] ,this.rowPos[1], this.colPos[3]-this.colPos[2], this.rowPos[2]-this.rowPos[1]);
		this.keys[7] = new NumpadKey(parentView.getContext(),renderer,this,this.colPos[0], this.rowPos[2], this.colPos[1]-this.colPos[0], this.rowPos[3]-this.rowPos[2]);
		this.keys[8] = new NumpadKey(parentView.getContext(),renderer,this,this.colPos[1], this.rowPos[2], this.colPos[2]-this.colPos[1], this.rowPos[3]-this.rowPos[2]);
		this.keys[9] = new NumpadKey(parentView.getContext(),renderer,this,this.colPos[2] ,this.rowPos[2], this.colPos[3]-this.colPos[2], this.rowPos[3]-this.rowPos[2]);		
		this.keys[10]= new NumpadKey(parentView.getContext(),renderer,this,this.colPos[3], this.rowPos[0], this.colPos[4]-this.colPos[3], this.rowPos[1]-this.rowPos[0]);
		this.keys[11]= new NumpadKey(parentView.getContext(),renderer,this,this.colPos[3], this.rowPos[1], this.colPos[4]-this.colPos[3], this.rowPos[2]-this.rowPos[1]);
		this.keys[12]= new NumpadKey(parentView.getContext(),renderer,this,this.colPos[3], this.rowPos[2], this.colPos[4]-this.colPos[3], this.rowPos[3]-this.rowPos[2]);		
		this.keys[13]= new NumpadKey(parentView.getContext(),renderer,this,this.colPos[3], this.rowPos[3], this.colPos[4]-this.colPos[3], this.rowPos[4]-this.rowPos[3]);		
		this.keys[14]= new NumpadKey(parentView.getContext(),renderer,this,this.colPos[0], this.rowPos[3], this.colPos[1]-this.colPos[0], this.rowPos[4]-this.rowPos[3]);
		
		this.keyPosMap[0][0] = 1; //this.keys[1];
		this.keyPosMap[1][0] = 2;
		this.keyPosMap[2][0] = 3;
		this.keyPosMap[3][0] = 10;
		
		this.keyPosMap[0][1] = 4; 
		this.keyPosMap[1][1] = 5;
		this.keyPosMap[2][1] = 6;
		this.keyPosMap[3][1] = 11;
		
		this.keyPosMap[0][2] = 7; 
		this.keyPosMap[1][2] = 8;
		this.keyPosMap[2][2] = 9;
		this.keyPosMap[3][2] = 12;
		
		this.keyPosMap[0][3] = 14; 
		this.keyPosMap[1][3] = 0;
		this.keyPosMap[2][3] = 0;
		this.keyPosMap[3][3] = 13;
		
		this.numberPaint = new Paint();
		this.numberPaint.setTextSize(32.0f);
		this.numberPaint.setAntiAlias(true);
		this.numberPaint.setDither(true);
		this.numberPaint.setSubpixelText(true);	
		this.numberPaint.setColor(NumpadPortraitLayout.longpressLabelColor);
		
		NumpadGlyphText num1 = new NumpadGlyphText(0.05f,0.70f,"1",this.numberPaint);
		NumpadGlyphText num2 = new NumpadGlyphText(0.05f,0.70f,"2",this.numberPaint);
		NumpadGlyphText num3 = new NumpadGlyphText(0.05f,0.70f,"3",this.numberPaint);		
		NumpadGlyphText num4 = new NumpadGlyphText(0.05f,0.70f,"4",this.numberPaint);		
		NumpadGlyphText num5 = new NumpadGlyphText(0.05f,0.70f,"5",this.numberPaint);		
		NumpadGlyphText num6 = new NumpadGlyphText(0.05f,0.70f,"6",this.numberPaint);		
		NumpadGlyphText num7 = new NumpadGlyphText(0.05f,0.70f,"7",this.numberPaint);
		NumpadGlyphText num8 = new NumpadGlyphText(0.05f,0.70f,"8",this.numberPaint);
		NumpadGlyphText num9 = new NumpadGlyphText(0.05f,0.70f,"9",this.numberPaint);
		NumpadGlyphText num0 = new NumpadGlyphText(0.035f,0.70f,"0",this.numberPaint);
		NumpadGlyphText num10 = new NumpadGlyphText(0.045f,0.70f,"@",this.numberPaint);
		
		this.letterPaint = new Paint();
		this.letterPaint.setTextSize(21.0f);
		this.letterPaint.setAntiAlias(true);
		this.letterPaint.setDither(true);
		this.letterPaint.setSubpixelText(true);	
		this.letterPaint.setColor(NumpadPortraitLayout.letterLabelColor);
		
		NumpadGlyphText letter0 = new NumpadGlyphText(0.85f,0.375f,", .",this.letterPaint);		
		NumpadGlyphReset letter1 = new NumpadGlyphReset(0.60f,0.375f,null);
		NumpadGlyphText letter2 = new NumpadGlyphText(0.40f,0.375f,"ABC",this.letterPaint);		
		NumpadGlyphText letter3 = new NumpadGlyphText(0.45f,0.375f,"DEF",this.letterPaint);
		NumpadGlyphText letter4 = new NumpadGlyphText(0.450f,0.375f,"GHI",this.letterPaint);
		NumpadGlyphText letter5 = new NumpadGlyphText(0.525f,0.375f,"JKL",this.letterPaint);
		NumpadGlyphText letter6 = new NumpadGlyphText(0.325f,0.375f,"MNO",this.letterPaint);
		NumpadGlyphText letter7 = new NumpadGlyphText(0.325f,0.375f,"PQRS",this.letterPaint);		
		NumpadGlyphText letter8 = new NumpadGlyphText(0.425f,0.375f,"TUV",this.letterPaint);		
		NumpadGlyphText letter9 = new NumpadGlyphText(0.30f,0.375f,"WXYZ",this.letterPaint);		
		NumpadGlyphText letter10 = new NumpadGlyphText(0.525f,0.375f,"? !`",this.letterPaint);		
		
		this.symbolPaint = new Paint();
		this.symbolPaint.setTextSize(18.0f);
		this.symbolPaint.setAntiAlias(true);
		this.symbolPaint.setDither(true);
		this.symbolPaint.setSubpixelText(true);
		this.symbolPaint.setTypeface(Typeface.MONOSPACE);
		this.symbolPaint.setColor(NumpadPortraitLayout.symbolLabelColor);
		
		NumpadGlyphText symbol1 = new NumpadGlyphText(0.50f,0.900f,"€£¥",this.symbolPaint);	
		NumpadGlyphText symbol2 = new NumpadGlyphText(0.50f,0.900f,"$#%",this.symbolPaint);
		NumpadGlyphText symbol3 = new NumpadGlyphText(0.50f,0.900f,"&*^",this.symbolPaint);
		NumpadGlyphText symbol4 = new NumpadGlyphText(0.50f,0.900f,"-<;",this.symbolPaint);
		NumpadGlyphText symbol5 = new NumpadGlyphText(0.50f,0.900f,"+>_",this.symbolPaint);
		NumpadGlyphText symbol6 = new NumpadGlyphText(0.50f,0.900f,"=\\|",this.symbolPaint);
		NumpadGlyphText symbol7 = new NumpadGlyphText(0.50f,0.900f,"\"(~",this.symbolPaint);
		NumpadGlyphText symbol8 = new NumpadGlyphText(0.50f,0.900f,"\')[",this.symbolPaint);
		NumpadGlyphText symbol9 = new NumpadGlyphText(0.50f,0.900f,":{]",this.symbolPaint);
		NumpadGlyphText symbol10 = new NumpadGlyphText(0.50f,0.900f,"/}©",this.symbolPaint);
		
		NumpadGlyphDel delSymbol = new NumpadGlyphDel(0.60f,0.450f,null);
		this.keys[14].glyph.add(delSymbol);
		
		NumpadGlyphSpace spaceSymbol = new NumpadGlyphSpace(0.55f,0.425f,null);
		this.keys[0].glyph.add(spaceSymbol);
		
		NumpadGlyphReturn returnSymbol = new NumpadGlyphReturn(0.65f,0.400f,null);
		this.keys[13].glyph.add(returnSymbol);
		
		NumpadGlyphClose closeSymbol = new NumpadGlyphClose(0.20f,0.375f,null);
		closeSymbol.paint.setColor(Color.argb(255, 32, 32, 32));
		this.keys[13].glyph.add(closeSymbol);
		
		NumpadGlyphShift shiftSymbol = new NumpadGlyphShift(0.65f,0.375f,null);
		this.keys[11].glyph.add(shiftSymbol);
		
		NumpadGlyphShift shiftSymbol2 = new NumpadGlyphShift(0.125f,0.375f,null);
		shiftSymbol2.paint.setColor(Color.argb(255, 32, 32, 32));
		this.keys[11].glyph.add(shiftSymbol2);
		
		NumpadGlyphAlt altSymbol = new NumpadGlyphAlt(0.65f,0.200f,null);
		this.keys[12].glyph.add(altSymbol);
		
		NumpadGlyphNumlock numSymbol = new NumpadGlyphNumlock(0.125f,0.200f,null);
		numSymbol.paint.setColor(Color.argb(255, 32, 32, 32));
		this.keys[12].glyph.add(numSymbol);
		
		NumpadGlyphTab tabSymbol = new NumpadGlyphTab(0.835f,0.625f,null);
		tabSymbol.paint.setColor(Color.argb(255, 80, 80, 80));
		this.keys[0].glyph.add(tabSymbol);
		
		this.keys[1].glyph.add(num1);
		this.keys[2].glyph.add(num2);
		this.keys[3].glyph.add(num3);
		this.keys[4].glyph.add(num4);
		this.keys[5].glyph.add(num5);
		this.keys[6].glyph.add(num6);
		this.keys[7].glyph.add(num7);
		this.keys[8].glyph.add(num8);
		this.keys[9].glyph.add(num9);
		this.keys[0].glyph.add(num0);
		this.keys[10].glyph.add(num10);
		
		this.keys[0].glyph.add(letter0);
		this.keys[1].glyph.add(letter1);
		this.keys[2].glyph.add(letter2);
		this.keys[3].glyph.add(letter3);
		this.keys[4].glyph.add(letter4);
		this.keys[5].glyph.add(letter5);
		this.keys[6].glyph.add(letter6);
		this.keys[7].glyph.add(letter7);
		this.keys[8].glyph.add(letter8);
		this.keys[9].glyph.add(letter9);
		this.keys[10].glyph.add(letter10);
		
		this.keys[1].glyph.add(symbol1);
		this.keys[2].glyph.add(symbol2);
		this.keys[3].glyph.add(symbol3);
		this.keys[4].glyph.add(symbol4);
		this.keys[5].glyph.add(symbol5);
		this.keys[6].glyph.add(symbol6);
		this.keys[7].glyph.add(symbol7);
		this.keys[8].glyph.add(symbol8);
		this.keys[9].glyph.add(symbol9);
		this.keys[10].glyph.add(symbol10);
	}	
	
	@Override public NumpadKey getKey(int x, int y)
	{
		int nx = 0;
		int ny = 0;
		while(x>this.colPosPx[nx+1] && nx<4)
		{
			nx++;
		}
		while(y>this.rowPosPx[ny+1] && ny<4)
		{
			ny++;
		}
		return this.keys[this.keyPosMap[nx][ny]];
	}

	@Override public NumpadKey getKey(int index)
	{
		if(index >=0 && index < this.keys.length)
		{
			return this.keys[index];
		}
		return null;
	}

	@Override public int countKey()
	{
		return this.keys.length;
	}

	@Override public int getMinWidth()
	{
		return this.width;
	}

	@Override public int getMinHeight()
	{
		return this.height;
	}

	@Override public void setConstraint(int w, int h)
	{
		this.width = w;
		this.height = h;		
		for(int i=0 ; i<5 ; i++)
		{
			this.colPosPx[i] = (int)(this.colPos[i]*(float)w);
			this.rowPosPx[i] = (int)(this.rowPos[i]*(float)h);
		}
	}

	@Override public int getWidth()
	{
		return width;
	}

	@Override public int getHeight()
	{
		return height;
	}

}
