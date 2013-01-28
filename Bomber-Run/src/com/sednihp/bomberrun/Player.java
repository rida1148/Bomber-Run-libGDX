package com.sednihp.bomberrun;

public class Player {

	private int levelScore = 0;
	private int overallScore = 0;
	private boolean alive = true;
	
	public Player()
	{
		
	}
	
	public void restartGame()
	{
		levelScore = 0;
		overallScore = 0;
		alive = true;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public void setAlive(boolean status)
	{
		alive = status;
	}
	
	public void addToScore(final int points)
	{
		levelScore += points;
	}
	
	public void completedLevel()
	{
		overallScore += levelScore;
		levelScore = 0;
	}
	
	public int getLevelScore()
	{
		return levelScore;
	}
	
	public int getOverallScore()
	{
		return overallScore;
	}
}
