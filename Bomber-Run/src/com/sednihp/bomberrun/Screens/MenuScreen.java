package com.sednihp.bomberrun.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.sednihp.bomberrun.Engine;


public class MenuScreen extends BaseScreen {

	private final float buttonY = 200f;
	private Skin skin;
	private Stage stage;
	private Label label;
	private TextButton playButton, highScoresButton;
	
	public MenuScreen(Engine e) 
	{
		super(e);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage(engine.getWidth(), engine.getHeight(), true);
		label = new Label("Welcome to Bomber Run", skin);
		playButton = new TextButton("Play", skin);
		highScoresButton = new TextButton("High Scores", skin);
		
		float buttonX = (engine.getWidth() - buttonWidth)/2;
        
        label.setX((engine.getWidth() - label.getWidth())/2);
        label.setY(buttonY + 100);
        stage.addActor(label);

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
                //reset the level back to 1, start the game
                engine.getStateManager().restartGame();
                engine.setScreen(engine.getGameScr());
            }
        } );
		stage.addActor(playButton);

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
	}
	
	@Override
	public void show()
	{
        Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float dTime)
	{		
		stage.act(dTime);
		
		super.render(dTime);
        
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
}
