package com.sednihp.bomberrun.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.sednihp.bomberrun.Engine;


public class MenuScreen implements Screen {

	private Engine engine;
	private final float buttonY = 200f, buttonWidth = 300f, buttonHeight = 60f, buttonSpacing = 20f;
	private Skin skin;
	private Stage stage;
	Label label;
	TextButton playButton, highScoresButton;
	
	public MenuScreen(Engine e) 
	{
		engine = e;
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage(engine.getWidth(), engine.getHeight(), true);
	}
	
	@Override
	public void show()
	{
		float buttonX = (engine.getWidth() - buttonWidth)/2;
        
        label = new Label("Welcome to Bomber Run", skin);
        label.setX((engine.getWidth() - label.getWidth())/2);
        label.setY(buttonY + 100);
        stage.addActor(label);

        playButton = new TextButton("Play", skin);
		playButton.setX(buttonX);
		playButton.setY(buttonY);
		playButton.setWidth(buttonWidth);
		playButton.setHeight(buttonHeight);
		playButton.addListener( new InputListener()
		{
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button )
			{
				super.touchDown(event, x, y, pointer, button);
				return true;
			}
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button )
            {
                super.touchUp( event, x, y, pointer, button );
                engine.setScreen(engine.getGameScr());
            }
        } );
		stage.addActor(playButton);

		highScoresButton = new TextButton("High Scores", skin);
		highScoresButton.setX(buttonX);
		highScoresButton.setY(buttonY - buttonHeight - buttonSpacing);
		highScoresButton.setWidth(buttonWidth);
		highScoresButton.setHeight(buttonHeight);
        highScoresButton.addListener( new InputListener()
        {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button )
            {
                super.touchUp( event, x, y, pointer, button );
                engine.setScreen( null );
            }
        } );
        stage.addActor(highScoresButton);
        
        Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float dTime)
	{		
		stage.act(dTime);
		
		Gdx.gl.glClearColor(0.586f, 0.781f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.draw();
	}
	
	@Override
	public void hide()
	{
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose()
	{	
		skin.dispose();
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
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
