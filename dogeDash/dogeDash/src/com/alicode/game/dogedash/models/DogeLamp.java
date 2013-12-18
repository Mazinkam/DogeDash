package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.Consts;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class DogeLamp extends Actor {

	private TextureRegion chosenType;
	private Rectangle bounds = new Rectangle();
	private float x;
	private ParticleEffect dogeBiscuitTrail;
	private TextureRegion frame;

	public DogeLamp(float x, float y) {

		setWidth(Assets.shield_pickup.getRegionWidth());
		setHeight(Assets.shield_pickup.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		this.x = x;

		addAction(Actions.repeat(10, Actions.sequence(Actions.rotateBy(10f, 1f))));

		dogeBiscuitTrail = new ParticleEffect();
		dogeBiscuitTrail.load(Gdx.files.internal("particles/dogeShieldTrail"), Gdx.files.internal("particles"));

	}

	@Override
	public void act(float delta) {
		if (Statics.state == Statics.GameState.Running) {
			super.act(delta);
			updateMovement();
			updateBounds();
			updateLamp();
		}
		if (!Statics.objectsAlive) {
			this.remove();
		}
	}

	private void updateLamp() {
		if (Statics.dogeLampActive) {
			Statics.dogeLampTimer--;
			if (Statics.dogeLampTimer <= 0) {
				Statics.dogeLampActive = false;
				Statics.playerVisionRadius += 100;
				Statics.dogeLampTimer = Consts.LAMP_TIMER;
			}
		}

	}

	private void updateMovement() {
		x -= Statics.backgroundSpeed;
		addAction(Actions.moveTo(x, getY()));

	}

	public void playerHit() {
		clearActions();
		Statics.dogeLampActive = true;
		disposeTrail();
		addAction(Actions.sequence(Actions.removeActor()));
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);

		frame = Assets.shield_pickup;
		
		updateTrail(batch);
		batch.draw(frame, getX(), getY(), frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, getWidth(), getHeight(), 1, 1, getRotation());
		batch.end();
		batch.begin();
	}

	private void updateTrail(SpriteBatch batch) {
		dogeBiscuitTrail.start();

		dogeBiscuitTrail.setPosition(getX() + Assets.shield_pickup.getRegionWidth()/2, getY()+Assets.shield_pickup.getRegionHeight()/2);

		dogeBiscuitTrail.draw(batch);
		dogeBiscuitTrail.update(Gdx.graphics.getDeltaTime());

	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void disposeTrail() {
		dogeBiscuitTrail.dispose();
		

	}

}