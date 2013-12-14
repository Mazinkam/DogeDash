package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.GamePoints;
import com.alicode.game.dogedash.Statics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class DogeOnHitEffect extends Actor {

	private TextureRegion chosenType;
	private Rectangle bounds = new Rectangle();
	private float x, y;
	private boolean effectActive = false;

	public DogeOnHitEffect(float x, float y) {

		this.x = x;
		this.y = y;
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
		x -= Statics.enemySpeed;
		addAction(Actions.moveTo(x, getY()));

	}

	public void playerGotSwag() {
		setWidth(Assets.style.getRegionWidth());
		setHeight(Assets.style.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		chosenType = Assets.style;
		effectActive = true;

		clearActions();
		addAction(Actions.parallel(Actions.sequence(Actions.moveBy(0, 100, 1.5f), Actions.fadeOut(1), Actions.removeActor())));
		GamePoints.bonusPointStatic += 100;

	}
	public void playerGotHit() {
		setWidth(Assets.pow.getRegionWidth());
		setHeight(Assets.pow.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		chosenType = Assets.pow;
		effectActive = true;

		clearActions();
		addAction(Actions.parallel(Actions.sequence(Actions.moveBy(0, 100, 1.5f), Actions.fadeOut(1), Actions.removeActor())));
		GamePoints.bonusPointStatic += 100;

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
		if (effectActive)
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
