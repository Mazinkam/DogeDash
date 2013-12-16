package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.Statics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Flower extends Actor {

	private TextureRegion chosenType;
	private Rectangle bounds = new Rectangle();
	private float x;

	public Flower(float x, float y) {
		int randomNum = 1 + (int) (Math.random() * 4);
		setWidth(Assets.flower1.getRegionWidth());
		setHeight(Assets.flower1.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		if (randomNum == 1)
			chosenType = Assets.flower1;
		if (randomNum == 2)
			chosenType = Assets.flower2;
		if (randomNum == 3)
			chosenType = Assets.flower3;
		if (randomNum == 4)
			chosenType = Assets.flower4;

		this.x = x;

		addAction(Actions.repeat(10, Actions.sequence(Actions.rotateBy(10f, 1f), Actions.rotateBy(-10f, 1f))));

//		if (Statics.gameLevel == 2)
//			setColor(0.15f, 0.15f, 0.4f, 1.0f);
	}

	@Override
	public void act(float delta) {
		if (Statics.state == Statics.GameState.Running) {
			super.act(delta);
			updateMovement();
			updateBounds();
		}
		if (!Statics.objectsAlive) {
			this.remove();
		}
	}

	private void updateMovement() {
		x -= Statics.backgroundSpeed;
		addAction(Actions.moveTo(x, getY()));

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);

		batch.draw(chosenType, getX(), getY(), chosenType.getRegionWidth() / 2, chosenType.getRegionHeight() / 2, getWidth(), getHeight(), 1, 1,
				getRotation());

	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public Rectangle getBounds() {
		return bounds;
	}

}