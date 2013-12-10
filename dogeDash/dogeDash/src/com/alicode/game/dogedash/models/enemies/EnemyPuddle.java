package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
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
	private int puddleTimer = 500;

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

		// addAction(Actions.repeat(10, Actions.sequence(Actions.rotateBy(10f,
		// 1f), Actions.rotateBy(-10f, 1f))));

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
		if (Statics.playerHitByPuddle) {
			puddleTimer--;
			if (puddleTimer <= 0)
				Statics.playerHitByPuddle = false;
			Gdx.app.log(DogeDashCore.LOG, "playerHitByPuddle " + Statics.playerHitByPuddle);
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
		Statics.playerHitByMud = false;
		Statics.playerHitByPuddle = true;

		Gdx.app.log(DogeDashCore.LOG, "playerHitByMud " + Statics.playerHitByMud);
		Gdx.app.log(DogeDashCore.LOG, "playerHitByPuddle " + Statics.playerHitByPuddle);
	}

	public Rectangle getBounds() {
		return bounds;
	}

}
