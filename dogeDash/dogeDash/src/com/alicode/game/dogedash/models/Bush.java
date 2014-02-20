package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.Consts;
import com.alicode.game.dogedash.Statics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Bush extends Actor {

	private TextureRegion chosenType;
	private Rectangle bounds = new Rectangle();
	private float x;
	private boolean hitByTheD = false;

	public Bush(float x, float y) {
		setWidth(Assets.gameBush.getRegionWidth());
		setHeight(Assets.gameBush.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		chosenType = Assets.gameBush;
		this.x = x;

		// if (Statics.gameLevel == 2)
		// setColor(0.15f, 0.15f, 0.4f, 1.0f);
	}

	@Override
	public void act(float delta) {
		if (Statics.state == Statics.GameState.Running) {
			super.act(delta);
			updateMovement();
			updateBounds();
		}

		if (!Statics.bushesAlive) {
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

	public void playerHit(boolean front, boolean above) {
		if (!Statics.isSuperD) {

			if (Statics.enemiesOnPlayer > 0) {
				Statics.cleanseEnemies = true;
				Statics.playerVisionRadius = Consts.PLAYER_VISION;
			}

			addAction(Actions.sequence(Actions.repeat(10, Actions.sequence(Actions.parallel(Actions.rotateTo(-10f, 0.1f)), Actions.rotateTo(10f, 0.1f))), Actions.removeActor()));
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

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public Rectangle getBounds() {
		return bounds;
	}

}