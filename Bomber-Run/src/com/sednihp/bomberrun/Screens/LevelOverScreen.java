package com.sednihp.bomberrun.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.sednihp.bomberrun.Engine;

public class LevelOverScreen extends BaseScreen {

	private final float labelY = 300f;
	private Skin skin;
	private Stage stage;
	private Label label, levelScoreLabel, overallScoreLabel;;
	private TextButton playButton;
	private boolean gameOver;
	
	public LevelOverScreen(Engine e) {
		super(e);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage(engine.getWidth(), engine.getHeight(), true);
	}
	
	@Override
	public void show()
	{
		stage.clear();
		
		gameOver = !(engine.getPlayer().isAlive());
		
		int levelScore = engine.getPlayer().getLevelScore();
		engine.getPlayer().completedLevel();
		
		if(gameOver)
		{
			label = new Label("Game over", skin);
			playButton = new TextButton("Restart game", skin);
		}
		else
		{
			label = new Label("Level completed!", skin);
			playButton = new TextButton("Next level", skin);
		}
		
		levelScoreLabel = new Label("Score = " + levelScore, skin);
		overallScoreLabel = new Label("Overall Score = " + engine.getPlayer().getOverallScore(), skin);
		
		label.setX((engine.getWidth() - label.getWidth())/2);
        label.setY(labelY);
        stage.addActor(label);
        
        levelScoreLabel.setX((engine.getWidth() - levelScoreLabel.getWidth()) / 2f);
        levelScoreLabel.setY(label.getY() - levelScoreLabel.getHeight());
        stage.addActor(levelScoreLabel);
        
        overallScoreLabel.setX((engine.getWidth() - overallScoreLabel.getWidth()) / 2f);
        overallScoreLabel.setY(levelScoreLabel.getY() - overallScoreLabel.getHeight());
        stage.addActor(overallScoreLabel);
        
        float buttonX = (engine.getWidth() - buttonWidth) / 2f;
        
        playButton.setX(buttonX);
		playButton.setY(overallScoreLabel.getY() - playButton.getHeight() - buttonSpacing);
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
                if(gameOver)
                {
                	engine.restartGame();
                	engine.setScreen(engine.getGameScr());
                }
                else
                {
                	engine.getStateManager().setToNextLevel();
                    engine.setScreen(engine.getGameScr());
                }
            }
        } );
		stage.addActor(playButton);
		
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
