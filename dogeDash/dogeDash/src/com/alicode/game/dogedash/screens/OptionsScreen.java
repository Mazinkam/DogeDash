package com.alicode.game.dogedash.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.utils.GameAudio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
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

	private Image image_menu, image_options_title, image_options_txt, image_soundon, image_sound, image_musicon, image_music, image_vibrationon,
			image_vibration, image_back, image_menu_mom_nose_paw, image_menu_mombody, image_menu_blackpup, image_menu_blackpup2,
			image_menu_creampup_body, image_menu_creampup_paw, image_menu_creampup_paw2, image_menu_bluepup, bg_overlay;

	private DogeDashCore game;
	private Drawable splashDrawable;
	private ShapeRenderer bg_rect;
	private Stage stage;
	private boolean isSoundOn = true, isMuiscOn = false, isVibrationOn = false;

	public OptionsScreen(DogeDashCore game) {
		this.game = game;
		stage = new Stage();
	}

	@Override
	public void show() {

		Gdx.input.setInputProcessor(stage);
		// background shit
		splashDrawable = new TextureRegionDrawable(Assets.menu);
		image_menu = new Image(splashDrawable, Scaling.stretch);
		image_menu.setFillParent(true);

		splashDrawable = new TextureRegionDrawable(Assets.menu_mombody);
		image_menu_mombody = new Image(splashDrawable, Scaling.stretch);
		image_menu_mombody.setFillParent(true);

		splashDrawable = new TextureRegionDrawable(Assets.menu_mom_nose_paw);
		image_menu_mom_nose_paw = new Image(splashDrawable, Scaling.stretch);
		image_menu_mom_nose_paw.setFillParent(true);

		splashDrawable = new TextureRegionDrawable(Assets.menu_mombody);
		image_menu_mombody = new Image(splashDrawable, Scaling.stretch);
		image_menu_mombody.setFillParent(true);

		splashDrawable = new TextureRegionDrawable(Assets.menu_bluepup);
		image_menu_bluepup = new Image(splashDrawable);
		image_menu_bluepup.setX(460);
		image_menu_bluepup.setY(45);

		splashDrawable = new TextureRegionDrawable(Assets.menu_creampup_body);
		image_menu_creampup_body = new Image(splashDrawable);
		image_menu_creampup_body.setX(200);
		image_menu_creampup_body.setY(-10);

		splashDrawable = new TextureRegionDrawable(Assets.menu_creampup_paw);
		image_menu_creampup_paw = new Image(splashDrawable);
		image_menu_creampup_paw.setX(200);
		image_menu_creampup_paw.setY(10);

		splashDrawable = new TextureRegionDrawable(Assets.menu_creampup_paw2);
		image_menu_creampup_paw2 = new Image(splashDrawable);
		image_menu_creampup_paw2.setX(290);
		image_menu_creampup_paw2.setY(10);

		splashDrawable = new TextureRegionDrawable(Assets.options_title);
		image_options_title = new Image(splashDrawable);
		image_options_title.setX(50);
		image_options_title.setY(400);

		splashDrawable = new TextureRegionDrawable(Assets.options_txt);
		image_options_txt = new Image(splashDrawable);
		image_options_txt.setX(70);
		image_options_txt.setY(340);

		if (isSoundOn)
			splashDrawable = new TextureRegionDrawable(Assets.soundon);
		else
			splashDrawable = new TextureRegionDrawable(Assets.soundoff);
		image_sound = new Image(splashDrawable);
		image_sound.setX(30);
		image_sound.setY(250);

		if (isMuiscOn)
			splashDrawable = new TextureRegionDrawable(Assets.musicon);
		else
			splashDrawable = new TextureRegionDrawable(Assets.musicoff);
		image_music = new Image(splashDrawable);
		image_music.setX(30);
		image_music.setY(200);

		if (isVibrationOn)
			splashDrawable = new TextureRegionDrawable(Assets.vibrationon);
		else
			splashDrawable = new TextureRegionDrawable(Assets.vibrationoff);
		image_vibration = new Image(splashDrawable);
		image_vibration.setX(30);
		image_vibration.setY(150);

		splashDrawable = new TextureRegionDrawable(Assets.back);

		image_back = new Image(splashDrawable);
		image_back.setX(660);
		image_back.setY(20);

		image_sound.addListener(new InputListener() {
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
				GameAudio.click();
				image_sound.setOrigin(image_sound.getWidth() / 4, image_sound.getHeight() / 2);
				image_sound.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_sound.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
				isSoundOn = !isSoundOn;
				if (isSoundOn)
					splashDrawable = new TextureRegionDrawable(Assets.soundon);
				else
					splashDrawable = new TextureRegionDrawable(Assets.soundoff);
				image_sound.setDrawable(splashDrawable);

			}
		});

		image_music.addListener(new InputListener() {
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
				GameAudio.click();
				image_music.setOrigin(image_music.getWidth() / 4, image_music.getHeight() / 2);
				image_music.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_music.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
				isMuiscOn = !isMuiscOn;
				if (isMuiscOn)
					splashDrawable = new TextureRegionDrawable(Assets.musicon);
				else
					splashDrawable = new TextureRegionDrawable(Assets.musicoff);
				image_music.setDrawable(splashDrawable);

			}
		});

		image_vibration.addListener(new InputListener() {
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
				GameAudio.click();
				image_vibration.setOrigin(image_vibration.getWidth() / 4, image_vibration.getHeight() / 2);
				image_vibration.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_vibration.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
				isVibrationOn = !isVibrationOn;
				if (isVibrationOn)
					splashDrawable = new TextureRegionDrawable(Assets.vibrationon);
				else
					splashDrawable = new TextureRegionDrawable(Assets.vibrationoff);
				image_vibration.setDrawable(splashDrawable);
			}

		});

		image_back.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						game.setScreen(new MenuScreen(game));
						return true;
					}
				};
				GameAudio.click();
				image_back.setOrigin(image_back.getWidth() / 4, image_back.getHeight() / 2);
				image_back.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_back.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

		stage.addActor(image_menu);

		stage.addActor(image_back);
		stage.addActor(image_menu_mombody);
		image_menu_bluepup.setOrigin(image_menu_bluepup.getWidth() / 2, image_menu_bluepup.getHeight());

		image_menu_bluepup.addAction(forever(sequence(rotateBy(5, 2), delay(0.5f), sequence(rotateBy(-5, 2)))));
		stage.addActor(image_menu_bluepup);
		stage.addActor(image_menu_mom_nose_paw);

		image_menu_creampup_body.setOrigin(image_menu_creampup_body.getWidth() / 2, image_menu_creampup_body.getHeight() / 2);
		image_menu_creampup_body.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		stage.addActor(image_menu_creampup_body);
		image_menu_creampup_paw.setOrigin(image_menu_creampup_paw.getWidth() / 2, image_menu_creampup_paw.getHeight() / 2);
		image_menu_creampup_paw.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		image_menu_creampup_paw.addAction(forever(sequence(rotateBy(20, 1), delay(0.5f), sequence(rotateBy(-20, 1)))));
		stage.addActor(image_menu_creampup_paw);
		image_menu_creampup_paw2.setOrigin(image_menu_creampup_paw.getWidth() / 2, image_menu_creampup_paw.getHeight() / 2);
		image_menu_creampup_paw2.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		image_menu_creampup_paw2.addAction(forever(sequence(rotateBy(-20, 1), delay(0.5f), sequence(rotateBy(20, 1)))));
		stage.addActor(image_menu_creampup_paw2);
		stage.addActor(image_sound);
		stage.addActor(image_back);
		stage.addActor(image_music);
		stage.addActor(image_vibration);
		stage.addActor(image_options_title);
		stage.addActor(image_options_txt);

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
		stage.setViewport(800, 480, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);

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
		// TODO Auto-generated method stub

	}

}
