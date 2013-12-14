package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.screens.MenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class EnemyBee extends Actor {

	private final Animation beeFlyingAnim;
	private float beeFlyingAnimState;
	private Array<TextureRegion> beeFlying;
	private boolean hitPlayer = false, onDoge = false;
	private float enemyX;
	private Rectangle bounds = new Rectangle();

	public EnemyBee(float x, float y) {
		setWidth(Assets.enemyBee.getRegionWidth());
		setHeight(Assets.enemyBee.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		beeFlying = new Array<TextureRegion>();
		this.enemyX = x;

		beeFlying.add(Assets.enemyBee);
		beeFlying.add(Assets.enemyBee2);
		beeFlying.add(Assets.enemyBee3);

		this.beeFlyingAnim = new Animation(0.15f, beeFlying);
	}

	@Override
	public void act(float delta) {
		if (Statics.gameRunning) {
			super.act(delta);
			updateMovement();
			updateBounds();
		}
	}

	private void updateMovement() {

		Action completeAction = new Action() {
			public boolean act(float delta) {
				Statics.cleanseEnemies = false;
				onDoge = false;
				Actions.removeActor();
				return true;
			}
		};

		if (!hitPlayer) {
			enemyX -= Statics.enemySpeed;
			addAction(Actions.moveTo(enemyX, getY()));
		}
		if (hitPlayer) {
			if (!Statics.isSuperD) {
				float randomX = (MotherDoge.playerX - 100) + (int) (Math.random() * (MotherDoge.playerX + 100));
				float randomY = (MotherDoge.playerY - 165) + (int) (Math.random() * (MotherDoge.playerY + 160));

				addAction(Actions.sequence(Actions.parallel(Actions.rotateTo(-180, 10.1f), Actions.moveTo(randomX, randomY, .9f))));
			}

		}

		if (Statics.cleanseEnemies && onDoge) {
			Statics.beesOnPlayer = 0;
			this.addAction(Actions.fadeOut(1f));
			addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(-360, 1.5f), completeAction)));
		}

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);

		TextureRegion frame = beeFlyingAnim.getKeyFrame(beeFlyingAnimState += Gdx.graphics.getDeltaTime() * 2, true);

		batch.draw(frame, getX(), getY(), frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, getWidth(), getHeight(), 1, 1, getRotation());
	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public void playerHit(boolean front, boolean above) {
		clearActions();
		hitPlayer = true;
		onDoge = true;
		Statics.playerHitByBee = true;
		Statics.beesOnPlayer++;

		Action completeAction = new Action() {
			public boolean act(float delta) {
				Statics.playerHitByBee = false;
				Gdx.app.log(DogeDashCore.LOG, "Statics.playerHitByBee " + Statics.playerHitByBee);
				return true;
			}
		};
		addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(-30, 1.5f), Actions.moveTo(MotherDoge.playerX, MotherDoge.playerY, 1.5f)),
				completeAction));

		if (Statics.isSuperD) {
			this.addAction(Actions.fadeOut(1f));
			if (front && above)
				addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(-360, 1.5f), Actions.moveBy(200, 200, 1.5f)), completeAction,
						Actions.removeActor()));
			if (front && !above)
				addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(360, 1.5f), Actions.moveBy(200, -200, 1.5f)), completeAction,
						Actions.removeActor()));
			if (!front && above)
				addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(360, 1.5f), Actions.moveBy(-200, 200, 1.5f)), completeAction,
						Actions.removeActor()));
			if (!front && !above)
				addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(-360, 1.5f), Actions.moveBy(-200, -200, 1.5f)), completeAction,
						Actions.removeActor()));
		}

	}

	public Rectangle getBounds() {
		return bounds;
	}
}
