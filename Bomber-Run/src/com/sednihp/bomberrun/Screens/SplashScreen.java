package com.sednihp.bomberrun.Screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.sednihp.bomberrun.Engine;

public class SplashScreen implements Screen {

	private Engine engine;
	private Texture logoImg;
	private Image logo;
	private Stage stage;
	
	public SplashScreen(Engine e) 
	{
		engine = e;
		stage = new Stage(0, 0, true);
		logoImg = new Texture(Gdx.files.internal("logo.png"));
	}

	@Override
    public void show() 
	{		
		stage.setViewport(engine.getWidth(), engine.getHeight(), true );
        stage.clear();
        
        TextureRegion planeRegion = new TextureRegion(logoImg);
        Drawable planeDrawable = new TextureRegionDrawable( planeRegion );

        logo = new Image(planeDrawable, Scaling.stretch );
        
        logo.getColor().a = 0f;
        
        logo.addAction( sequence( fadeIn( 0.75f ), delay( 2f ), fadeOut( 0.75f ),
    		new Action() {
    			public boolean act( float delta ) {
    				engine.setScreen(engine.getMenuScr());
    				return true;
    			}
    		})
        );   
        stage.addActor(logo);
    }
	
	@Override
	public void render(float dTime) 
	{
		stage.act(dTime);
		
		Gdx.gl.glClearColor(0.586f, 0.781f, 1.0f, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );	
		
		stage.draw();
	}
	
	@Override
    public void dispose() 
	{
		logoImg.dispose();
		stage.dispose();
    }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
}
