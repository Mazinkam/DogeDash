package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Background extends Actor {
	private float x, y;
	public Background(float x, float y) {
		setWidth(Assets.bg_big.getWidth());
		setHeight(Assets.bg_big.getHeight());
		setPosition(x, y);
		this.x=x;
		this.y=y;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Assets.bg_big, getX() , getY(), getWidth(), getHeight());
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		updateBgMovement();
	}

	
	private void updateBgMovement() {
		x-=4;

		if (getX() >= -Assets.bg_big.getWidth()) {
//			position.x += 4794;
			addAction(Actions.moveTo(x, getY()));
			
		}
		else{
			x+=Assets.bg_big.getWidth()*2;
			addAction(Actions.moveTo(x, getY()));
			Gdx.app.log(DogeDashCore.LOG, "currentX:: " + x + "getX()" + getX());
		}
	}
}
