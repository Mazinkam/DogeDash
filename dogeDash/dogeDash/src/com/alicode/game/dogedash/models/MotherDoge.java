package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class MotherDoge extends Actor {

	enum ActorStage{
		Normal, Hit, Dead;
	}
	
	private final Animation dogeWalkAnimation;
	private float dogeWalkAnimationState;
	private Array<TextureRegion> dogeWalk, dogeWalkHit, dogeSuper;
	private Array<? extends TextureRegion> dogeWalkFrames, dogeWalkHitFrames, dogeSuperFrames;
	private Rectangle bounds = new Rectangle();

	public MotherDoge() {
		
		setWidth(Assets.character.getRegionWidth());
		setHeight(Assets.character.getRegionHeight());
		dogeWalk = new Array<TextureRegion>();
		dogeWalkFrames = new Array<TextureRegion>();

		dogeWalkHit = new Array<TextureRegion>();
		dogeWalkHitFrames = new Array<TextureRegion>();
		
		dogeSuper = new Array<TextureRegion>();
		dogeSuperFrames = new Array<TextureRegion>();
		
		dogeWalk.add(Assets.character);
		dogeWalk.add(Assets.character2);
		dogeWalkFrames.addAll(dogeWalk);
		
		dogeWalkHit.add(Assets.character);
		dogeWalkHit.add(Assets.character2);
		dogeWalkHit.add(Assets.characterHit);
		dogeWalkHit.add(Assets.characterHit2);
		dogeWalkHitFrames.addAll(dogeWalkHit);
		
		dogeSuper.add(Assets.dogsuper1);
		dogeSuper.add(Assets.dogsuper2);
		dogeSuperFrames.addAll(dogeSuper);

		this.dogeWalkAnimation = new Animation(0.15f, dogeWalkFrames);
		setPosition(DogeDashCore.WIDTH / 8, DogeDashCore.HEIGHT / 2);
		setOrigin(Assets.character.getRegionWidth() / 2, Assets.character.getRegionHeight() / 2);
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
		TextureRegion frame = dogeWalkAnimation.getKeyFrame(dogeWalkAnimationState += Gdx.graphics.getDeltaTime()/2, true);

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