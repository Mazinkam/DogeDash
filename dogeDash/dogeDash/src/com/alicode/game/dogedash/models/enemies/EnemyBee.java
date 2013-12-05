package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.MotherDoge;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class EnemyBee extends Actor {

	private final Animation beeFlyingAnim;
	private float beeFlyingAnimState;
	private Array<TextureRegion> beeFlying;
	private boolean hitPlayer = false;
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

		normalMovement();
		stickOnPlayer();
		clearTheAir();

	}

	private void normalMovement() {
		if (!hitPlayer) {
			enemyX -= Statics.enemySpeed;
			addAction(Actions.moveTo(enemyX, getY()));
		}

	}

	private void stickOnPlayer() {
		if (hitPlayer && !Statics.cleanseEnemies) {
			if (!Statics.isSuperD) {
				float randomX = (MotherDoge.playerX - 100) + (int) (Math.random() * (MotherDoge.playerX + 100));
				float randomY = (MotherDoge.playerY - 165) + (int) (Math.random() * (MotherDoge.playerY + 160));

				addAction(Actions.sequence(Actions.parallel(Actions.rotateTo(-180, 10.1f), Actions.moveTo(randomX, randomY, .9f))));
			}
		}
	}

	private void clearTheAir() {

		Action completeAction = new Action() {
			public boolean act(float delta) {

				Statics.cleanseEnemies = false;
				hitPlayer = false;
				Statics.playerHitByBee = false;
				Statics.beesOnPlayer--;
				if (Statics.beesOnPlayer < 0) {
					Statics.beesOnPlayer = 0;
				}
				this.actor.remove();
				Gdx.app.log(DogeDashCore.LOG, "hitPlayer " + hitPlayer + " Statics.cleanseEnemies " + Statics.cleanseEnemies);
				return true;
			}
		};

		if (Statics.cleanseEnemies && hitPlayer && Statics.beesOnPlayer > 0) {

			this.addAction(Actions.sequence(Actions.rotateBy(360f, 1f), completeAction));

		}
		Gdx.app.log(DogeDashCore.LOG, "beesOnPlayer " + Statics.beesOnPlayer);
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
		Statics.playerHitByBee = true;
		Statics.beesOnPlayer++;
		//
		Action completeAction = new Action() {
			public boolean act(float delta) {
				Statics.playerHitByBee = false;

				return true;
			}
		};
		addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(-30, 1.5f), Actions.moveTo(MotherDoge.playerX, MotherDoge.playerY, 1.5f)),
				completeAction));

		if (Statics.isSuperD) {
			// this.addAction(Actions.fadeOut(1f));
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
