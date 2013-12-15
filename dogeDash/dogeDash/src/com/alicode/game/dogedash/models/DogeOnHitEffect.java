package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.GamePoints;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.screens.MenuScreen;
import com.alicode.game.dogedash.sql.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
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
			updateBounds();
		}
		if (!Statics.objectsAlive) {
			this.remove();
		}
	}

	public void playerGotSwag() {
		Statics.playerGotSwag = true;
		setWidth(Assets.style.getRegionWidth());
		setHeight(Assets.style.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		chosenType = Assets.style;
		effectActive = true;

		clearActions();

		Action completeAction = new Action() {
			public boolean act(float delta) {
				Statics.playerGotSwag = false;
				GamePoints.bonusPointStatic += 100;
				return true;
			}
		};

		addAction(Actions.sequence(Actions.moveBy(0, 50, 0.5f), Actions.fadeOut(1), completeAction, Actions.removeActor()));

	}

	public void playerGotHit() {
		Statics.playerGotPow = true;
		setWidth(Assets.pow.getRegionWidth());
		setHeight(Assets.pow.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		chosenType = Assets.pow;
		effectActive = true;

		clearActions();
		Action completeAction = new Action() {
			public boolean act(float delta) {
				Statics.playerGotPow = false;
				if (Statics.isSuperD)
					GamePoints.bonusPointStatic += 100;
				return true;
			}
		};

		addAction(Actions.sequence(Actions.moveBy(0, 50, 0.5f), Actions.fadeOut(1), completeAction, Actions.removeActor()));

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
