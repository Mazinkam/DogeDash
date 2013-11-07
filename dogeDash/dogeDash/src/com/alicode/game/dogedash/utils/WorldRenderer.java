package com.alicode.game.dogedash.utils;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
public class WorldRenderer {
	
	World world;
	SpriteBatch batch;
	OrthographicCamera cam;
	Texture shipTexture, followerTexture, bulletTexture;
	float width, height;
	ShapeRenderer sr;
	ParticleEmitter exhaust;
	
	public WorldRenderer(World world){
		this.world = world;	
	}
	
	public void render() {

	}
	
	private void setExhaustRotation() {
	}

	public OrthographicCamera getCamera(){
		return cam;
	}
	
	public void dispose() {
		batch.dispose();
		shipTexture.dispose();
		followerTexture.dispose();
		bulletTexture.dispose();
		sr.dispose();		
	}

}
