package com.sednihp.bomberrun.Entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;


public class Building extends Rectangle {

	private static final long serialVersionUID = 2L;
	private final int storeyHeight = 20;
	private int numStoreys;
	private Color color;
	
	public Building(int x) {
		super(x, 30, 25, 0);
		
		Random rand = new Random();
		numStoreys = rand.nextInt(15) + 1;
		super.setHeight(numStoreys * storeyHeight);
		
		color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 0);
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getScore() { 
		return numStoreys;
	}
}
