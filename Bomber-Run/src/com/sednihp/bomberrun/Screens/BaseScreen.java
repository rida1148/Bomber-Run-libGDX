package com.sednihp.bomberrun.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.sednihp.bomberrun.Engine;

public class BaseScreen implements Screen {

	protected Engine engine;
	protected final float buttonWidth = 300f, buttonHeight = 60f, buttonSpacing = 20f;
	protected final Color bgColor;
	private FPSLogger logger;
	
	public BaseScreen(Engine e) 
	{
		this.engine = e;
		bgColor = new Color(0.586f, 0.781f, 1.0f, 1);
		logger = new FPSLogger();
	}
	
	@Override
	public void render(float dTime) 
	{
		Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		logger.log();
	}

	@Override
	public void resize(int width, int height) 
	{
	}

	@Override
	public void show() 
	{
	}

	@Override
	public void hide() 
	{		
	}

	@Override
	public void pause() 
	{
	}

	@Override
	public void resume() 
	{
	}

	@Override
	public void dispose()
	{
	}
}
