package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Background extends Actor {
	public Background(float width, float height) {
		setWidth(width);
		setHeight(height);
		setPosition(0, 0);
	
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Assets.bg_big, getX() , getY(), getWidth(), getHeight());
	}
}
