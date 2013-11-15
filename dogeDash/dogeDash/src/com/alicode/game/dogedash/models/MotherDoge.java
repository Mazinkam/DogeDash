package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class MotherDoge extends Actor {

	private final Animation dogeWalkAnimation;
	private float dogeWalkAnimationState;
	private Array<TextureRegion> dogeWalk;
	private Array<? extends TextureRegion> dogeWalkFrames;
	private Rectangle bounds = new Rectangle();

	public MotherDoge() {

		setWidth(Assets.character.getRegionWidth());
		setHeight(Assets.character.getRegionHeight());
		dogeWalk = new Array<TextureRegion>();
		dogeWalkFrames = new Array<TextureRegion>();

		dogeWalk.add(Assets.character);
		dogeWalk.add(Assets.character2);
		dogeWalkFrames.addAll(dogeWalk);

		this.dogeWalkAnimation = new Animation(0.15f, dogeWalkFrames);
		setPosition(DogeDashCore.WIDTH / 8, DogeDashCore.HEIGHT / 2);
		setOrigin(Assets.character.getRegionWidth() / 2, Assets.character.getRegionHeight() / 2);
		setColor(0.0138f, 0.081f, 1f, 0.9f);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		updateBounds();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
		TextureRegion frame = dogeWalkAnimation.getKeyFrame(dogeWalkAnimationState += Gdx.graphics.getDeltaTime(), true);

		batch.draw(frame, getX(), getY(), frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, getWidth(), getHeight(), 1, 1, getRotation());
	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public void normalDogeMovement(float x, float y) {
		if (y >= 390)
			y = 390;
		if (y <= 0)
			y = 0;

		addAction(Actions.moveTo(x, y, 0.5f));

	}

	public Rectangle getBounds() {
		return bounds;
	}
}