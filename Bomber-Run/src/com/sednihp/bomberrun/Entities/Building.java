package com.sednihp.bomberrun.Entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;


public class Building extends Rectangle {

	private static final long serialVersionUID = 2L;
	private final int storeyHeight = 20;
	private final int spacing = 7;
	private int numStoreys;
	private Color color;
	
	public Building(int numBuildings, int count, int scrWidth) {
		super(0, 30, 25, 0);
		
		float buildingWidth = this.width + spacing;
		float farLeftX = (scrWidth - numBuildings*buildingWidth) / 2;
		this.setX(farLeftX + count*buildingWidth);
		
		Random rand = new Random();
		numStoreys = rand.nextInt(15) + 1;
		super.setHeight(numStoreys * storeyHeight);
		
		color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getScore() { 
		return numStoreys;
	}
}
