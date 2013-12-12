package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.utils.GameVibrate;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class EnemyLog extends Actor {

	private Rectangle bounds = new Rectangle();
	private TextureRegion chosenType;
	private float x;
	private int logTimer = 500;

	public EnemyLog(float x, float y) {
		setWidth(Assets.hurdleLog.getRegionWidth());
		setHeight(Assets.hurdleLog.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		chosenType = Assets.hurdleLog;

		this.x = x;

	//	addAction(Actions.repeat(10, Actions.sequence(Actions.rotateBy(10f, 1f), Actions.rotateBy(-10f, 1f))));

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
	}

	private void updatePuddleTimer() {
		if (Statics.playerHitByLog) {
			logTimer--;
			if (logTimer <= 0)
				Statics.playerHitByLog = false;
		}

	}

	private void updateMovement() {
		x -= Statics.backgroundSpeed;
		addAction(Actions.moveTo(x, getY()));

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
		Statics.playerHitByLog = true;
		GameVibrate.vibrate(500);

	}

	public Rectangle getBounds() {
		return bounds;
	}

}
