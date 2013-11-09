package com.alicode.game.dogedash.models.player;

import com.alicode.game.dogedash.models.MovableGameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Player extends MovableGameObject{
	float rotate = 90f;
	public Player(float SPEED, float rotation, float width, float height, Vector2 position) {
		super(SPEED, rotation, width, height, position);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
		
//		if (velocity.y != 0){
//			if(velocity.y < 1)
//			{
//				rotate+=1.2;
//				rotation = velocity.angle() + rotate;
//			}
//			else
//			{
//				rotate+=1.2;
//				rotation = velocity.angle() - rotate;
//			}
//		}
//			rotation = velocity.angle() - position.y/4;
		
		bounds.x = position.x;
		bounds.y = position.y;
	}
	
	public void moveRight()
	{
		
	}
	

}
