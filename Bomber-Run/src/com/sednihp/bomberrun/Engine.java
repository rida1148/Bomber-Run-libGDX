package com.sednihp.bomberrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.sednihp.bomberrun.Screens.GameScreen;
import com.sednihp.bomberrun.Screens.MenuScreen;
import com.sednihp.bomberrun.Screens.SplashScreen;

public class Engine extends Game {

	private int width = 800, height = 480;
	
	SplashScreen splashScr;
	MenuScreen menuScr;
	GameScreen gameScr;
	
	public Engine() 
	{		
	}
	
	@Override
	public void create() 
	{
		splashScr = new SplashScreen(this);
		menuScr = new MenuScreen(this);
		gameScr = new GameScreen(this);
		setScreen(getSplashScr());		
	}

	public SplashScreen getSplashScr()
	{
		return splashScr;
	}
	
	public MenuScreen getMenuScr()
	{
		return menuScr;
	}
	
	public GameScreen getGameScr()
	{
		return gameScr;
	}
	
	@Override
    public void resize(int width, int height) 
	{
        super.resize( width, height );
        this.width = width;
        this.height = height;
    }
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void setScreen(Screen screen)
    {
        super.setScreen( screen );
    }

    @Override
    public void dispose() 
    {
        super.dispose();
        
        gameScr.dispose();
        menuScr.dispose();
        splashScr.dispose();
    }
}
