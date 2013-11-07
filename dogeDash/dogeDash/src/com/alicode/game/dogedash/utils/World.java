package com.alicode.game.dogedash.utils;

import java.util.Iterator;

import com.alicode.game.dogedash.DogeDashCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class World {
	
	DogeDashCore game;
	WorldRenderer worldRender;


	
	public World (DogeDashCore game){
		this.game = game;
		Gdx.input.setInputProcessor(new InputHandler(this));
	}
	
	public void update(){

	}
	
	public void setRenderer(WorldRenderer worldRender){
		this.worldRender = worldRender;
	}
	
	public WorldRenderer getRenderer(){
		return worldRender;
	}
	
	public void dispose(){
		
	}
}
