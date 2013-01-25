package com.sednihp.bomberrun.Screens;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.sednihp.bomberrun.Engine;
import com.sednihp.bomberrun.Entities.Plane;
import com.sednihp.bomberrun.Entities.Building;
import com.sednihp.bomberrun.Entities.Bomb;
import com.sednihp.bomberrun.Entities.Cloud;

public class GameScreen extends BaseScreen {
	private Texture planeImg, bombImg, cloudImg;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRender;
	private Rectangle groundLine;
	private Color groundColor;
	
	private Plane plane;
	private Array<Building> buildings;
	private Array<Cloud> clouds;
	private Bomb bomb;
	private Music planeEngine;
	private Sound explosion;
	private int score = 0;
	
	
	public GameScreen(Engine e) 
	{
		super(e);
	}
	
	@Override
    public void show() 
	{
		super.show();
		
		planeImg = new Texture(Gdx.files.internal("plane.png"));
		bombImg = new Texture(Gdx.files.internal("bomb.png"));
		cloudImg = new Texture(Gdx.files.internal("cloud.png"));
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, engine.getWidth(), engine.getHeight());
  		shapeRender = new ShapeRenderer();
  		groundLine = new Rectangle(0,30,engine.getWidth(),1);
  		groundColor = new Color(0.554f, 0.316f, 0.234f, 0);
  		
  		plane = new Plane(engine.getHeight());
  		
  		buildings = new Array<Building>();
  		for(int i = 0; i < 1; ++i)
  		{
  			Building b = new Building(160 + i*32);
  			buildings.add(b);
  		}
  		
  		bomb = null;
  		
  		clouds = new Array<Cloud>();
  		for(int i=0; i<2;++i) 
  		{
  			Cloud c = new Cloud(engine.getWidth(), engine.getHeight(), groundLine.y);
  			clouds.add(c);
  		}
  		
  		planeEngine = Gdx.audio.newMusic(Gdx.files.internal("plane.ogg"));
  		planeEngine.setLooping(true);
  		planeEngine.play();
  		
  		explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
  		Gdx.audio.newSound(Gdx.files.internal("crash.ogg"));
    }
	
	@Override
	public void render(float dTime)
	{		
		handleEvents(dTime);
		update(dTime);
		draw(dTime);
	}
	
	private void handleEvents(float dTime) 
	{
		if(Gdx.input.isTouched() && bomb == null) 
		{
        	 float x = plane.x + (plane.width / 2);
        	 bomb = new Bomb(x, plane.y);
		}
	}
	
	private void update(float dTime) 
	{
		updateClouds(dTime);
		
		updatePlane(dTime);
				
		updateBomb(dTime);
		
		if(buildings.size == 0)
		{
			plane.setToPark();
		}
		
		if(plane.isParked())
		{
			engine.setScreen(new MenuScreen(engine));
		}
	}
	
	private void updateClouds(final float dTime) 
	{
		Iterator<Cloud> cIter = clouds.iterator();
		while(cIter.hasNext()) 
		{
			Cloud c = cIter.next();
			c.move(dTime, engine.getWidth());
		}
	}
	
	private void updatePlane(final float dTime)
	{
		if(!plane.isParked())
		{
			plane.move(dTime, engine.getWidth(), (int)(groundLine.y + groundLine.height));
			
			//check if plane has hit a building
			Iterator<Building> pIter = buildings.iterator();
		    while(pIter.hasNext()) 
		    {
		    	Building b = pIter.next();
		    	
		    	if(b.overlaps(plane))
		    	{
		    		plane.hasCrashed();
		    		planeEngine.stop();
		    		engine.setScreen(new MenuScreen(engine));
		    	}
		    }
		}
		else if(plane.isParked())
		{
			
		}
	}
	
	private void updateBomb(float dTime)
	{
		if(bomb != null) 
		{
			bomb.move(dTime, groundLine.y);
			
			boolean removeBomb = false;
			
			//check if bomb has hit a building
			Iterator<Building> bIter = buildings.iterator();
		    while(bIter.hasNext()) 
		    {
		    	Building b = bIter.next();
		    	
		    	if(b.overlaps(bomb))
		    	{
		    		score += b.getScore();
		    		bIter.remove();
		    		removeBomb = true;
		    	}		    	
		    }
		    
		    //check if bomb has reached the ground
		    if(bomb.isOffScreen()) 
		    {
				removeBomb = true;
			}
		    
		    if(removeBomb) 
		    {
		    	explosion.play();
		    	bomb = null;
		    }
		}
	}
	
	private void draw(float dTime) 
	{
		Gdx.gl.glClearColor(0.586f, 0.781f, 1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			for(Cloud c : clouds) 
			{
				batch.draw(cloudImg, c.x, c.y);
			}
			
			if(bomb != null) 
			{
				batch.draw(bombImg, bomb.x, bomb.y);
			}
			
			batch.draw(planeImg, plane.x, plane.y);
			super.font.draw(batch, "Score = " + score, 0, groundLine.y);
		batch.end();

		shapeRender.setProjectionMatrix(camera.combined);
		shapeRender.begin(ShapeType.FilledRectangle);
			for(Building b : buildings) 
			{
				shapeRender.setColor(b.getColor());
				shapeRender.filledRect(b.x, b.y, b.width, b.height);
			}
			shapeRender.setColor(groundColor);
			shapeRender.filledRect(groundLine.x, groundLine.y, groundLine.width, groundLine.height);
		shapeRender.end();
	}
	
	@Override
    public void dispose() 
	{
		super.dispose();
		
		planeImg.dispose();
		bombImg.dispose();
		cloudImg.dispose();
		planeEngine.dispose();
		explosion.dispose();
		shapeRender.dispose();
    }
}
