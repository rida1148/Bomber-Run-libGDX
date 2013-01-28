package com.sednihp.bomberrun;

import com.badlogic.gdx.utils.TimeUtils;

public class Timer {

	private long startTime, pausedTime = 0;
	private boolean paused = false, running = false;
	
	public Timer()
	{
		
	}
	
	public boolean isPaused()
	{
		return paused;
	}
	
	public boolean isRunning()
	{
		return running;
	}
	
	public void start()
	{
		pausedTime = 0;
		startTime = TimeUtils.millis();
		paused = false;
		running = true;
	}
	
	public long stop()
	{
		running = false;
		return TimeUtils.millis() - startTime;
	}
	
	public long getTime()
	{
		return pausedTime + (TimeUtils.millis() - startTime);
	}
	
	public long pause()
	{
		pausedTime += getTime();
		paused = true;
		return pausedTime;
	}
	
	public void resume()
	{
		startTime = TimeUtils.millis();
		paused = false;
	}
	
	public void restart()
	{
		pausedTime = 0;
		start();
	}
}
