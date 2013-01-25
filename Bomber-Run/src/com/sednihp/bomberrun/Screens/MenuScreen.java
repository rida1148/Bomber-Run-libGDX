package com.sednihp.bomberrun.Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sednihp.bomberrun.Engine;


public class MenuScreen extends BaseScreen {

	private final float buttonY = 200f, buttonWidth = 300f, buttonHeight = 60f, buttonSpacing = 20f; 
	
	public MenuScreen(Engine e) {
		super(e);
	}
	
	@Override
    public void resize(int width, int height) {
		super.resize(width, height);
		
		float buttonX = (width - buttonWidth)/2;
        
        Label l = new Label("Welcome to Bomber Run", skin);
        l.setX((width - l.getWidth())/2);
        l.setY(buttonY + 100);
        stage.addActor(l);

        TextButton t = new TextButton("Play", skin);
		t.setX(buttonX);
		t.setY(buttonY);
		t.setWidth(buttonWidth);
		t.setHeight(buttonHeight);
		t.addListener( new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button )
            {
                super.touchUp( event, x, y, pointer, button );
                engine.setScreen(new GameScreen(engine));
            }
        } );
		stage.addActor(t);

		TextButton tb = new TextButton("High Scores", skin);
		tb.setX(buttonX);
		tb.setY(buttonY - buttonHeight - buttonSpacing);
		tb.setWidth(buttonWidth);
		tb.setHeight(buttonHeight);
        tb.addListener( new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button )
            {
                super.touchUp( event, x, y, pointer, button );
                engine.setScreen( null );
            }
        } );
        stage.addActor(tb);
    }

}
