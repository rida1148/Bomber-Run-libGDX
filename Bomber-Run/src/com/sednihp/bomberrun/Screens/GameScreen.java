package com.sednihp.bomberrun.Screens;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.sednihp.bomberrun.Engine;
import com.sednihp.bomberrun.Timer;
import com.sednihp.bomberrun.Entities.Plane;
import com.sednihp.bomberrun.Entities.Building;
import com.sednihp.bomberrun.Entities.Bomb;
import com.sednihp.bomberrun.Entities.Cloud;

public class GameScreen extends BaseScreen {
	
	private SpriteBatch batch;
	private BitmapFont font;
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
	private Timer endGameTimer;
	
	public GameScreen(Engine e) 
	{
		super(e);
		
		font = new BitmapFont(Gdx.files.internal("saucerbb.fnt"), false);
		batch = new SpriteBatch();
		planeImg = new Texture(Gdx.files.internal("plane.png"));
		bombImg = new Texture(Gdx.files.internal("bomb.png"));
		cloudImg = new Texture(Gdx.files.internal("cloud.png"));
  		planeEngine = Gdx.audio.newMusic(Gdx.files.internal("plane.ogg"));
  		explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
  		shapeRender = new ShapeRenderer();
  		groundLine = new Rectangle(0,30,engine.getWidth(),1);
  		groundColor = new Color(0.554f, 0.316f, 0.234f, 0);
  		endGameTimer = new Timer();
	}
	
	@Override
    public void show() 
	{		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, engine.getWidth(), engine.getHeight());
  		
  		plane = new Plane(engine.getHeight(), engine.getStateManager().getCurrentLevel());
  		
  		createBuildings();
  		
  		bomb = null;
  		
  		clouds = new Array<Cloud>();
  		for(int i=0; i<3;++i) 
  		{
  			Cloud c = new Cloud(engine.getWidth(), engine.getHeight(), groundLine.y);
  			clouds.add(c);
  		}
  		
  		planeEngine.setLooping(true);
  		planeEngine.play();
    }
	
	public void createBuildings()
	{
		buildings = new Array<Building>();
		int numBuildings = 0;
  		switch(engine.getStateManager().getCurrentLevel())
  		{
	  		case 1: numBuildings = 15;
	  				break;
	  		case 2: numBuildings = 17;
	  				break;
	  		case 3: numBuildings = 19;
	  				break;
	  		case 4: numBuildings = 21;
	  				break;
	  		case 5: numBuildings = 23;
	  				break;
	  		default: numBuildings = 25;
	  				 break;
  		}
  		//numBuildings = 1;	  		
		for(int i = 0; i < numBuildings; ++i)
  		{
  			Building b = new Building(numBuildings, i, engine.getWidth());
  			buildings.add(b);
  		}
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
		
		updateState();
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
		    	}
		    }
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
		    		engine.getPlayer().addToScore(b.getScore());
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
	
	private void updateState()
	{
		//if we've destroyed all the buildings, set the plane to park
		if(buildings.size == 0)
		{
			plane.setToPark();
		}
		
		//if the plane has parked, start a timer so we don't exit straight away
		if(plane.isParked() && !endGameTimer.isRunning())
		{
			endGameTimer.start();
		}		
		//if the plane has been parked for over 2s, go to the lvlOverScreen
		else if(plane.isParked() && endGameTimer.getTime() > 2000)
		{
			engine.setScreen(engine.getLvlOverScr());
		}
		//if the plane has crashed, go to the lvlOverScr immediately
		else if(plane.hasCrashed())
		{
			engine.setScreen(engine.getLvlOverScr());
		}
	}
	
	private void draw(float dTime) 
	{
		super.render(dTime);
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
			font.draw(batch, "Score = " + engine.getPlayer().getLevelScore(), 0, groundLine.y);
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
		planeImg.dispose();
		bombImg.dispose();
		cloudImg.dispose();
		planeEngine.dispose();
		explosion.dispose();
		shapeRender.dispose();
		font.dispose();
		batch.dispose();
    }
	
	@Override
	public void hide()
	{
		super.hide();
		
		endGameTimer.stop();
	}
}
