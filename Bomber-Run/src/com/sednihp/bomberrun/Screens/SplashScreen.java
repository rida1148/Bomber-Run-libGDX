package com.sednihp.bomberrun.Screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.sednihp.bomberrun.Engine;

public class SplashScreen extends BaseScreen {

	private Texture logoImg;
	private Image logo;
	private Stage stage;
	
	public SplashScreen(Engine e) 
	{
		super(e);
		stage = new Stage(engine.getWidth(), engine.getHeight(), true);
		logoImg = new Texture(Gdx.files.internal("logo.png"));
		
		TextureRegion planeRegion = new TextureRegion(logoImg);
        Drawable planeDrawable = new TextureRegionDrawable( planeRegion );

        logo = new Image(planeDrawable, Scaling.stretch );
	}

	@Override
    public void show() 
	{		
		stage.setViewport(engine.getWidth(), engine.getHeight(), true );
        stage.clear();
        
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
		
		super.render(dTime);
		
		stage.draw();
	}
	
	@Override
    public void dispose() 
	{
		logoImg.dispose();
		stage.dispose();
    }	
}
