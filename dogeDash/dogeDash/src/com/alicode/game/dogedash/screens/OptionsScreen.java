package com.alicode.game.dogedash.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.Consts;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.models.WindowOverlay;
import com.alicode.game.dogedash.utils.GameAudio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class OptionsScreen implements Screen {

	private Image imageMenuBg, imageBackButton, imageMenuMomNosePaw, imageMenuMomBody, imageMenuCreamPupBody, imageMenuCreamPupPaw,
			imageMenuCreamPupPaw2, imageMenuBluePup;

	private Image imageOptionsTitle, imageOptionsText, imageMusicButton, imageSoundButton, imageVibrationButton;

	private DogeDashCore game;
	private Drawable tempDrawable;
	private Stage stage;
	public static int isSoundOn, isMusicOn, isVibrationOn;
	private WindowOverlay winOverlay;
	private InputMultiplexer inputMultiplexer;

	public OptionsScreen(DogeDashCore game) {
		this.game = game;
		winOverlay = new WindowOverlay();
		stage = new Stage();
		inputMultiplexer = new InputMultiplexer(stage);

		isSoundOn = DogeDashCore.db.getSettings(1).getSoundSettings();
		isMusicOn = DogeDashCore.db.getSettings(1).getMusicSettings();
		isVibrationOn = DogeDashCore.db.getSettings(1).getVibrationSettings();

		Gdx.app.log(Consts.LOG, "isSoundOn: " + isSoundOn + " isMusicOn: " + isMusicOn + " isVibrationOn " + isVibrationOn);
	}

	@Override
	public void show() {

		initBackground();
		initForeground();
		initInput();
		initActors();

	}

	private void initBackground() {
		tempDrawable = new TextureRegionDrawable(Assets.menu);
		imageMenuBg = new Image(tempDrawable, Scaling.stretch);
		imageMenuBg.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mombody);
		imageMenuMomBody = new Image(tempDrawable, Scaling.stretch);
		imageMenuMomBody.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mom_nose_paw);
		imageMenuMomNosePaw = new Image(tempDrawable, Scaling.stretch);
		imageMenuMomNosePaw.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mombody);
		imageMenuMomBody = new Image(tempDrawable, Scaling.stretch);
		imageMenuMomBody.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_bluepup);
		imageMenuBluePup = new Image(tempDrawable);
		imageMenuBluePup.setX(460);
		imageMenuBluePup.setY(45);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_body);
		imageMenuCreamPupBody = new Image(tempDrawable);
		imageMenuCreamPupBody.setX(200);
		imageMenuCreamPupBody.setY(-10);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_paw);
		imageMenuCreamPupPaw = new Image(tempDrawable);
		imageMenuCreamPupPaw.setX(200);
		imageMenuCreamPupPaw.setY(10);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_paw2);
		imageMenuCreamPupPaw2 = new Image(tempDrawable);
		imageMenuCreamPupPaw2.setX(290);
		imageMenuCreamPupPaw2.setY(10);

		tempDrawable = new TextureRegionDrawable(Assets.back);

		imageBackButton = new Image(tempDrawable);
		imageBackButton.setX(660);
		imageBackButton.setY(20);

	}

	private void initForeground() {
		tempDrawable = new TextureRegionDrawable(Assets.options_title);
		imageOptionsTitle = new Image(tempDrawable);
		imageOptionsTitle.setX(50);
		imageOptionsTitle.setY(400);

		tempDrawable = new TextureRegionDrawable(Assets.options_txt);
		imageOptionsText = new Image(tempDrawable);
		imageOptionsText.setX(70);
		imageOptionsText.setY(340);

		if (isSoundOn == 1)
			tempDrawable = new TextureRegionDrawable(Assets.soundon);
		else
			tempDrawable = new TextureRegionDrawable(Assets.soundoff);
		imageSoundButton = new Image(tempDrawable);
		imageSoundButton.setX(30);
		imageSoundButton.setY(250);

		if (isMusicOn == 1)
			tempDrawable = new TextureRegionDrawable(Assets.musicon);
		else
			tempDrawable = new TextureRegionDrawable(Assets.musicoff);
		imageMusicButton = new Image(tempDrawable);
		imageMusicButton.setX(30);
		imageMusicButton.setY(200);

		if (isVibrationOn == 1)
			tempDrawable = new TextureRegionDrawable(Assets.vibrationon);
		else
			tempDrawable = new TextureRegionDrawable(Assets.vibrationoff);
		imageVibrationButton = new Image(tempDrawable);
		imageVibrationButton.setX(30);
		imageVibrationButton.setY(150);

	}

	private void initInput() {
		InputProcessor backProcessor = new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {

				if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK))

					game.setScreen(new MenuScreen(game));
				// Gdx.app.exit();
				return false;
			}
		};
		inputMultiplexer.addProcessor(backProcessor);
		// inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);

		imageSoundButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						// game.setScreen(new SplashScreen(game));
						return true;
					}
				};

				imageSoundButton.setOrigin(imageSoundButton.getWidth() / 4, imageSoundButton.getHeight() / 2);
				imageSoundButton.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageSoundButton.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
				isSoundOn ^= 1;
				if (isSoundOn == 1)
					tempDrawable = new TextureRegionDrawable(Assets.soundon);
				else
					tempDrawable = new TextureRegionDrawable(Assets.soundoff);
				imageSoundButton.setDrawable(tempDrawable);
				GameAudio.dogeBark();

			}
		});

		imageMusicButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						// game.setScreen(new SplashScreen(game));
						return true;
					}
				};

				imageMusicButton.setOrigin(imageMusicButton.getWidth() / 4, imageMusicButton.getHeight() / 2);
				imageMusicButton.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageMusicButton.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
				isMusicOn ^= 1;
				if (isMusicOn == 1)
					tempDrawable = new TextureRegionDrawable(Assets.musicon);
				else
					tempDrawable = new TextureRegionDrawable(Assets.musicoff);
				imageMusicButton.setDrawable(tempDrawable);
				GameAudio.dogeBark();

			}
		});

		imageVibrationButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						// game.setScreen(new SplashScreen(game));
						return true;
					}
				};

				imageVibrationButton.setOrigin(imageVibrationButton.getWidth() / 4, imageVibrationButton.getHeight() / 2);
				imageVibrationButton.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageVibrationButton.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f),
						rotateBy(-5, 0.3f, Interpolation.swing), completeAction)));
				isVibrationOn ^= 1;
				if (isVibrationOn == 1)
					tempDrawable = new TextureRegionDrawable(Assets.vibrationon);
				else
					tempDrawable = new TextureRegionDrawable(Assets.vibrationoff);
				imageVibrationButton.setDrawable(tempDrawable);
				GameAudio.dogeBark();
			//	GameVibrate.vibrate(500);
			}

		});

		imageBackButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {

//						DogeDashCore.db.updateSettings(new Settings(1, isSoundOn, isMusicOn, isVibrationOn));
						game.setScreen(new MenuScreen(game));
						return true;
					}
				};
				GameAudio.dogeBark();
				imageBackButton.setOrigin(imageBackButton.getWidth() / 4, imageBackButton.getHeight() / 2);
				imageBackButton.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageBackButton.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

	}

	private void initActors() {
		stage.addActor(imageMenuBg);

		stage.addActor(imageBackButton);
		stage.addActor(imageMenuMomBody);
		imageMenuBluePup.setOrigin(imageMenuBluePup.getWidth() / 2, imageMenuBluePup.getHeight());

		imageMenuBluePup.addAction(forever(sequence(rotateBy(5, 2), delay(0.5f), sequence(rotateBy(-5, 2)))));
		stage.addActor(imageMenuBluePup);
		stage.addActor(imageMenuMomNosePaw);

		imageMenuCreamPupBody.setOrigin(imageMenuCreamPupBody.getWidth() / 2, imageMenuCreamPupBody.getHeight() / 2);
		imageMenuCreamPupBody.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		stage.addActor(imageMenuCreamPupBody);
		imageMenuCreamPupPaw.setOrigin(imageMenuCreamPupPaw.getWidth() / 2, imageMenuCreamPupPaw.getHeight() / 2);
		imageMenuCreamPupPaw.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		imageMenuCreamPupPaw.addAction(forever(sequence(rotateBy(20, 1), delay(0.5f), sequence(rotateBy(-20, 1)))));
		stage.addActor(imageMenuCreamPupPaw);
		imageMenuCreamPupPaw2.setOrigin(imageMenuCreamPupPaw.getWidth() / 2, imageMenuCreamPupPaw.getHeight() / 2);
		imageMenuCreamPupPaw2.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		imageMenuCreamPupPaw2.addAction(forever(sequence(rotateBy(-20, 1), delay(0.5f), sequence(rotateBy(20, 1)))));
		stage.addActor(imageMenuCreamPupPaw2);
		
		//fix for S3 resolution
		winOverlay.setX(0);
		winOverlay.setY(0);
		winOverlay.setWidth(1280);
		winOverlay.setHeight(720);
		stage.addActor(winOverlay);
		stage.addActor(imageSoundButton);
		stage.addActor(imageBackButton);
		stage.addActor(imageMusicButton);
		stage.addActor(imageVibrationButton);
		stage.addActor(imageOptionsTitle);
		stage.addActor(imageOptionsText);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		Vector2 size = Scaling.fit.apply(Consts.GAMEWIDTH, Consts.GAMEHEIGHT, width, height);
		int viewportX = (int) (width - size.x) / 2;
		int viewportY = (int) (height - size.y) / 2;
		int viewportWidth = (int) size.x;
		int viewportHeight = (int) size.y;
		Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
		stage.setViewport(Consts.GAMEWIDTH, Consts.GAMEHEIGHT, true);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
