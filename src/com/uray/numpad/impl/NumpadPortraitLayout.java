package com.uray.numpad.impl;

import com.uray.numpad.NumpadKey;
import com.uray.numpad.NumpadKeyValue;
import com.uray.numpad.NumpadKeymap;
import com.uray.numpad.NumpadLayout;
import com.uray.numpad.NumpadRenderer;
import com.uray.numpad.glyph.*;

import android.view.ViewGroup;

public class NumpadPortraitLayout extends NumpadLayout
{
	private int colPosPx[] = new int[5];
	private int rowPosPx[] = new int[5];
	private float colPos[] = new float[5];
	private float rowPos[] = new float[5];
	private int keyPosMap[][] = new int[4][4];
	public NumpadPortraitLayout(ViewGroup parentView,NumpadRenderer renderer)
	{
		super(parentView,renderer);
		
		this.height = 240;
		this.width = 320;
		this.colPos[0] = 0.0000f;
		this.colPos[1] = 0.2700f;
		this.colPos[2] = 0.5400f;
		this.colPos[3] = 0.8100f;
		this.colPos[4] = 1.0000f;
		
		this.rowPos[0] = 0.0000f;
		this.rowPos[1] = 0.2677f;
		this.rowPos[2] = 0.5333f;
		this.rowPos[3] = 0.8000f;
		this.rowPos[4] = 1.0000f;
		
		NumpadGlyphText glyphText      = new NumpadGlyphText();
		NumpadGlyphTab  glyphTab       = new NumpadGlyphTab();
		NumpadGlyphSpace glyphSpace    = new NumpadGlyphSpace();
		NumpadGlyphReset glyphReset    = new NumpadGlyphReset();
		NumpadGlyphShift shiftSymbol   = new NumpadGlyphShift();
		NumpadGlyphCapslock capsSymbol = new NumpadGlyphCapslock();
		NumpadGlyphAlt altSymbol       = new NumpadGlyphAlt();
		NumpadGlyphNumlock numSymbol   = new NumpadGlyphNumlock();
		NumpadGlyphReturn returnSymbol = new NumpadGlyphReturn();
		NumpadGlyphClose closeSymbol   = new NumpadGlyphClose();
		NumpadGlyphDel delSymbol       = new NumpadGlyphDel();
		
		this.keys = new NumpadKey[15];	
		
		//---------------------------------------------- KEY[0] , numpad 0
		this.keys[0] = new NumpadKey(0,parentView.getContext(),renderer,this,
				                     this.colPos[1], this.rowPos[3], 
				                     this.colPos[3]-this.colPos[1], 
				                     this.rowPos[4]-this.rowPos[3]);
		this.keys[0].edgeFlag  = NumpadKey.edgeRight;
		this.keyPosMap[1][3] = 0;
		this.keyPosMap[2][3] = 0;
		NumpadKeymap keymap0     = new NumpadKeymap();
		keymap0.altTapMap        = new NumpadKeyValue[1];
		keymap0.defaultTapMap    = new NumpadKeyValue[3];
		keymap0.shiftTapMap      = new NumpadKeyValue[1];
		keymap0.longPressMap     = new NumpadKeyValue( glyphText, 0.035f,0.700f,'0', NumpadKeymap.keyCodeChars);		
		keymap0.altTapMap[0]     = new NumpadKeyValue( glyphTab,  0.835f,0.625f,'\t',NumpadKeymap.keyCodeChars);
		keymap0.defaultTapMap[0] = new NumpadKeyValue( glyphSpace,0.550f,0.425f,' ', NumpadKeymap.keyCodeChars);		
		keymap0.defaultTapMap[1] = new NumpadKeyValue( glyphText, 0.850f,0.375f,',', NumpadKeymap.keyCodeChars);	
		keymap0.defaultTapMap[2] = new NumpadKeyValue( glyphText, 0.900f,0.375f,'.', NumpadKeymap.keyCodeChars);
		keymap0.shiftTapMap[0]   = keymap0.defaultTapMap[0];
		this.keys[0].keymap = keymap0;
		
		//----------------------------------------------- KEY[1] , numpad 1
		this.keys[1] = new NumpadKey(1,parentView.getContext(),renderer,this,
				                     this.colPos[0], this.rowPos[0], 
				                     this.colPos[1]-this.colPos[0], 
				                     this.rowPos[1]-this.rowPos[0]);
		this.keys[1].edgeFlag  = NumpadKey.edgeBottom | NumpadKey.edgeRight | NumpadKey.edgeTop;
		this.keyPosMap[0][0] = 1;
		NumpadKeymap keymap1     = new NumpadKeymap();
		keymap1.defaultTapMap    = new NumpadKeyValue[1];
		keymap1.shiftTapMap       = new NumpadKeyValue[1];
		keymap1.altTapMap        = new NumpadKeyValue[3];
		keymap1.longPressMap     = new NumpadKeyValue(glyphText, 0.05f,0.700f,'1', NumpadKeymap.keyCodeChars);
		keymap1.defaultTapMap[0] = new NumpadKeyValue(glyphReset,0.60f,0.375f,(char)0,NumpadKeymap.keyCodeTapEnd);
		keymap1.altTapMap[0]     = new NumpadKeyValue(glyphText, 0.50f,0.900f,'€', NumpadKeymap.keyCodeChars);
		keymap1.altTapMap[1]     = new NumpadKeyValue(glyphText, 0.65f,0.900f,'£', NumpadKeymap.keyCodeChars);		
		keymap1.altTapMap[2]     = new NumpadKeyValue(glyphText, 0.80f,0.900f,'¥', NumpadKeymap.keyCodeChars);	
		keymap1.shiftTapMap[0]   = new NumpadKeyValue(null     , 0.05f,0.700f,'1', NumpadKeymap.keyCodeChars);
		this.keys[1].keymap = keymap1;
		
		//---------------------------------------------- KEY[2] , numpad 2
		this.keys[2] = new NumpadKey(2,parentView.getContext(),renderer,this,
									 this.colPos[1], this.rowPos[0], 
									 this.colPos[2]-this.colPos[1], 
									 this.rowPos[1]-this.rowPos[0]);
		this.keys[2].edgeFlag  = NumpadKey.edgeBottom | NumpadKey.edgeRight | NumpadKey.edgeTop;
		this.keyPosMap[1][0] = 2;
		NumpadKeymap keymap2 = new NumpadKeymap();
		keymap2.defaultTapMap    = new NumpadKeyValue[3];
		keymap2.altTapMap        = new NumpadKeyValue[3];		
		keymap2.shiftTapMap	     = new NumpadKeyValue[3];
		keymap2.longPressMap     = new NumpadKeyValue(glyphText,0.050f,0.700f,'2',NumpadKeymap.keyCodeChars);		
		keymap2.altTapMap[0]     = new NumpadKeyValue(glyphText,0.500f,0.900f,'$',NumpadKeymap.keyCodeChars);
		keymap2.altTapMap[1]     = new NumpadKeyValue(glyphText,0.650f,0.900f,'#',NumpadKeymap.keyCodeChars);		
		keymap2.altTapMap[2]     = new NumpadKeyValue(glyphText,0.800f,0.900f,'%',NumpadKeymap.keyCodeChars);			
		keymap2.defaultTapMap[0] = new NumpadKeyValue(null,     0.500f,0.375f,'a',NumpadKeymap.keyCodeChars);		
		keymap2.defaultTapMap[1] = new NumpadKeyValue(null,     0.650f,0.375f,'b',NumpadKeymap.keyCodeChars);		
		keymap2.defaultTapMap[2] = new NumpadKeyValue(null,     0.800f,0.375f,'c',NumpadKeymap.keyCodeChars);		
		keymap2.shiftTapMap[0]   = new NumpadKeyValue(glyphText,0.500f,0.375f,'A',NumpadKeymap.keyCodeChars);
		keymap2.shiftTapMap[1]   = new NumpadKeyValue(glyphText,0.650f,0.375f,'B',NumpadKeymap.keyCodeChars);
		keymap2.shiftTapMap[2]   = new NumpadKeyValue(glyphText,0.800f,0.375f,'C',NumpadKeymap.keyCodeChars);		
		this.keys[2].keymap = keymap2;

		//---------------------------------------------- KEY[3] , numpad 3
		this.keys[3] = new NumpadKey(3,parentView.getContext(),renderer,this,
				                     this.colPos[2], this.rowPos[0], 
				                     this.colPos[3] -this.colPos[2], 
				                     this.rowPos[1] -this.rowPos[0]);
		this.keys[3].edgeFlag  = NumpadKey.edgeBottom | NumpadKey.edgeRight | NumpadKey.edgeTop;
		this.keyPosMap[2][0] = 3;
		NumpadKeymap keymap3 = new NumpadKeymap();
		keymap3.defaultTapMap    = new NumpadKeyValue[3];
		keymap3.altTapMap        = new NumpadKeyValue[3];		
		keymap3.shiftTapMap	     = new NumpadKeyValue[3];
		keymap3.longPressMap     = new NumpadKeyValue(glyphText,0.050f,0.700f,'3',NumpadKeymap.keyCodeChars);		
		keymap3.altTapMap[0]     = new NumpadKeyValue(glyphText,0.500f,0.900f,'&',NumpadKeymap.keyCodeChars);
		keymap3.altTapMap[1]     = new NumpadKeyValue(glyphText,0.650f,0.900f,'*',NumpadKeymap.keyCodeChars);		
		keymap3.altTapMap[2]     = new NumpadKeyValue(glyphText,0.800f,0.900f,'^',NumpadKeymap.keyCodeChars);			
		keymap3.defaultTapMap[0] = new NumpadKeyValue(null,     0.450f,0.375f,'d',NumpadKeymap.keyCodeChars);		
		keymap3.defaultTapMap[1] = new NumpadKeyValue(null,     0.620f,0.375f,'e',NumpadKeymap.keyCodeChars);		
		keymap3.defaultTapMap[2] = new NumpadKeyValue(null,     0.770f,0.375f,'f',NumpadKeymap.keyCodeChars);
		keymap3.shiftTapMap[0]   = new NumpadKeyValue(glyphText,0.450f,0.375f,'D',NumpadKeymap.keyCodeChars);
		keymap3.shiftTapMap[1]   = new NumpadKeyValue(glyphText,0.620f,0.375f,'E',NumpadKeymap.keyCodeChars);
		keymap3.shiftTapMap[2]   = new NumpadKeyValue(glyphText,0.770f,0.375f,'F',NumpadKeymap.keyCodeChars);
		this.keys[3].keymap = keymap3;

		//---------------------------------------------- KEY[4] , numpad 4
		this.keys[4] = new NumpadKey(4,parentView.getContext(),renderer,this,
									 this.colPos[0], this.rowPos[1], 
									 this.colPos[1]-this.colPos[0], 
									 this.rowPos[2]-this.rowPos[1]);
		this.keys[4].edgeFlag  = NumpadKey.edgeBottom | NumpadKey.edgeRight;
		this.keyPosMap[0][1] = 4; 
		NumpadKeymap keymap4 = new NumpadKeymap();
		keymap4.defaultTapMap    = new NumpadKeyValue[3];
		keymap4.altTapMap        = new NumpadKeyValue[3];		
		keymap4.shiftTapMap	     = new NumpadKeyValue[3];
		keymap4.longPressMap     = new NumpadKeyValue(glyphText,0.050f,0.700f,'4',NumpadKeymap.keyCodeChars);		
		keymap4.altTapMap[0]     = new NumpadKeyValue(glyphText,0.500f,0.900f,'\\',NumpadKeymap.keyCodeChars);
		keymap4.altTapMap[1]     = new NumpadKeyValue(glyphText,0.650f,0.900f,'<',NumpadKeymap.keyCodeChars);		
		keymap4.altTapMap[2]     = new NumpadKeyValue(glyphText,0.800f,0.900f,';',NumpadKeymap.keyCodeChars);			
		keymap4.shiftTapMap[0]   = new NumpadKeyValue(glyphText,0.500f,0.375f,'G',NumpadKeymap.keyCodeChars);		
		keymap4.shiftTapMap[1]   = new NumpadKeyValue(glyphText,0.675f,0.375f,'H',NumpadKeymap.keyCodeChars);		
		keymap4.shiftTapMap[2]   = new NumpadKeyValue(glyphText,0.850f,0.375f,'I',NumpadKeymap.keyCodeChars);	
		keymap4.defaultTapMap[0] = new NumpadKeyValue(null     ,0.500f,0.375f,'g',NumpadKeymap.keyCodeChars);		
		keymap4.defaultTapMap[1] = new NumpadKeyValue(null     ,0.675f,0.375f,'h',NumpadKeymap.keyCodeChars);		
		keymap4.defaultTapMap[2] = new NumpadKeyValue(null     ,0.850f,0.375f,'i',NumpadKeymap.keyCodeChars);		
		this.keys[4].keymap = keymap4;

		//---------------------------------------------- KEY[5] , numpad 5
		this.keys[5] = new NumpadKey(5,parentView.getContext(),renderer,this,
									 this.colPos[1], this.rowPos[1], 
									 this.colPos[2]-this.colPos[1], 
									 this.rowPos[2]-this.rowPos[1]);
		this.keys[5].edgeFlag  = NumpadKey.edgeBottom | NumpadKey.edgeRight;
		this.keyPosMap[1][1] = 5;
		NumpadKeymap keymap5 = new NumpadKeymap();
		keymap5.defaultTapMap    = new NumpadKeyValue[3];
		keymap5.altTapMap        = new NumpadKeyValue[3];		
		keymap5.shiftTapMap	     = new NumpadKeyValue[3];
		keymap5.longPressMap = new NumpadKeyValue(glyphText,0.050f,0.700f,'5',NumpadKeymap.keyCodeChars);		
		keymap5.altTapMap[0]     = new NumpadKeyValue(glyphText,0.500f,0.900f,'+' ,NumpadKeymap.keyCodeChars);
		keymap5.altTapMap[1]     = new NumpadKeyValue(glyphText,0.650f,0.900f,'>' ,NumpadKeymap.keyCodeChars);		
		keymap5.altTapMap[2]     = new NumpadKeyValue(glyphText,0.800f,0.900f,'_' ,NumpadKeymap.keyCodeChars);			
		keymap5.defaultTapMap[0] = new NumpadKeyValue(null     ,0.550f,0.375f,'j' ,NumpadKeymap.keyCodeChars);		
		keymap5.defaultTapMap[1] = new NumpadKeyValue(null     ,0.650f,0.375f,'k' ,NumpadKeymap.keyCodeChars);		
		keymap5.defaultTapMap[2] = new NumpadKeyValue(null     ,0.815f,0.375f,'l' ,NumpadKeymap.keyCodeChars);	
		keymap5.shiftTapMap[0]   = new NumpadKeyValue(glyphText,0.550f,0.375f,'J' ,NumpadKeymap.keyCodeChars);		
		keymap5.shiftTapMap[1]   = new NumpadKeyValue(glyphText,0.650f,0.375f,'K' ,NumpadKeymap.keyCodeChars);		
		keymap5.shiftTapMap[2]   = new NumpadKeyValue(glyphText,0.815f,0.375f,'L' ,NumpadKeymap.keyCodeChars);		
		this.keys[5].keymap = keymap5;
		
		//---------------------------------------------- KEY[6] , numpad 6
		this.keys[6] = new NumpadKey(6,parentView.getContext(),renderer,this,
									this.colPos[2] ,this.rowPos[1], 
									this.colPos[3]-this.colPos[2], 
									this.rowPos[2]-this.rowPos[1]);
		this.keys[6].edgeFlag  = NumpadKey.edgeBottom | NumpadKey.edgeRight;
		this.keyPosMap[2][1] = 6;
		NumpadKeymap keymap6 = new NumpadKeymap();
		keymap6.defaultTapMap    = new NumpadKeyValue[3];
		keymap6.altTapMap        = new NumpadKeyValue[3];		
		keymap6.shiftTapMap	     = new NumpadKeyValue[3];
		keymap6.longPressMap     = new NumpadKeyValue(glyphText,0.050f,0.700f,'6',NumpadKeymap.keyCodeChars);		
		keymap6.altTapMap[0]     = new NumpadKeyValue(glyphText,0.500f,0.900f,'=',NumpadKeymap.keyCodeChars);
		keymap6.altTapMap[1]     = new NumpadKeyValue(glyphText,0.650f,0.900f,'`',NumpadKeymap.keyCodeChars);		
		keymap6.altTapMap[2]     = new NumpadKeyValue(glyphText,0.800f,0.900f,'|',NumpadKeymap.keyCodeChars);			
		keymap6.defaultTapMap[0] = new NumpadKeyValue(null     ,0.425f,0.375f,'m',NumpadKeymap.keyCodeChars);		
		keymap6.defaultTapMap[1] = new NumpadKeyValue(null     ,0.605f,0.375f,'n',NumpadKeymap.keyCodeChars);		
		keymap6.defaultTapMap[2] = new NumpadKeyValue(null     ,0.765f,0.375f,'o',NumpadKeymap.keyCodeChars);	
		keymap6.shiftTapMap[0]   = new NumpadKeyValue(glyphText,0.410f,0.375f,'M',NumpadKeymap.keyCodeChars);		
		keymap6.shiftTapMap[1]   = new NumpadKeyValue(glyphText,0.600f,0.375f,'N',NumpadKeymap.keyCodeChars);		
		keymap6.shiftTapMap[2]   = new NumpadKeyValue(glyphText,0.765f,0.375f,'O',NumpadKeymap.keyCodeChars);		
		this.keys[6].keymap = keymap6;
		
		//---------------------------------------------- KEY[7] , numpad 7
		this.keys[7] = new NumpadKey(7,parentView.getContext(),renderer,this,
				                     this.colPos[0], this.rowPos[2], 
				                     this.colPos[1]-this.colPos[0], 
				                     this.rowPos[3]-this.rowPos[2]);
		this.keys[7].edgeFlag  = NumpadKey.edgeBottom | NumpadKey.edgeRight;
		this.keyPosMap[0][2] = 7; 
		NumpadKeymap keymap7 = new NumpadKeymap();
		keymap7.defaultTapMap    = new NumpadKeyValue[4];
		keymap7.altTapMap        = new NumpadKeyValue[3];		
		keymap7.shiftTapMap	     = new NumpadKeyValue[4];
		keymap7.longPressMap     = new NumpadKeyValue(glyphText,0.050f,0.700f,'7', NumpadKeymap.keyCodeChars);		
		keymap7.altTapMap[0]     = new NumpadKeyValue(glyphText,0.500f,0.900f,'\'',NumpadKeymap.keyCodeChars);
		keymap7.altTapMap[1]     = new NumpadKeyValue(glyphText,0.650f,0.900f,'(' ,NumpadKeymap.keyCodeChars);		
		keymap7.altTapMap[2]     = new NumpadKeyValue(glyphText,0.800f,0.900f,'~' ,NumpadKeymap.keyCodeChars);			
		keymap7.defaultTapMap[0] = new NumpadKeyValue(null     ,0.350f,0.375f,'p' ,NumpadKeymap.keyCodeChars);		
		keymap7.defaultTapMap[1] = new NumpadKeyValue(null     ,0.500f,0.375f,'q' ,NumpadKeymap.keyCodeChars);		
		keymap7.defaultTapMap[2] = new NumpadKeyValue(null     ,0.675f,0.375f,'r' ,NumpadKeymap.keyCodeChars);				
		keymap7.defaultTapMap[3] = new NumpadKeyValue(null     ,0.825f,0.375f,'s' ,NumpadKeymap.keyCodeChars);			
		keymap7.shiftTapMap[0]   = new NumpadKeyValue(glyphText,0.350f,0.375f,'P' ,NumpadKeymap.keyCodeChars);		
		keymap7.shiftTapMap[1]   = new NumpadKeyValue(glyphText,0.500f,0.375f,'Q' ,NumpadKeymap.keyCodeChars);		
		keymap7.shiftTapMap[2]   = new NumpadKeyValue(glyphText,0.675f,0.375f,'R' ,NumpadKeymap.keyCodeChars);				
		keymap7.shiftTapMap[3]   = new NumpadKeyValue(glyphText,0.825f,0.375f,'S' ,NumpadKeymap.keyCodeChars);			
		this.keys[7].keymap = keymap7;
		
		//---------------------------------------------- KEY[8] , numpad 8
		this.keys[8] = new NumpadKey(8,parentView.getContext(),renderer,this,
									 this.colPos[1], this.rowPos[2], 
									 this.colPos[2]-this.colPos[1], 
									 this.rowPos[3]-this.rowPos[2]);
		this.keys[8].edgeFlag  = NumpadKey.edgeBottom | NumpadKey.edgeRight;
		this.keyPosMap[1][2] = 8;
		NumpadKeymap keymap8 = new NumpadKeymap();
		keymap8.defaultTapMap    = new NumpadKeyValue[3];
		keymap8.altTapMap        = new NumpadKeyValue[3];		
		keymap8.shiftTapMap	     = new NumpadKeyValue[3];
		keymap8.longPressMap     = new NumpadKeyValue(glyphText,0.050f,0.700f,'8',NumpadKeymap.keyCodeChars);		
		keymap8.altTapMap[0]     = new NumpadKeyValue(glyphText,0.50f,0.900f,'\'',NumpadKeymap.keyCodeChars);
		keymap8.altTapMap[1]     = new NumpadKeyValue(glyphText,0.65f,0.900f,')' ,NumpadKeymap.keyCodeChars);		
		keymap8.altTapMap[2]     = new NumpadKeyValue(glyphText,0.80f,0.900f,'[' ,NumpadKeymap.keyCodeChars);			
		keymap8.defaultTapMap[0] = new NumpadKeyValue(null     ,0.45f,0.375f,'t' ,NumpadKeymap.keyCodeChars);		
		keymap8.defaultTapMap[1] = new NumpadKeyValue(null     ,0.60f,0.375f,'u' ,NumpadKeymap.keyCodeChars);		
		keymap8.defaultTapMap[2] = new NumpadKeyValue(null     ,0.80f,0.375f,'v' ,NumpadKeymap.keyCodeChars);	
		keymap8.shiftTapMap[0]   = new NumpadKeyValue(glyphText,0.45f,0.375f,'T' ,NumpadKeymap.keyCodeChars);		
		keymap8.shiftTapMap[1]   = new NumpadKeyValue(glyphText,0.60f,0.375f,'U' ,NumpadKeymap.keyCodeChars);		
		keymap8.shiftTapMap[2]   = new NumpadKeyValue(glyphText,0.80f,0.375f,'V' ,NumpadKeymap.keyCodeChars);		
		this.keys[8].keymap = keymap8;
		
		//---------------------------------------------- KEY[9] , numpad 9
		this.keys[9] = new NumpadKey(9,parentView.getContext(),renderer,this,
				                     this.colPos[2] ,this.rowPos[2], 
				                     this.colPos[3]-this.colPos[2], 
				                     this.rowPos[3]-this.rowPos[2]);	
		this.keys[9].edgeFlag  = NumpadKey.edgeBottom | NumpadKey.edgeRight;
		this.keyPosMap[2][2] = 9;
		NumpadKeymap keymap9 = new NumpadKeymap();
		keymap9.defaultTapMap    = new NumpadKeyValue[4];
		keymap9.altTapMap        = new NumpadKeyValue[3];		
		keymap9.shiftTapMap	     = new NumpadKeyValue[4];
		keymap9.longPressMap     = new NumpadKeyValue(glyphText,0.050f,0.700f,'9',NumpadKeymap.keyCodeChars);		
		keymap9.altTapMap[0]     = new NumpadKeyValue(glyphText,0.500f,0.900f,':' ,NumpadKeymap.keyCodeChars);
		keymap9.altTapMap[1]     = new NumpadKeyValue(glyphText,0.650f,0.900f,'{' ,NumpadKeymap.keyCodeChars);		
		keymap9.altTapMap[2]     = new NumpadKeyValue(glyphText,0.800f,0.900f,']' ,NumpadKeymap.keyCodeChars);			
		keymap9.defaultTapMap[0] = new NumpadKeyValue(null     ,0.300f,0.375f,'w' ,NumpadKeymap.keyCodeChars);		
		keymap9.defaultTapMap[1] = new NumpadKeyValue(null     ,0.525f,0.375f,'x' ,NumpadKeymap.keyCodeChars);		
		keymap9.defaultTapMap[2] = new NumpadKeyValue(null     ,0.675f,0.375f,'y' ,NumpadKeymap.keyCodeChars);				
		keymap9.defaultTapMap[3] = new NumpadKeyValue(null     ,0.835f,0.375f,'z' ,NumpadKeymap.keyCodeChars);	
		keymap9.shiftTapMap[0]   = new NumpadKeyValue(glyphText,0.300f,0.375f,'W' ,NumpadKeymap.keyCodeChars);		
		keymap9.shiftTapMap[1]   = new NumpadKeyValue(glyphText,0.525f,0.375f,'X' ,NumpadKeymap.keyCodeChars);		
		keymap9.shiftTapMap[2]   = new NumpadKeyValue(glyphText,0.675f,0.375f,'Y' ,NumpadKeymap.keyCodeChars);				
		keymap9.shiftTapMap[3]   = new NumpadKeyValue(glyphText,0.835f,0.375f,'Z' ,NumpadKeymap.keyCodeChars);			
		this.keys[9].keymap = keymap9;
		
		//---------------------------------------------- KEY[10] , numpad @
		this.keys[10] = new NumpadKey(10,parentView.getContext(),renderer,this,
				                      this.colPos[3], this.rowPos[0], 
				                      this.colPos[4]-this.colPos[3], 
				                      this.rowPos[1]-this.rowPos[0]);
		this.keys[10].edgeFlag = NumpadKey.edgeBottom | NumpadKey.edgeTop;
		this.keyPosMap[3][0] = 10;
		NumpadKeymap keymap10 = new NumpadKeymap();
		keymap10.defaultTapMap    = new NumpadKeyValue[3];
		keymap10.altTapMap        = new NumpadKeyValue[3];		
		keymap10.shiftTapMap	  = new NumpadKeyValue[3];
		keymap10.longPressMap     = new NumpadKeyValue(glyphText,0.050f,0.700f,'@',NumpadKeymap.keyCodeChars);		
		keymap10.altTapMap[0]     = new NumpadKeyValue(glyphText,0.500f,0.900f,'/',NumpadKeymap.keyCodeChars);
		keymap10.altTapMap[1]     = new NumpadKeyValue(glyphText,0.650f,0.900f,'}',NumpadKeymap.keyCodeChars);		
		keymap10.altTapMap[2]     = new NumpadKeyValue(glyphText,0.800f,0.900f,'©',NumpadKeymap.keyCodeChars);			
		keymap10.defaultTapMap[0] = new NumpadKeyValue(null     ,0.60f,0.375f,'?' ,NumpadKeymap.keyCodeChars);		
		keymap10.defaultTapMap[1] = new NumpadKeyValue(null     ,0.75f,0.375f,'!' ,NumpadKeymap.keyCodeChars);		
		keymap10.defaultTapMap[2] = new NumpadKeyValue(null     ,0.85f,0.375f,'-' ,NumpadKeymap.keyCodeChars);	
		keymap10.shiftTapMap[0]   = new NumpadKeyValue(glyphText,0.500f,0.375f,'?' ,NumpadKeymap.keyCodeChars);		
		keymap10.shiftTapMap[1]   = new NumpadKeyValue(glyphText,0.700f,0.375f,'!' ,NumpadKeymap.keyCodeChars);		
		keymap10.shiftTapMap[2]   = new NumpadKeyValue(glyphText,0.775f,0.375f,'-' ,NumpadKeymap.keyCodeChars);			
		this.keys[10].keymap = keymap10;

		//---------------------------------------------- KEY[11] , numpad shift
		this.keys[11] = new NumpadKey(11,parentView.getContext(),renderer,this,
				                      this.colPos[3], this.rowPos[1], 
				                      this.colPos[4]-this.colPos[3], 
				                      this.rowPos[2]-this.rowPos[1]);
		this.keys[11].edgeFlag = NumpadKey.edgeBottom;
		this.keys[11].isToggleKey = true;
		this.keyPosMap[3][1] = 11;
		NumpadKeymap keymap11 = new NumpadKeymap();
		keymap11.defaultTapMap    = new NumpadKeyValue[1];
		keymap11.altTapMap        = new NumpadKeyValue[1];
		keymap11.shiftTapMap	  = new NumpadKeyValue[1];
		keymap11.defaultTapMap[0] = new NumpadKeyValue(shiftSymbol,0.600f,0.375f,(char)0,NumpadKeymap.keyCodeShift);	
		keymap11.altTapMap[0] 	  = keymap11.defaultTapMap[0];
		keymap11.shiftTapMap[0]   = keymap11.defaultTapMap[0];
		keymap11.longPressMap     = new NumpadKeyValue(capsSymbol,0.125f,0.200f,(char)0,NumpadKeymap.keyCodeShiftLock);		
		this.keys[11].keymap = keymap11;
		
		//---------------------------------------------- KEY[12] , numpad numlock/alt
		this.keys[12] = new NumpadKey(12,parentView.getContext(),renderer,this,
				                      this.colPos[3], this.rowPos[2], 
				                      this.colPos[4]-this.colPos[3], 
				                      this.rowPos[3]-this.rowPos[2]);
		this.keys[12].edgeFlag = NumpadKey.edgeBottom;
		this.keys[12].isToggleKey = true;
		this.keyPosMap[3][2] = 12;
		NumpadKeymap keymap12 = new NumpadKeymap();
		keymap12.defaultTapMap    = new NumpadKeyValue[1];
		keymap12.altTapMap        = new NumpadKeyValue[1];
		keymap12.shiftTapMap	  = new NumpadKeyValue[1];
		keymap12.defaultTapMap[0] = new NumpadKeyValue(altSymbol,0.600f,0.200f,(char)0,NumpadKeymap.keyCodeAlt);		
		keymap12.altTapMap[0]     = keymap12.defaultTapMap[0];
		keymap12.shiftTapMap[0]   = keymap12.defaultTapMap[0];
		keymap12.longPressMap     = new NumpadKeyValue(numSymbol,0.125f,0.200f,(char)0,NumpadKeymap.keyCodeNumLock);	
		this.keys[12].keymap = keymap12;
		
		//---------------------------------------------- KEY[13] , numpad close/return
		this.keys[13] = new NumpadKey(13,parentView.getContext(),renderer,this,
				                      this.colPos[3], this.rowPos[3], 
				                      this.colPos[4]-this.colPos[3], 
				                      this.rowPos[4]-this.rowPos[3]);
		this.keys[13].edgeFlag = 0;
		this.keyPosMap[3][3] = 13;	
		NumpadKeymap keymap13 = new NumpadKeymap();
		keymap13.defaultTapMap    = new NumpadKeyValue[1];
		keymap13.altTapMap        = new NumpadKeyValue[1];
		keymap13.shiftTapMap	  = new NumpadKeyValue[1];
		keymap13.defaultTapMap[0] = new NumpadKeyValue(returnSymbol,0.60f,0.400f,(char)0,NumpadKeymap.keyCodeTapReturn);	
		keymap13.altTapMap[0]     = keymap13.defaultTapMap[0];
		keymap13.shiftTapMap[0]   = keymap13.defaultTapMap[0];
		keymap13.longPressMap     = new NumpadKeyValue(closeSymbol, 0.20f,0.375f,(char)0,NumpadKeymap.keyCodeCancel);	
		this.keys[13].keymap = keymap13;
		
		//---------------------------------------------- KEY[14] , numpad del
		this.keys[14] = new NumpadKey(14,parentView.getContext(),renderer,this,
				                      this.colPos[0], this.rowPos[3], 
				                      this.colPos[1]-this.colPos[0], 
				                      this.rowPos[4]-this.rowPos[3]);
		this.keys[14].edgeFlag = NumpadKey.edgeRight;
		this.keyPosMap[0][3] = 14; 
		NumpadKeymap keymap14 = new NumpadKeymap();
		keymap14.defaultTapMap    = new NumpadKeyValue[1];
		keymap14.altTapMap        = new NumpadKeyValue[1];	
		keymap14.shiftTapMap	  = new NumpadKeyValue[1];
		keymap14.defaultTapMap[0] = new NumpadKeyValue(delSymbol,0.60f,0.450f,(char)0,NumpadKeymap.keyCodeDel);	
		keymap14.altTapMap[0]     = keymap14.defaultTapMap[0];
		keymap14.shiftTapMap[0]   = keymap14.defaultTapMap[0];
		keymap14.longPressMap     = keymap14.defaultTapMap[0];
		this.keys[14].keymap = keymap14;
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

}
