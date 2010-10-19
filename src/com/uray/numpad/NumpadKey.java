package com.uray.numpad;

public class NumpadKey 
{
	public class NumpadKeyValue
	{
		public String label;
		public String value;
	}
	public class NumpadKeyMap
	{
		public NumpadKeyValue longPressMap;
		public NumpadKeyValue defaultTapMap;
		public NumpadKeyValue shiftTapMap;
		public NumpadKeyValue altTapMap;
	}

	private float posX;
	private float posY;
	private float width;
	private float height;
	private int state;
	private int id;
	
	NumpadKey(float x,float y,float w, float h)
	{
		this.setPosX(x);
		this.setPosY(y);
		this.setWidth(w);
		this.setHeight(h);
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	private void setState(int state)
	{
		this.state = state;
	}

	private int getState()
	{
		return state;
	}

	private void setHeight(float height)
	{
		this.height = height;
	}

	private float getHeight()
	{
		return height;
	}

	private void setWidth(float width)
	{
		this.width = width;
	}

	private float getWidth()
	{
		return width;
	}

	private void setPosY(float posY)
	{
		this.posY = posY;
	}

	private float getPosY()
	{
		return posY;
	}

	private void setPosX(float posX)
	{
		this.posX = posX;
	}

	private float getPosX()
	{
		return posX;
	}
}
