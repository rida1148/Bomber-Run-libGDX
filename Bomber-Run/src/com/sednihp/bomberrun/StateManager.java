package com.sednihp.bomberrun;

public class StateManager {

	private int currentLevelNumber = 1;
	
	public StateManager()
	{
		
	}
	
	public int getCurrentLevel()
	{
		return currentLevelNumber;
	}
	
	public void setToNextLevel()
	{
		++currentLevelNumber;
	}
	
	public void restartGame()
	{
		currentLevelNumber = 1;
	}
}
