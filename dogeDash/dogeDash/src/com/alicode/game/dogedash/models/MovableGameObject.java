package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.models.player.Player;
import com.badlogic.gdx.math.Vector2;

public abstract class MovableGameObject extends GameObject{

	protected Vector2 velocity;
	protected float SPEED;
	protected float rotation;
	
	public MovableGameObject(float SPEED, float rotation, float width, float height, Vector2 position){
		super(position, width, height);
		this.SPEED = SPEED;
		this.rotation = rotation;
		velocity = new Vector2(0, 0);
	}
	
	public Vector2 getVelocity(){
		return velocity;
	}
	
	public void setVelocity(Vector2 velocity){
		this.velocity = velocity;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public void setRotation(float rotation){
		this.rotation = rotation;
	}
	
	public void update(Player player){
		bounds.x = position.x;
		bounds.y = position.y;
	}
}
