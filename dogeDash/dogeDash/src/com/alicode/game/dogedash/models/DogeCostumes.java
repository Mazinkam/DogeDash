package com.alicode.game.dogedash.models;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.Statics.GameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class DogeCostumes extends Actor {

	public float costumeX, costumeY;

	private ParticleEffect waterDrops, mudDrops;

	private Array<TextureRegion> noseCostumeNormal;
	private Array<TextureRegion> headCostumeNormal;
	private Array<TextureRegion> backCostumeNormal;
	private Array<TextureRegion> eyesCostumeNormal;

	private Array<TextureRegion> noseCostumeJump;
	private Array<TextureRegion> headCostumeJump;
	private Array<TextureRegion> backCostumeJump;
	private Array<TextureRegion> eyesCostumeJump;

	private boolean reverseControls = false;

	public DogeCostumes() {
		defineCostumes();
		setPosition(DogeDashCore.WIDTH / 8,  DogeDashCore.HEIGHT / 2);
		setOrigin(Assets.character.getRegionWidth() / 2, Assets.character.getRegionHeight() / 2);

	}

	private void defineCostumes() {

		noseCostumeNormal = new Array<TextureRegion>();
		headCostumeNormal = new Array<TextureRegion>();
		backCostumeNormal = new Array<TextureRegion>();
		eyesCostumeNormal = new Array<TextureRegion>();

		noseCostumeJump = new Array<TextureRegion>();
		headCostumeJump = new Array<TextureRegion>();
		backCostumeJump = new Array<TextureRegion>();
		eyesCostumeJump = new Array<TextureRegion>();

		// jump
		backCostumeJump.add(Assets.acc_angelj);
		backCostumeJump.add(Assets.acc_devilwingsj);
		backCostumeJump.add(Assets.acc_keepoutj);

		noseCostumeJump.add(Assets.acc_moustachej);
		noseCostumeJump.add(Assets.acc_clownnosej);

		eyesCostumeJump.add(Assets.acc_shadesj);
		eyesCostumeJump.add(Assets.acc_monoclej);
		eyesCostumeJump.add(Assets.acc_hipsterj);
		eyesCostumeJump.add(Assets.acc_unibrowj);

		headCostumeJump.add(Assets.acc_tophatj);
		headCostumeJump.add(Assets.acc_hornsj);
		headCostumeJump.add(Assets.acc_haloj);
		headCostumeJump.add(Assets.acc_pumpkinj);
		headCostumeJump.add(Assets.acc_santahatj);
		headCostumeJump.add(Assets.acc_policehatj);

		// normal
		backCostumeNormal.add(Assets.acc_angel);
		backCostumeNormal.add(Assets.acc_devilwings);
		backCostumeNormal.add(Assets.acc_keepout);

		noseCostumeNormal.add(Assets.acc_moustache);
		noseCostumeNormal.add(Assets.acc_clownnose);

		eyesCostumeNormal.add(Assets.acc_shades);
		eyesCostumeNormal.add(Assets.acc_monocle);
		eyesCostumeNormal.add(Assets.acc_hipster);
		eyesCostumeNormal.add(Assets.acc_unibrow);

		headCostumeNormal.add(Assets.acc_tophat);
		headCostumeNormal.add(Assets.acc_horns);
		headCostumeNormal.add(Assets.acc_halo);
		headCostumeNormal.add(Assets.acc_pumpkin);
		headCostumeNormal.add(Assets.acc_santahat);
		headCostumeNormal.add(Assets.acc_policehat);

	}

	@Override
	public void act(float delta) {
		if (Statics.state == Statics.GameState.Running) {
			super.act(delta);
			dogeHitByLog();
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
		if (!Statics.isSuperD) {

			if (Statics.playerJump) {

				if (Statics.noseCostume >= 0)
					batch.draw(noseCostumeJump.get(Statics.noseCostume), getX(), getY(), noseCostumeJump.get(Statics.noseCostume).getRegionWidth() / 2, noseCostumeJump.get(Statics.noseCostume)
							.getRegionHeight() / 2, noseCostumeJump.get(Statics.noseCostume).getRegionWidth(), noseCostumeJump.get(Statics.noseCostume).getRegionHeight(), 1, 1, getRotation());
				if (Statics.backCostume >= 0)
					batch.draw(backCostumeJump.get(Statics.backCostume), getX(), getY(), backCostumeJump.get(Statics.backCostume).getRegionWidth() / 2, backCostumeJump.get(Statics.backCostume)
							.getRegionHeight() / 2, backCostumeJump.get(Statics.backCostume).getRegionWidth(), backCostumeJump.get(Statics.backCostume).getRegionHeight(), 1, 1, getRotation());
				if (Statics.eyeCostume >= 0)
					batch.draw(eyesCostumeJump.get(Statics.eyeCostume), getX(), getY(), eyesCostumeJump.get(Statics.eyeCostume).getRegionWidth() / 2, eyesCostumeJump.get(Statics.eyeCostume)
							.getRegionHeight() / 2, eyesCostumeJump.get(Statics.eyeCostume).getRegionWidth(), eyesCostumeJump.get(Statics.eyeCostume).getRegionHeight(), 1, 1, getRotation());
				if (Statics.headCostume >= 0)
					batch.draw(headCostumeJump.get(Statics.headCostume), getX(), getY(), headCostumeJump.get(Statics.headCostume).getRegionWidth() / 2, headCostumeJump.get(Statics.headCostume)
							.getRegionHeight() / 2, headCostumeJump.get(Statics.headCostume).getRegionWidth(), headCostumeJump.get(Statics.headCostume).getRegionHeight(), 1, 1, getRotation());

			}

			if (!Statics.playerJump) {
				if (Statics.noseCostume >= 0)
					batch.draw(noseCostumeNormal.get(Statics.noseCostume), getX(), getY(), noseCostumeNormal.get(Statics.noseCostume).getRegionWidth() / 2, noseCostumeNormal.get(Statics.noseCostume)
							.getRegionHeight() / 2, noseCostumeNormal.get(Statics.noseCostume).getRegionWidth(), noseCostumeNormal.get(Statics.noseCostume).getRegionHeight(), 1, 1, getRotation());
				if (Statics.backCostume >= 0)
					batch.draw(backCostumeNormal.get(Statics.backCostume), getX(), getY(), backCostumeNormal.get(Statics.backCostume).getRegionWidth() / 2, backCostumeNormal.get(Statics.backCostume)
							.getRegionHeight() / 2, backCostumeNormal.get(Statics.backCostume).getRegionWidth(), backCostumeNormal.get(Statics.backCostume).getRegionHeight(), 1, 1, getRotation());
				if (Statics.eyeCostume >= 0)
					batch.draw(eyesCostumeNormal.get(Statics.eyeCostume), getX(), getY(), eyesCostumeNormal.get(Statics.eyeCostume).getRegionWidth() / 2, eyesCostumeNormal.get(Statics.eyeCostume)
							.getRegionHeight() / 2, eyesCostumeNormal.get(Statics.eyeCostume).getRegionWidth(), eyesCostumeNormal.get(Statics.eyeCostume).getRegionHeight(), 1, 1, getRotation());
				if (Statics.headCostume >= 0)
					batch.draw(headCostumeNormal.get(Statics.headCostume), getX(), getY(), headCostumeNormal.get(Statics.headCostume).getRegionWidth() / 2, headCostumeNormal.get(Statics.headCostume)
							.getRegionHeight() / 2, headCostumeNormal.get(Statics.headCostume).getRegionWidth(), headCostumeNormal.get(Statics.headCostume).getRegionHeight(), 1, 1, getRotation());
			}

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

	public void normalCostumeMovement(float playerX, float playerY) {
		if (!Statics.playerJump) {

			costumeX = playerX;
			costumeY = playerY;

			if (Statics.playerHitByLog) {
				costumeX = playerX;
				costumeY = -playerY + 480;
			}

			if (costumeY >= 390)
				costumeY = 390;

			if (costumeY <= 0)
				costumeY = 0;

			if (costumeX > 110)
				MotherDoge.playerX--;

			if (costumeX <= 110)
				costumeX++;

		}
		addAction(Actions.parallel(Actions.moveTo(costumeX, costumeY, 0.5f)));

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
				addAction(Actions.parallel(Actions.sequence(Actions.rotateTo(-180, 1), completeAction)));
			}
			if (reverseControls && !Statics.playerHitByLog) {
				reverseControls = false;
			}

		}
	}

}
