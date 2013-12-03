package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Background extends Actor {
	private float x, y;

	public Background(float x, float y) {
		setWidth(Assets.bg_big_day.getWidth());
		setHeight(Assets.bg_big_day.getHeight());
		setPosition(x, y);
		this.x = x;
		this.y = y;
		if (Statics.gameLevel == 2)
			setColor(0.15f, 0.15f, 0.4f, 1.0f);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (Statics.gameLevel == 1)
			batch.draw(Assets.bg_big_day, getX(), getY(), getWidth(), getHeight());
		if (Statics.gameLevel == 2)
			batch.draw(Assets.bg_big_night, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void act(float delta) {
		if (Statics.gameRunning) {
			super.act(delta);
			updateBgMovement();
		}
	}

	private void updateBgMovement() {
		x -= Statics.backgroundSpeed;
		if (getX() >= -Assets.bg_big_day.getWidth()) {
			// position.x += 4794;
			addAction(Actions.moveTo(x, getY()));

		} else {
			x += Assets.bg_big_day.getWidth() * 2;
			addAction(Actions.moveTo(x, getY()));
		}
	}
}
