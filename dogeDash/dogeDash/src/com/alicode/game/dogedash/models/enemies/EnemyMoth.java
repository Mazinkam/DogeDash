package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.Statics;
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

public class EnemyMoth extends Actor {

	private final Animation mothFlyingAnim;
	private float mothFlyingAnimState;
	private Array<TextureRegion> mothFlying;
	private Array<? extends TextureRegion> mothFlyingFrames;
	private Rectangle bounds = new Rectangle();

	public EnemyMoth(float x, float y) {
		setWidth(Assets.enemyMoth.getRegionWidth());
		setHeight(Assets.enemyMoth.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		mothFlying = new Array<TextureRegion>();
		mothFlyingFrames = new Array<TextureRegion>();

		mothFlying.add(Assets.enemyMoth);
		mothFlying.add(Assets.enemyMoth2);
		mothFlying.add(Assets.enemyMoth3);
		mothFlyingFrames.addAll(mothFlying);

		this.mothFlyingAnim = new Animation(0.15f, mothFlyingFrames);

		addAction(Actions.moveTo(-getWidth(), getY(), MathUtils.random(4.0f, 6.0f)));
		if (Statics.gameLevel == 2)
			setColor(0.15f, 0.15f, 0.4f, 1.0f);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		updateBounds();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);

		TextureRegion frame = mothFlyingAnim.getKeyFrame(mothFlyingAnimState += Gdx.graphics.getDeltaTime() * 2, true);

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
