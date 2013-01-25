package com.sednihp.bomberrun.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sednihp.bomberrun.Engine;

public class BaseScreen implements Screen {

	protected Engine engine;
	protected BitmapFont font;
	protected SpriteBatch batch;
	protected Stage stage;
	protected Skin skin;

	public BaseScreen(Engine e)
	{
		this.engine = e;
		this.font = new BitmapFont(Gdx.files.internal("saucerbb.fnt"), false);
		this.batch = new SpriteBatch();
		this.stage = new Stage(0, 0, true);
		this.skin = new Skin(Gdx.files.internal("uiskin.json"));

		Gdx.input.setInputProcessor( stage );
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
	public void resize(int width, int height)
	{
		stage.setViewport( width, height, true );
		stage.clear();
	}

	@Override
	public void show()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void hide()
	{
		dispose();

	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose()
	{
		font.dispose();
		batch.dispose();
		stage.dispose();
		skin.dispose();
	}

}
