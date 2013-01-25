package com.sednihp.bomberrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.sednihp.bomberrun.Screens.SplashScreen;

public class Engine extends Game {

	private int width = 800, height = 480;
	
	public Engine() {
	}
	
	@Override
	public void create() {
		setScreen(new SplashScreen(this));		
	}

	@Override
    public void resize(int width, int height) {
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
    public void setScreen(Screen screen) {
        super.setScreen( screen );
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
