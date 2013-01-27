package com.sednihp.bomberrun.Screens;

import com.badlogic.gdx.Screen;
import com.sednihp.bomberrun.Engine;

public class BaseScreen implements Screen {

	protected Engine engine;
	
	public BaseScreen(Engine e) 
	{
		this.engine = e;
	}
	
	@Override
	public void render(float dTime) 
	{
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
		dispose();
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
