package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.utils.GameVibrate;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class EnemyMud extends Actor {

	private Rectangle bounds = new Rectangle();
	private TextureRegion chosenType;
	private float x;

	public EnemyMud(float x, float y) {
		int randomNum = 1 + (int) (Math.random() * 2);
		setWidth(Assets.gameMud.getRegionWidth());
		setHeight(Assets.gameMud.getRegionHeight());


		if (randomNum == 1)
			chosenType = Assets.gameMud;
		if (randomNum == 2)
			chosenType = Assets.gameMud2;

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
	//	Statics.playerHitByMud = true;
		Statics.playerHitAnimation = true;
		GameVibrate.vibrate(500);
		Action completeAction = new Action() {
			public boolean act(float delta) {
				Statics.playerHitAnimation = false;

				return true;
			}
		};
		addAction(Actions.sequence(Actions.parallel(Actions.scaleBy(0.2f, 0.2f), Actions.delay(.5f),Actions.scaleTo(1, 1),
				completeAction)));
		
		Gdx.app.log(DogeDashCore.LOG, "playerHitByMud " + Statics.playerHitByMud);
	}

	public Rectangle getBounds() {
		return bounds;
	}

}
