package com.alicode.game.dogedash.models.player;

import com.alicode.game.dogedash.models.MovableGameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Player extends MovableGameObject {
	public static float rotate = 0;

	public Player(float SPEED, float rotation, float width, float height, Vector2 position) {
		super(SPEED, rotation, width, height, position);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));

		rotation = velocity.angle() * rotate;

		bounds.x = position.x;
		bounds.y = position.y;

		if (position.y <= 0)
			position.y = 0;
		if (position.y >= 400)
			position.y = 400;
	}

	public void moveRight() {

	}

}
