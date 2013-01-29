package com.sednihp.bomberrun.Entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;


public class Building extends Rectangle 
{

	private static final long serialVersionUID = 2L;
	private final int storeyHeight = 20;
	private final int spacing = 7;
	private int numStoreys, hitpoints, score;
	private Color color;
	private boolean destroyed = false;

	//numBuildings tells how many buildings created in a group
	//count is which number building of the group we have reached
	//ground is where the groundLine is
	//bgColor is the sky colour to ensure the building is visible
	public Building(final int numBuildings, 
					final int count, 
					final int scrWidth, 
					final float ground, 
					final int maxStoreys) 
	{
		super(0, ground, 25, 0);
		
		float buildingWidth = this.width + spacing;
		float farLeftX = (scrWidth - numBuildings*buildingWidth) / 2;
		this.setX(farLeftX + count*buildingWidth);
		
		Random rand = new Random();
		numStoreys = rand.nextInt(maxStoreys) + 1;
		super.setHeight(numStoreys * storeyHeight);
		score = numStoreys;
		
		if(numStoreys > 15)
		{
			hitpoints = 2;
		}
		else
		{
			hitpoints = 1;
		}
		
		color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
	}
	
	public void hitByBomb()
	{
		--hitpoints;
		
		if(hitpoints == 0)
		{
			destroyed = true;
		}
		else
		{
			numStoreys /= 2;
			super.setHeight(numStoreys * storeyHeight);			
		}
	}
	
	public boolean isDestroyed()
	{
		return destroyed;
	}
	
	public Color getColor() 
	{
		return color;
	}
	
	public int getScore() 
	{ 
		return score;
	}
}
