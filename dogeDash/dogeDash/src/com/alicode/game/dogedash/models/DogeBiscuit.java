package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
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

public class DogeBiscuit extends Actor {

	private TextureRegion chosenType;
	private Rectangle bounds = new Rectangle();
	private Animation dogeBiscuitAnim;
	private float dogeBiscuitAnimState;
	private Array<TextureRegion> dogeBiscuit;
	private float x;
	private ParticleEffect dogeBiscuitTrail;

	public DogeBiscuit(float x, float y) {

		setWidth(Assets.treat1.getRegionWidth());
		setHeight(Assets.treat1.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		dogeBiscuit = new Array<TextureRegion>();
		dogeBiscuit.add(Assets.treat1);
		dogeBiscuit.add(Assets.treat2);

		this.dogeBiscuitAnim = new Animation(0.15f, dogeBiscuit);

		this.x = x;

		addAction(Actions.repeat(10, Actions.sequence(Actions.rotateBy(10f, 1f))));

		dogeBiscuitTrail = new ParticleEffect();
		dogeBiscuitTrail.load(Gdx.files.internal("particles/dogeBiscuitTrail"), Gdx.files.internal("particles"));

		if (Statics.gameLevel == 2)
			setColor(0.15f, 0.15f, 0.4f, 1.0f);
	}

	@Override
	public void act(float delta) {
		if (Statics.state == Statics.GameState.Running) {
			super.act(delta);
			updateMovement();
			updateBounds();
			updateSuperD();
		}
		if (!Statics.objectsAlive) {
			this.remove();
		}
	}

	private void updateSuperD() {
		if (Statics.isSuperD) {
			Statics.superDogeTimer--;
			if (Statics.superDogeTimer <= 0) {
				Statics.isSuperD = false;
				Statics.superDogeTimer = 200;
			Statics.normalMode();
			}
			Gdx.app.log(DogeDashCore.LOG, "superDogeTimer " + Statics.superDogeTimer);
		}

	}

	private void updateMovement() {
		x -= Statics.backgroundSpeed;
		addAction(Actions.moveTo(x, getY()));

	}

	public void playerHit() {
		clearActions();
		Statics.isSuperD = true;
		disposeTrail();
		Statics.SuperDogeMode();
		addAction(Actions.sequence(Actions.removeActor()));
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);

		TextureRegion frame = dogeBiscuitAnim.getKeyFrame(dogeBiscuitAnimState += Gdx.graphics.getDeltaTime(), true);
		updateTrail(batch);
		batch.draw(frame, getX(), getY(), frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, getWidth(), getHeight(), 1, 1, getRotation());
	}

	private void updateTrail(SpriteBatch batch) {
		dogeBiscuitTrail.start();

		dogeBiscuitTrail.setPosition(getX() + Assets.chowcoin.getRegionWidth(), getY());

//		for (int i = 0; i < dogeBiscuitTrail.getEmitters().size; i++) {
//			dogeBiscuitTrail.getEmitters().get(i).getAngle().setLow(160);
//			dogeBiscuitTrail.getEmitters().get(i).getAngle().setHigh(-10);
//		}

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