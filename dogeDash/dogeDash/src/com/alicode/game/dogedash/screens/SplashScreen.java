package com.alicode.game.dogedash.screens;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class SplashScreen implements Screen {
	private Image splashImage;
	private DogeDashCore game;
	private Stage stage;

	public SplashScreen(DogeDashCore game) {
		this.game = game;
		stage = new Stage();
	}

	@Override
	public void show() {

		// start playing the menu music
		// game.getMusicManager().play(DogeDashCoreMusic.MENU);

		// retrieve the splash image's region from the atlas

		Drawable splashDrawable = new TextureRegionDrawable(Assets.splash);

		// here we create the splash image actor; its size is set when the
		// resize() method gets called
		splashImage = new Image(splashDrawable, Scaling.stretch);
		splashImage.setFillParent(true);

		// this is needed for the fade-in effect to work correctly; we're just
		// making the image completely transparent
		splashImage.getColor().a = 0f;

		// configure the fade-in/out effect on the splash image
		splashImage.addAction(Actions.sequence(Actions.fadeIn(0.75f), Actions.delay(1.75f), Actions.fadeOut(0.75f), new Action() {
			@Override
			public boolean act(float delta) {
				// the last action will move to the next screen
				game.setScreen(new MenuScreen(game));
				return true;
			}
		}));

		// and finally we add the actor to the stage
		stage.addActor(splashImage);
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
