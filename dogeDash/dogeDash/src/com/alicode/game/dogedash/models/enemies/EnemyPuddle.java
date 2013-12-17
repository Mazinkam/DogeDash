package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.Consts;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.utils.GameVibrate;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class EnemyPuddle extends Actor {

	private Rectangle bounds = new Rectangle();
	private TextureRegion chosenType;
	private float x;
	private boolean hitByTheD = false;

	public EnemyPuddle(float x, float y) {
		int randomNum = 1 + (int) (Math.random() * 2);
		setWidth(Assets.gamePuddle.getRegionWidth());
		setHeight(Assets.gamePuddle.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		if (randomNum == 1)
			chosenType = Assets.gamePuddle;
		if (randomNum == 2)
			chosenType = Assets.gamePuddle2;

		this.x = x;

//		if (Statics.gameLevel == 2)
//			setColor(0.15f, 0.15f, 0.4f, 1.0f);
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
		if (Statics.playerHitByPuddle) {
			Statics.puddleTimer--;
			if (Statics.puddleTimer <= 0) {
				Statics.playerHitByPuddle = false;
				Statics.puddleTimer = Consts.PUDDLE_TIMER;
			}
		}
		if (!Statics.objectsAlive) {
			this.remove();
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

		batch.draw(chosenType, getX(), getY(), chosenType.getRegionWidth() / 2, chosenType.getRegionHeight() / 2, getWidth(), getHeight(), 1, 1, getRotation());

	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public void playerHit(boolean front, boolean above) {

		if (!Statics.isSuperD) {
			Statics.playerHitByMud = false;
			Statics.playerHitByPuddle = true;
			GameVibrate.vibrate(500);
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
