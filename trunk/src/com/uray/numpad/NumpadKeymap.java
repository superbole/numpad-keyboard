package com.uray.numpad;


public class NumpadKeymap
{
	public NumpadKeyValue longPressMap;
	public NumpadKeyValue[] defaultTapMap;
	public NumpadKeyValue[] shiftTapMap;
	public NumpadKeyValue[] altTapMap;
	public final static int keyCodeChars      = -1;
	public final static int keyCodeDel        = -2;
	public final static int keyCodeAlt        = -3;
	public final static int keyCodeShift      = -4;
	public final static int keyCodeShiftLock  = -5;
	public final static int keyCodeCancel     = -6;
	public final static int keyCodeNumLock    = -7;
	public final static int keyCodeTapEnd     = -8;
	public final static int keyCodeTapReturn  = -9;
	public NumpadKeymap(){}
}
