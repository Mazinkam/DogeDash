package com.alicode.game.dogedash.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import javax.sound.midi.Sequence;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.utils.GameAudio;
import com.alicode.game.dogedash.utils.GameVibrate;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
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

public class MenuScreen implements Screen {

	private Image image_menu, image_menu_mom_nose_paw, image_menu_mombody, image_menu_blackpup, image_menu_blackpup2, image_menu_creampup_body,
			image_menu_creampup_paw, image_menu_creampup_paw2, image_menu_bluepup, image_puppydash, image_play, image_options, image_highscores,
			image_customization;
	private DogeDashCore game;
	private Stage stage;
	private Drawable tempDrawable;
	private InputMultiplexer inputMultiplexer;

	public MenuScreen(DogeDashCore game) {
		this.game = game;
		stage = new Stage();
		inputMultiplexer = new InputMultiplexer(stage);
	}

	@Override
	public void show() {

		initBackground();
		initForeground();
		initInput();
		initActors();

	}

	private void initBackground() {
		// background shit
		tempDrawable = new TextureRegionDrawable(Assets.menu);
		image_menu = new Image(tempDrawable, Scaling.stretch);
		image_menu.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mombody);
		image_menu_mombody = new Image(tempDrawable, Scaling.stretch);
		image_menu_mombody.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mom_nose_paw);
		image_menu_mom_nose_paw = new Image(tempDrawable, Scaling.stretch);
		image_menu_mom_nose_paw.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mombody);
		image_menu_mombody = new Image(tempDrawable, Scaling.stretch);
		image_menu_mombody.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_bluepup);
		image_menu_bluepup = new Image(tempDrawable);
		image_menu_bluepup.setX(460);
		image_menu_bluepup.setY(45);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_body);
		image_menu_creampup_body = new Image(tempDrawable);
		image_menu_creampup_body.setX(200);
		image_menu_creampup_body.setY(-10);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_paw);
		image_menu_creampup_paw = new Image(tempDrawable);
		image_menu_creampup_paw.setX(200);
		image_menu_creampup_paw.setY(10);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_paw2);
		image_menu_creampup_paw2 = new Image(tempDrawable);
		image_menu_creampup_paw2.setX(290);
		image_menu_creampup_paw2.setY(10);

		tempDrawable = new TextureRegionDrawable(Assets.puppydash);
		image_puppydash = new Image(tempDrawable);
		image_puppydash.setX(70);
		image_puppydash.setY(380);

		tempDrawable = new TextureRegionDrawable(Assets.menu_blackpup);
		image_menu_blackpup = new Image(tempDrawable);
		image_menu_blackpup.setX(10);
		image_menu_blackpup.setY(40);

		tempDrawable = new TextureRegionDrawable(Assets.menu_blackpup2);
		image_menu_blackpup2 = new Image(tempDrawable);
		image_menu_blackpup2.setX(260);
		image_menu_blackpup2.setY(250);

	}

	private void initForeground() {
		tempDrawable = new TextureRegionDrawable(Assets.play);
		image_play = new Image(tempDrawable);
		image_play.setX(30);
		image_play.setY(280);

		tempDrawable = new TextureRegionDrawable(Assets.options);

		image_options = new Image(tempDrawable);
		image_options.setX(30);
		image_options.setY(230);

		tempDrawable = new TextureRegionDrawable(Assets.highscores);

		image_highscores = new Image(tempDrawable);
		image_highscores.setX(30);
		image_highscores.setY(180);

		tempDrawable = new TextureRegionDrawable(Assets.customization);

		image_customization = new Image(tempDrawable);
		image_customization.setX(30);
		image_customization.setY(120);

	}

	private void initInput() {
		InputProcessor backProcessor = new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {

				if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK))

					Gdx.app.exit();
				return false;
			}
		};
		inputMultiplexer.addProcessor(backProcessor);
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);

		image_play.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						game.setScreen(new WorldSelection(game));
						return true;
					}
				};
				GameAudio.dogeBark();
				GameVibrate.vibrate(500);
				image_play.setOrigin(image_play.getWidth() / 4, image_play.getHeight() / 2);
				image_play.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_play.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

		image_options.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						game.setScreen(new OptionsScreen(game));
						return true;
					}
				};
				GameAudio.dogeBark();
				image_options.setOrigin(image_options.getWidth() / 4, image_options.getHeight() / 2);
				image_options.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_options.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

		image_highscores.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						if (Gdx.app.getType() != Gdx.app.getType().WebGL)
							game.setScreen(new HighscoresScreen(game));
						return true;
					}
				};
				GameAudio.dogeBark();
				image_highscores.setOrigin(image_highscores.getWidth() / 4, image_highscores.getHeight() / 2);
				image_highscores.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_highscores.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));

			}

		});

		image_customization.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						if (Gdx.app.getType() != Gdx.app.getType().WebGL)
							game.setScreen(new CustomizationScreen(game));
						return true;
					}
				};
				GameAudio.dogeBark();
				image_customization.setOrigin(image_customization.getWidth() / 4, image_customization.getHeight() / 2);
				image_customization.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_customization.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

	}

	private void initActors() {
		stage.addActor(image_menu);

		stage.addActor(image_menu_blackpup);
		stage.addActor(image_menu_blackpup2);

		stage.addActor(image_menu_mombody);
		image_menu_bluepup.setOrigin((image_menu_bluepup.getWidth() / 2), image_menu_bluepup.getHeight());

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

		stage.addActor(image_puppydash);
		stage.addActor(image_play);
		stage.addActor(image_options);
		stage.addActor(image_highscores);
		stage.addActor(image_customization);

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
		inputMultiplexer.clear();
		this.dispose();

	}

}
