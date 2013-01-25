package com.sednihp.bomberrun.Entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Plane extends Rectangle {

	private static final long serialVersionUID = 1L;
	private int speed = 70;
	private boolean parking = false;
	private boolean landed = false;
	private boolean parked = false;
	private Vector2 direction;

	public Plane(final int scrHeight) {
		super(0, 0, 84, 39);
		super.setX(-width);
		super.setY(scrHeight - height - 1);
		this.direction = new Vector2(1,0);
	}
	
	public boolean isParked() {
		return parked;
	}
	
	public void setToPark() {
		parking = true;
	}
	
	public void move(final float dTime, final int scrWidth, final int groundLevel)
	{
		if(!parking) 
		{
			super.x += direction.x * speed * dTime;
			
			if(super.x > scrWidth)
			{
				super.x = -width;
				super.y -= 10;
				speed += 3;
			}
		}
		else 
		{
			speed = 120;
			int landingX = scrWidth - 4*(int)super.width;
			int parkingX = scrWidth - 2*(int)super.width;
			int parkingY = groundLevel;
			
			direction.nor();
			
			if(super.x >= scrWidth / 2) 
			{
				super.x += direction.x * speed * dTime;
				
				if(super.x > scrWidth) 
				{
					super.x = -width;
					
					direction.x = landingX - super.x;
					direction.y = super.y - parkingY;
					
					direction.nor();
				}
			}
			else
			{
				super.x += direction.x * speed * dTime;
			}
			
			super.y -= direction.y * speed * dTime;
			
			if(!landed && super.x >= landingX && super.y <= parkingY)
			{
				landed = true;
				direction.set(parkingX - super.x, 0);
			}
			else if(landed && super.x >= parkingX)
			{
				parked = true;
			}
			
		}
	}
	
	public void hasCrashed() {
		speed = 0;
	}
}
