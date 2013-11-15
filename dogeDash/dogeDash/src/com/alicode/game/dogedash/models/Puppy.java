package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class Puppy extends Actor {

	private final Animation pupWalkingAnim;
	private float pupWalkingAnimState;
	private Array<TextureRegion> pupWalkingRed, pupWalkingBlack, pupWalkingCream, pupWalkingBlue;
	private Array<? extends TextureRegion> pupWalkingFrames;
	private Rectangle bounds = new Rectangle();

	public Puppy(float x, float y) {
		int randomNum = 1 + (int) (Math.random() * 4);
		setWidth(Assets.redPup.getRegionWidth());
		setHeight(Assets.redPup.getRegionHeight());
		setPosition(x, y - getHeight() / 2);

		pupWalkingRed = new Array<TextureRegion>();
		pupWalkingRed.add(Assets.redPup);
		pupWalkingRed.add(Assets.redPup2);

		pupWalkingBlack = new Array<TextureRegion>();
		pupWalkingBlack.add(Assets.blackPup);
		pupWalkingBlack.add(Assets.blackPup2);

		pupWalkingCream = new Array<TextureRegion>();
		pupWalkingCream.add(Assets.creamPup);
		pupWalkingCream.add(Assets.creamPup2);

		pupWalkingBlue = new Array<TextureRegion>();
		pupWalkingBlue.add(Assets.bluePup);
		pupWalkingBlue.add(Assets.bluePup2);

		pupWalkingFrames = new Array<TextureRegion>();

		if (randomNum == 1)
			pupWalkingFrames.addAll(pupWalkingRed);
		if (randomNum == 2)
			pupWalkingFrames.addAll(pupWalkingBlack);
		if (randomNum == 3)
			pupWalkingFrames.addAll(pupWalkingCream);
		if (randomNum == 4)
			pupWalkingFrames.addAll(pupWalkingBlue);

		this.pupWalkingAnim = new Animation(0.15f, pupWalkingFrames);

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

		TextureRegion frame = pupWalkingAnim.getKeyFrame(pupWalkingAnimState += Gdx.graphics.getDeltaTime() , true);

		batch.draw(frame, getX(), getY(), frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, getWidth(), getHeight(), 1, 1, getRotation());
	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public void playerHit(boolean front, boolean above) {
		clearActions();
		addAction(Actions.fadeOut(1f));
//		if (front && above)
//			addAction(Actions.sequence(Actions.parallel(Actions.moveBy(200, 0, 1.5f)), Actions.removeActor()));
//		if (front && !above)
//			addAction(Actions.sequence(Actions.parallel(Actions.moveBy(200, 0, 1.5f)), Actions.removeActor()));
//		if (!front && above)
//			addAction(Actions.sequence(Actions.parallel(Actions.moveBy(-200, 0, 1.5f)), Actions.removeActor()));
//		if (!front && !above)
			addAction(Actions.sequence(Actions.parallel(Actions.moveBy(-200, 0, 1.5f)), Actions.removeActor()));
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
}
