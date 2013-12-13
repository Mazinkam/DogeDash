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

public class DogeCoin extends Actor {

	private TextureRegion chosenType;
	private Rectangle bounds = new Rectangle();
	private float x;
	private ParticleEffect coinTrail;

	public DogeCoin(float x, float y) {

		setWidth(Assets.chowcoin.getRegionWidth());
		setHeight(Assets.chowcoin.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		chosenType = Assets.chowcoin;

		this.x = x;
		
		coinTrail = new ParticleEffect();
		coinTrail.load(Gdx.files.internal("particles/coinTrail"), Gdx.files.internal("particles"));

		addAction(Actions.repeat(10, Actions.sequence(Actions.rotateBy(20f, 1f))));

		if (Statics.gameLevel == 2)
			setColor(0.15f, 0.15f, 0.4f, 1.0f);
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

	public void playerHit() {
		clearActions();
		addAction(Actions.sequence(Actions.fadeOut(1),Actions.removeActor()));
		GamePoints.dogeCoins++;
		disposeTrail();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
		updateTrail(batch);
		batch.draw(chosenType, getX(), getY(), chosenType.getRegionWidth() / 2, chosenType.getRegionHeight() / 2, getWidth(), getHeight(), 1, 1,
				getRotation());
	}

	private void updateTrail(SpriteBatch batch) {
		coinTrail.start();

		coinTrail.setPosition(getX() + Assets.chowcoin.getRegionWidth(), getY());

//		for (int i = 0; i < coinTrail.getEmitters().size; i++) {
//			coinTrail.getEmitters().get(i).getAngle().setLow(180);
//			coinTrail.getEmitters().get(i).getAngle().setHigh(180);
//		}

		coinTrail.draw(batch);
		coinTrail.update(Gdx.graphics.getDeltaTime());
		
	}
	
	public void disposeTrail()
	{
		coinTrail.dispose();
	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public Rectangle getBounds() {
		return bounds;
	}

}