package com.alicode.game.dogedash.models.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
	public static float rotate = 0;
	private float speed;
	private float rotation;
	private float width;
	private float height;
	private Vector2 position;
	private Vector2 velocity;
	private Rectangle bounds;
	
	public Player(float speed, float rotation, float width, float height, Vector2 position) {
		this.speed = speed;
		this.rotation = speed;
		this.width = width;
		this.height = height;
		this.position = position;
		// TODO Auto-generated constructor stub
	}

	public void update() {
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * speed));

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

	public static float getRotate() {
		return rotate;
	}

	public static void setRotate(float rotate) {
		Player.rotate = rotate;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

}
