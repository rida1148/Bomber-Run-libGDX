package com.sednihp.bomberrun.Entities;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;

public class Cloud extends Rectangle {

	private static final long serialVersionUID = 3L;
	private int direction;
	private final int speed;

	public Cloud(final int scrWidth, final int scrHeight, final float ground) 
	{
		super(0, 0, 150, 100);
		
		Random rand = new Random();
		switch(rand.nextInt(2)) 
		{
			case 0: super.setX(0);
					direction = 1;
					break;
			case 1: super.setX(scrWidth);
					direction = -1;
					break;
		}
		
		float y = rand.nextFloat();
		super.setY((y * (scrHeight-ground-this.height)) + ground);
		
		speed = rand.nextInt(30) + 40;
	}
	
	public void move(final float dTime, final int scrWidth) {
		super.x += direction * speed * dTime;
		
		if(super.x > scrWidth || super.x + super.width < 0) {
			direction *= -1;
		}
	}
}
