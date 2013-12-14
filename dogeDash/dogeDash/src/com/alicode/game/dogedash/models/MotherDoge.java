package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.Statics.GameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class MotherDoge extends Actor {

	public static float playerX, playerY, playerZ;

	private final Animation dogeWalkAnimation;
	private final Animation dogeWalkHitAnimation;
	private final Animation dogeGotHit;
	private final Animation dogeSuperD;
	private final Animation dogeSuperDEffect;

	private float dogeWalkAnimationState;

	private Array<TextureRegion> dogeWalk, dogeWalkHit, dogeHit, dogeSuper, dogeSuperEffect;

	private Rectangle bounds = new Rectangle();

	private TextureRegion frame;

	private ParticleEffect waterDrops, mudDrops;

	private boolean reverseControls = false;

	public MotherDoge() {

		setWidth(Assets.character.getRegionWidth());
		setHeight(Assets.character.getRegionHeight());
		dogeWalk = new Array<TextureRegion>();
		dogeWalkHit = new Array<TextureRegion>();
		dogeSuper = new Array<TextureRegion>();
		dogeHit = new Array<TextureRegion>();
		dogeSuperEffect = new Array<TextureRegion>();

		dogeSuperEffect.add(Assets.energy1);
		dogeSuperEffect.add(Assets.energy2);
		dogeSuperEffect.add(Assets.energy3);

		dogeWalk.add(Assets.character);
		dogeWalk.add(Assets.character2);

		waterDrops = new ParticleEffect();
		waterDrops.load(Gdx.files.internal("particles/waterDrops"), Gdx.files.internal("particles"));

		mudDrops = new ParticleEffect();
		mudDrops.load(Gdx.files.internal("particles/mudDrops"), Gdx.files.internal("particles"));

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
		this.dogeSuperD = new Animation(0.15f, dogeSuper);
		this.dogeSuperDEffect = new Animation(0.15f, dogeSuperEffect);

		setPosition(DogeDashCore.WIDTH / 8, DogeDashCore.HEIGHT / 2);
		setOrigin(Assets.character.getRegionWidth() / 2, Assets.character.getRegionHeight() / 2);
		if (Statics.gameLevel == 2)
			setColor(0.15f, 0.15f, 0.4f, 1.0f);
	}

	@Override
	public void act(float delta) {
		if (Statics.state == Statics.GameState.Running) {
			super.act(delta);
			updateBounds();
			jumpUpdate();
			updatePlayerStatus();
			dogeHitByLog();
		}
	}

	private void updatePlayerStatus() {

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
		if (!Statics.isSuperD) {
			frame = dogeWalkAnimation.getKeyFrame(dogeWalkAnimationState += Gdx.graphics.getDeltaTime() / 2, true);

			if (Statics.beesOnPlayer > 2)
				frame = dogeWalkHitAnimation.getKeyFrame(dogeWalkAnimationState += Gdx.graphics.getDeltaTime() / 2, true);

			if (Statics.playerHitByBee || Statics.playerHitAnimation) {
				addAction(Actions.sequence(Actions.parallel(Actions.fadeOut(0.2f), Actions.fadeIn(0.2f))));
				frame = dogeGotHit.getKeyFrame(dogeWalkAnimationState += Gdx.graphics.getDeltaTime() / 2, true);
			}
			if (Statics.playerJump && Statics.state == GameState.Running) {
				frame = Assets.characterJump;
			}

			if (Statics.state == GameState.GameOver) {
				frame = Assets.characterDie;
			}
			batch.draw(frame, getX(), getY(), frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(),
					frame.getRegionHeight(), 1, 1, getRotation());
			if (Statics.playerHitByPuddle)
				updateWaterParticles(batch);
			if (Statics.playerHitByMud)
				updateMudParticles(batch);
		}
		if (Statics.isSuperD) {
			Statics.cleanseEnemies = true;
			frame = dogeSuperD.getKeyFrame(dogeWalkAnimationState += Gdx.graphics.getDeltaTime() / 2, true);
			batch.draw(frame, getX(), getY(), frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(),
					frame.getRegionHeight(), 1, 1, getRotation());

			frame = dogeSuperDEffect.getKeyFrame(dogeWalkAnimationState += Gdx.graphics.getDeltaTime() / 2, true);
			batch.draw(frame, getX() - 170, getY() - 100, frame.getRegionWidth() / 2, frame.getRegionHeight() / 2, frame.getRegionWidth(),
					frame.getRegionHeight(), 1, 1, getRotation());
		}

	}

	private void updateMudParticles(SpriteBatch batch) {
		mudDrops.start();

		mudDrops.setPosition(getX() + Assets.character.getRegionWidth(), getY());

		for (int i = 0; i < mudDrops.getEmitters().size; i++) {
			mudDrops.getEmitters().get(i).getAngle().setLow(180);
			mudDrops.getEmitters().get(i).getAngle().setHigh(180);
		}

		mudDrops.draw(batch);
		mudDrops.update(Gdx.graphics.getDeltaTime());

	}

	private void updateWaterParticles(SpriteBatch batch) {
		waterDrops.start();

		waterDrops.setPosition(getX() + Assets.character.getRegionWidth(), getY());

		for (int i = 0; i < waterDrops.getEmitters().size; i++) {
			waterDrops.getEmitters().get(i).getAngle().setLow(180);
			waterDrops.getEmitters().get(i).getAngle().setHigh(180);
		}

		waterDrops.draw(batch);
		waterDrops.update(Gdx.graphics.getDeltaTime());

	}

	public void startJump() {
		if (!Statics.isSuperD && !Statics.playerJump && Statics.playerJumpCooldown < 0) {
			playerZ = 50;
			Statics.playerJump = true;
		}

	}

	private void jumpUpdate() {
		if (playerZ >= 0) {
			Statics.playerJump = true;
			playerZ--;
			Statics.playerJumpCooldown = 100;

		} else {
			Statics.playerJump = false;
			Statics.playerJumpCooldown--;
		}

	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public void changeAnimation() {

	}

	public void normalDogeMovement(float playerX, float playerY) {
		if (!Statics.playerJump) {

			MotherDoge.playerX = playerX;
			MotherDoge.playerY = playerY;

			if (Statics.playerHitByLog) {
				MotherDoge.playerX = playerX;
				MotherDoge.playerY = -playerY + 480;
			}

			if (MotherDoge.playerY >= 390)
				MotherDoge.playerY = 390;
			if (MotherDoge.playerY <= 0)
				MotherDoge.playerY = 0;

			if (MotherDoge.playerX > 110)
				MotherDoge.playerX--;

			if (MotherDoge.playerX <= 110)
				MotherDoge.playerX++;

		}
		addAction(Actions.parallel(Actions.moveTo(playerX, MotherDoge.playerY, 0.5f)));

	}

	public void dogeHitByLog() {
		if (!Statics.isSuperD) {
			if (!reverseControls && Statics.playerHitByLog) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						Statics.playerHitAnimation = false;
						reverseControls = true;
						return true;
					}
				};
				addAction(Actions.parallel(Actions.sequence(Actions.rotateTo(-200, 1), completeAction)));
			}
			if (reverseControls && Statics.playerHitByLog) {
				addAction(Actions.sequence(Actions.rotateTo(-200, 10)));

			}
			if (reverseControls && !Statics.playerHitByLog) {
				reverseControls = false;
			}

		}
	}

	public Rectangle getBounds() {
		return bounds;
	}

}