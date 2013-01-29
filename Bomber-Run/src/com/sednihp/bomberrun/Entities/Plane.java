package com.sednihp.bomberrun.Entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Plane extends Rectangle {

	private static final long serialVersionUID = 1L;
	private int speed = 60, speedIncrease, stepDown;
	private Vector2 direction;
	private enum PlaneState {FLYING, PARKING, LANDED, PARKED, CRASHED};
	private PlaneState currentState = PlaneState.FLYING;
	private float landingX = 0, parkingX = 0;

	public Plane(final int scrHeight, final int level)
	{
		super(0, 0, 84, 39);
		super.setX(-width);
		super.setY(scrHeight - height - 1);
		this.direction = new Vector2(1,0);
		
		speedIncrease = level;
		stepDown = 10;
	}
	
	//only set the plane to park if it is flying
	public void setToPark() 
	{
		if(currentState == PlaneState.FLYING)
		{
			currentState = PlaneState.PARKING;
		}
	}
	
	public boolean isParking()
	{
		return currentState == PlaneState.PARKING;
	}
	
	public boolean isParked() 
	{		
		return currentState == PlaneState.PARKED;
	}
	
	public void setToCrashed() 
	{
		speed = 0;
		currentState = PlaneState.CRASHED;
	}
	
	public boolean hasCrashed() 
	{
		return currentState == PlaneState.CRASHED;
	}
	
	public void move(final float dTime, final int scrWidth, final float groundLevel)
	{
		//if the plane is flying move it across the screen
		//when it clears the RHS wrap round to the LHS, move down and speed up
		if(currentState == PlaneState.FLYING) 
		{
			super.x += direction.x * speed * dTime;
			
			if(super.x > scrWidth)
			{
				super.x = -width;
				super.y -= stepDown;
				speed += speedIncrease;
			}
		}
		//if the plane is no longer flying but is parking or landed
		else
		{
			speed = 140;
			
			direction.nor();
			
			super.x += direction.x * speed * dTime;
			
			//only set the plane's vector up to park when it has come back round to the LHS of the screen
			if(super.x > scrWidth) 
			{
				super.x = -width;
				
				landingX = scrWidth - 4*super.width;
				parkingX = scrWidth - 2*super.width;
				
				direction.x = landingX - super.x;
				direction.y = super.y - groundLevel;
				
				direction.nor();
			}

			super.y -= direction.y * speed * dTime;
			
			//if the plane is parking and has reached the ground set it to landed
			if(currentState == PlaneState.PARKING && super.y <= groundLevel)
			{
				currentState = PlaneState.LANDED;
				direction.set(1, 0);
			}
			//if the plane has landed and reached its parking spot, stop it
			else if(currentState == PlaneState.LANDED && super.x >= parkingX)
			{
				currentState = PlaneState.PARKED;
				speed = 0;
			}
		}
	}
}
