package com.alicode.game.dogedash.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.logging.Level;

import javax.sound.midi.Sequence;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.utils.GameAudio;
import com.alicode.game.dogedash.worlds.WorldTerminal;
import com.alicode.game.dogedash.worlds.WorldTwo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

public class WorldSelection implements Screen {

	private Image image_menu, image_menu_mom_nose_paw, image_menu_mombody, image_menu_blackpup, image_menu_blackpup2, image_menu_creampup_body,
			image_menu_creampup_paw, image_menu_creampup_paw2, image_menu_bluepup;
	private Image image_lvlselect, image_lvlselect_txt, image_hard, image_easy, image_normal, image_lvl1, image_lvl2, image_tutorial_levelselect,
			image_back;

	private Sequence sequence;
	private DogeDashCore game;
	private Stage stage;

	public WorldSelection(DogeDashCore game) {
		this.game = game;
		stage = new Stage();
	}

	@Override
	public void show() {

		Gdx.input.setInputProcessor(stage);
		// background shit
		Drawable splashDrawable = new TextureRegionDrawable(Assets.menu);
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

		splashDrawable = new TextureRegionDrawable(Assets.menu_blackpup);
		image_menu_blackpup = new Image(splashDrawable);
		image_menu_blackpup.setX(10);
		image_menu_blackpup.setY(40);

		splashDrawable = new TextureRegionDrawable(Assets.menu_blackpup2);
		image_menu_blackpup2 = new Image(splashDrawable);
		image_menu_blackpup2.setX(260);
		image_menu_blackpup2.setY(250);

		splashDrawable = new TextureRegionDrawable(Assets.lvlselect);
		image_lvlselect = new Image(splashDrawable);
		image_lvlselect.setX(50);
		image_lvlselect.setY(360);

		splashDrawable = new TextureRegionDrawable(Assets.lvlselect_txt);
		image_lvlselect_txt = new Image(splashDrawable);
		image_lvlselect_txt.setX(30);
		image_lvlselect_txt.setY(340);

		splashDrawable = new TextureRegionDrawable(Assets.lvl1);
		image_lvl1 = new Image(splashDrawable);
		image_lvl1.setX(30);
		image_lvl1.setY(160);

		splashDrawable = new TextureRegionDrawable(Assets.lvl2);
		image_lvl2 = new Image(splashDrawable);
		image_lvl2.setX(30 + image_lvl1.getWidth() + 30);
		image_lvl2.setY(160);

		splashDrawable = new TextureRegionDrawable(Assets.tutorial_select);

		image_tutorial_levelselect = new Image(splashDrawable);
		image_tutorial_levelselect.setX(30);
		image_tutorial_levelselect.setY(10);

		splashDrawable = new TextureRegionDrawable(Assets.back);
		image_back = new Image(splashDrawable);
		image_back.setX(660);
		image_back.setY(20);

		image_lvl1.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						game.setScreen(new WorldTerminal(Gdx.graphics.getDeltaTime()));
						return true;
					}
				};
				GameAudio.click();
				Statics.gameLevel = 1;
				image_lvl1.setOrigin(image_lvl1.getWidth() / 4, image_lvl1.getHeight() / 2);
				image_lvl1.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_lvl1.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

		image_lvl2.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						game.setScreen(new WorldTerminal(Gdx.graphics.getDeltaTime()));
						return true;
					}
				};
				GameAudio.click();
				Statics.gameLevel = 2;
				image_lvl2.setOrigin(image_lvl2.getWidth() / 4, image_lvl2.getHeight() / 2);
				image_lvl2.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_lvl2.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

		image_tutorial_levelselect.addListener(new InputListener() {
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
				image_tutorial_levelselect.setOrigin(image_tutorial_levelselect.getWidth() / 4, image_tutorial_levelselect.getHeight() / 2);
				image_tutorial_levelselect.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_tutorial_levelselect.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f),
						rotateBy(-5, 0.3f, Interpolation.swing), completeAction)));

			}

		});

		image_back.addListener(new InputListener() {
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
				image_back.setOrigin(image_back.getWidth() / 4, image_back.getHeight() / 2);
				image_back.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_back.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

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

		stage.addActor(image_lvlselect);
		stage.addActor(image_lvlselect_txt);

		stage.addActor(image_lvl1);
		stage.addActor(image_lvl2);
		stage.addActor(image_tutorial_levelselect);
		stage.addActor(image_back);

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
