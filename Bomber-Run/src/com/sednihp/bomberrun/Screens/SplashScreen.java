package com.sednihp.bomberrun.Screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.sednihp.bomberrun.Engine;

public class SplashScreen extends BaseScreen {

	private Texture logoImg;
	private Image logo;
	
	public SplashScreen(Engine e) {
		super(e);
	}

	@Override
    public void show() {
		super.show();
		
		logoImg = new Texture(Gdx.files.internal("logo.png"));
    }
	
	@Override
    public void resize(int width, int height) {
        super.resize( width, height );
        
        stage.clear();
        
        TextureRegion planeRegion = new TextureRegion(logoImg);
        Drawable planeDrawable = new TextureRegionDrawable( planeRegion );

        logo = new Image(planeDrawable, Scaling.stretch );
        
        logo.getColor().a = 0f;
        
        logo.addAction( sequence( fadeIn( 0.75f ), delay( 2f ), fadeOut( 0.75f ),
    		new Action() {
    			public boolean act( float delta ) {
    				engine.setScreen(new MenuScreen(engine));
    				return true;
    			}
    		})
        );
        
        stage.addActor(logo);
    }
	
	@Override
    public void dispose() {
		super.dispose();
    }
	
}
