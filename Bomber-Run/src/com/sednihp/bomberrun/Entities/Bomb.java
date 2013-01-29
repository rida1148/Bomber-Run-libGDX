package com.sednihp.bomberrun.Entities;

import com.badlogic.gdx.math.Rectangle;

public class Bomb extends Rectangle 
{
	private static final long serialVersionUID = 3L;
	private final int speed = 80;
	private boolean offScreen = false;

	public Bomb(final float x, final float y) 
	{
		super(0, y, 7, 19);
		super.setX(x - width/2);
	}
	
	public void move(final float dTime, final float ground) 
	{
		super.y -= speed * dTime;
		
		if(super.y <= ground) 
		{
			offScreen = true;
		}
	}
	
	public boolean isOffScreen() 
	{
		return offScreen;
	}
}
