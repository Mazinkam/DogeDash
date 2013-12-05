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

	enum ActorStage {
		Normal, Hit, Dead;
	}

	private final Animation dogeWalkAnimation;
	private final Animation dogeWalkHitAnimation;
	private final Animation dogeGotHit;
	private final Animation dogeSuperD;
	private float dogeWalkAnimationState;
	private Array<TextureRegion> dogeWalk, dogeWalkHit, dogeHit, dogeSuper;
	private Rectangle bounds = new Rectangle();
	private TextureRegion frame;
	public static float playerX, playerY, playerZ;

	public MotherDoge() {

		setWidth(Assets.character.getRegionWidth());
		setHeight(Assets.character.getRegionHeight());
		dogeWalk = new Array<TextureRegion>();
		dogeWalkHit = new Array<TextureRegion>();
		dogeSuper = new Array<TextureRegion>();
		dogeHit = new Array<TextureRegion>();

		dogeWalk.add(Assets.character);
		dogeWalk.add(Assets.character2);

		dogeWalkHit.add(Assets.character);
		dogeWalkHit.add(Assets.character2);

		dogeWalkHit.add(Assets.characterHit);
		dogeWalkHit.add(Assets.characterHit2);

		dogeHit.add(Assets.characterHit);
		dogeHit.add(Assets.characterHit2);

		dogeSuper.add(Assets.dogsuper1);
		dogeSuper.add(Assets.dogsuper2);

		this.dogeWalkAnimation = new Animation(0.15f, dogeWalk);
		this.dogeWalkHitAnimation = new Animation(0.15f, dogeWalkHit);
		this.dogeGotHit = new Animation(0.05f, dogeHit);
		this.dogeSuperD = new Animation(0.05f, dogeSuper);

		setPosition(DogeDashCore.WIDTH / 8, DogeDashCore.HEIGHT / 2);
		setOrigin(Assets.character.getRegionWidth() / 2, Assets.character.getRegionHeight() / 2);
		if (Statics.gameLevel == 2)
			setColor(0.15f, 0.15f, 0.4f, 1.0f);
	}

	@Override
	public void act(float delta) {
		if (Statics.gameRunning) {
			super.act(delta);
			updateBounds();
			jumpUpdate();
			updatePlayerStatus();
		}
	}

	private void updatePlayerStatus() {
	
			
		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);

		frame = dogeWalkAnimation.getKeyFrame(dogeWalkAnimationState += Gdx.graphics.getDeltaTime() / 2, true);

		if (Statics.beesOnPlayer > 2)
			frame = dogeWalkHitAnimation.getKeyFrame(dogeWalkAnimationState += Gdx.graphics.getDeltaTime() / 2, true);

		if (Statics.playerHitByBee) {
			addAction(Actions.sequence(Actions.parallel(Actions.fadeOut(0.2f), Actions.fadeIn(0.2f))));
			frame = dogeGotHit.getKeyFrame(dogeWalkAnimationState += Gdx.graphics.getDeltaTime() / 2, true);
		}
		if (Statics.playerJump) {
			frame = Assets.characterJump;
		}
		if (Statics.isSuperD) {
			frame = dogeSuperD.getKeyFrame(dogeWalkAnimationState += Gdx.graphics.getDeltaTime() / 2, true);
		}
		batch.draw(frame, getX(), getY(), frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(), frame.getRegionHeight(),
				1, 1, getRotation());

	}

	public void startJump() {
		if (!Statics.isSuperD && !Statics.playerJump) {
			playerZ = 50;
			Statics.playerJump = true;
		}

	}

	private void jumpUpdate() {
		if (playerZ >= 0) {
			Statics.playerJump = true;
			playerZ--;

		} else {
			Statics.playerJump = false;
		}

	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public void changeAnimation() {

	}

	public void normalDogeMovement(float x, float y) {
		if (y >= 390)
			y = 390;
		if (y <= 0)
			y = 0;

		playerX = x;
		playerY = y;

		addAction(Actions.moveTo(x, y, 0.5f));

	}

	public Rectangle getBounds() {
		return bounds;
	}

}