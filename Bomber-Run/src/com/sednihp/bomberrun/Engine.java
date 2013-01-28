package com.sednihp.bomberrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.sednihp.bomberrun.Screens.GameScreen;
import com.sednihp.bomberrun.Screens.LevelOverScreen;
import com.sednihp.bomberrun.Screens.MenuScreen;
import com.sednihp.bomberrun.Screens.SplashScreen;

public class Engine extends Game {

	private int width = 800, height = 480;
	
	private StateManager stateMan;
	private SplashScreen splashScr;
	private MenuScreen menuScr;
	private GameScreen gameScr;
	private LevelOverScreen lvlOverScr;
	private Player player;
	
	public Engine() 
	{		
	}
	
	@Override
	public void create() 
	{
		stateMan = new StateManager();
		splashScr = new SplashScreen(this);
		menuScr = new MenuScreen(this);
		gameScr = new GameScreen(this);
		lvlOverScr = new LevelOverScreen(this);
		
		setScreen(getSplashScr());
		
		player = new Player();
	}
	
	public void restartGame()
	{
		stateMan.restartGame();
		player.restartGame();
		setScreen(getGameScr());
	}
	
	public StateManager getStateManager()
	{
		return stateMan;
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
	
	public LevelOverScreen getLvlOverScr()
	{
		return lvlOverScr;
	}
	
	public Player getPlayer()
	{
		return player;
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
