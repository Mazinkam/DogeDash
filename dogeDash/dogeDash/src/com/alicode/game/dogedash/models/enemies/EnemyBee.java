package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class EnemyBee extends Actor {

	private final Animation beeFlyingAnim;
	private float beeFlyingAnimState;
	private Array<TextureRegion> beeFlying;
	private Array<? extends TextureRegion> beeFlyingFrames;
	private Rectangle bounds = new Rectangle();

	public EnemyBee(float x, float y) {
		setWidth(Assets.enemyBee.getRegionWidth());
		setHeight(Assets.enemyBee.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		beeFlying = new Array<TextureRegion>();
		beeFlyingFrames = new Array<TextureRegion>();

		beeFlying.add(Assets.enemyBee);
		beeFlying.add(Assets.enemyBee2);
		beeFlying.add(Assets.enemyBee3);
		beeFlyingFrames.addAll(beeFlying);

		this.beeFlyingAnim = new Animation(0.15f, beeFlyingFrames);

		addAction(Actions.moveTo(-getWidth(), getY(), MathUtils.random(4.0f, 6.0f)));
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		updateBounds();
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
		addAction(Actions.fadeOut(1f));
		if (front && above)
			addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(-360, 1.5f), Actions.moveBy(200, 200, 1.5f)), Actions.removeActor()));
		if (front && !above)
			addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(360, 1.5f), Actions.moveBy(200, -200, 1.5f)), Actions.removeActor()));
		if (!front && above)
			addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(360, 1.5f), Actions.moveBy(-200, 200, 1.5f)), Actions.removeActor()));
		if (!front && !above)
			addAction(Actions.sequence(Actions.parallel(Actions.rotateBy(-360, 1.5f), Actions.moveBy(-200, -200, 1.5f)), Actions.removeActor()));
	}

	public Rectangle getBounds() {
		return bounds;
	}
}
