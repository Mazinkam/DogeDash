package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.utils.GameVibrate;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class EnemyLog extends Actor {

	private Rectangle bounds = new Rectangle();
	private TextureRegion chosenType;
	private float x;
	private boolean hitByTheD = false;

	public EnemyLog(float x, float y) {
		setWidth(Assets.hurdleLog.getRegionWidth());
		setHeight(Assets.hurdleLog.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		chosenType = Assets.hurdleLog;

		this.x = x;

		if (Statics.gameLevel == 2)
			setColor(0.15f, 0.15f, 0.4f, 1.0f);
	}

	@Override
	public void act(float delta) {
		if (Statics.state == Statics.GameState.Running) {
			super.act(delta);
			updateMovement();
			updateBounds();
			updatePuddleTimer();
		}
		if (!Statics.objectsAlive) {
			this.remove();
		}
	}

	private void updatePuddleTimer() {
		if (Statics.playerHitByLog) {
			Statics.logTimer--;
			if (Statics.logTimer <= 0) {
				Statics.playerHitByLog = false;
				Statics.logTimer = 600;
			}
		}

	}

	private void updateMovement() {
		if (!hitByTheD) {
			x -= Statics.backgroundSpeed;
			addAction(Actions.moveTo(x, getY()));
		}

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

	public void playerHit(boolean front, boolean above) {

		if (!Statics.isSuperD) {
			Statics.playerHitByLog = true;
			GameVibrate.vibrate(500);

			Statics.playerHitAnimation = true;
			Action completeAction = new Action() {
				public boolean act(float delta) {
					Statics.playerHitAnimation = false;

					return true;
				}
			};
			addAction(Actions.sequence(Actions.parallel(Actions.scaleBy(0.2f, 0.2f), Actions.delay(.5f), Actions.scaleTo(1, 1), completeAction)));
		}

		if (Statics.isSuperD) {
			clearActions();
			hitByTheD = true;

			if (front && above)
				addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(-360, 1.5f), Actions.moveBy(200, 200, 1.5f)), Actions.removeActor()));
			if (front && !above)
				addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(360, 1.5f), Actions.moveBy(200, -200, 1.5f)), Actions.removeActor()));
			if (!front && above)
				addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(360, 1.5f), Actions.moveBy(-200, 200, 1.5f)), Actions.removeActor()));
			if (!front && !above)
				addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(-360, 1.5f), Actions.moveBy(-200, -200, 1.5f)), Actions.removeActor()));
		}

	}

	public Rectangle getBounds() {
		return bounds;
	}

}
