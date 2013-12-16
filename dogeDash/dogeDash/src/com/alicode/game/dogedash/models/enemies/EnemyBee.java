package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.utils.GameVibrate;
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
	private boolean hitByTheD = false;

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
		if (Statics.state == Statics.GameState.Running) {
			super.act(delta);
			updateMovement();
			updateBounds();
		}

		if (!Statics.enemiesAlive) {
			this.remove();
		}
	}

	private void updateMovement() {

		normalMovement();
		stickOnPlayer();
		clearTheAir();

	}

	private void normalMovement() {
		if (!hitPlayer && !hitByTheD) {
			enemyX -= Statics.enemySpeed;
			addAction(Actions.moveTo(enemyX, getY()));
		}

	}

	private void stickOnPlayer() {
		if (hitPlayer && !Statics.cleanseEnemies) {
			if (!Statics.isSuperD) {
				float randomX = (MotherDoge.playerX - 100) + (int) (Math.random() * (MotherDoge.playerX + 200));
				float randomY = (MotherDoge.playerY - 120) + (int) (Math.random() * (MotherDoge.playerY + 160));

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
				Statics.enemiesOnPlayer--;
				if (Statics.enemiesOnPlayer < 0) {
					Statics.enemiesOnPlayer = 0;
				}
				this.actor.remove();
				return true;
			}
		};

		if (Statics.cleanseEnemies && hitPlayer && Statics.enemiesOnPlayer > 0) {

			this.addAction(Actions.sequence(Actions.rotateBy(360f, 1f), completeAction));

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
		if (!Statics.isSuperD) {
			hitPlayer = true;
			Statics.playerHitByBee = true;
			Statics.enemiesOnPlayer++;
			GameVibrate.vibrate(500);

			Action completeAction = new Action() {
				public boolean act(float delta) {
					Statics.playerHitByBee = false;

					return true;
				}
			};
			addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(-30, 1.5f), Actions.moveTo(MotherDoge.playerX, MotherDoge.playerY, 1.5f)), completeAction));
		}

		if (Statics.isSuperD) {
			clearActions();
			hitByTheD = true;
			if (front && above)
				addAction(Actions.sequence(Actions.parallel(Actions.fadeOut(1), Actions.rotateBy(-360, 1.5f), Actions.moveBy(200, 200, 1.5f)), Actions.removeActor()));
			if (front && !above)
				addAction(Actions.sequence(Actions.parallel(Actions.fadeOut(1), Actions.rotateBy(360, 1.5f), Actions.moveBy(200, -200, 1.5f)), Actions.removeActor()));
			if (!front && above)
				addAction(Actions.sequence(Actions.parallel(Actions.fadeOut(1), Actions.rotateBy(360, 1.5f), Actions.moveBy(-200, 200, 1.5f)), Actions.removeActor()));
			if (!front && !above)
				addAction(Actions.sequence(Actions.parallel(Actions.fadeOut(1), Actions.rotateBy(-360, 1.5f), Actions.moveBy(-200, -200, 1.5f)), Actions.removeActor()));
		}

	}

	public Rectangle getBounds() {
		return bounds;
	}
}
