package com.alicode.game.dogedash.screens;

import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.utils.GameAudio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class MenuScreen extends AbstractScreen {

	Image bgImage, pDashTitle, playButton, optionsButton, highscoresButton, customButton;

	
	public MenuScreen(DogeDashCore game) {
		super(game);
	//	GameAudio.playMenuBG(true);

	}

	@Override
	public void show() {

		Gdx.input.setInputProcessor(stage);

		AtlasRegion splashRegion = getAtlas().findRegion("menu/main/menu_background");
		Drawable splashDrawable = new TextureRegionDrawable(splashRegion);

		bgImage = new Image(splashDrawable, Scaling.stretch);
		bgImage.setFillParent(true);

		splashRegion = getAtlas().findRegion("menu/text/titles/puppydash");
		splashDrawable = new TextureRegionDrawable(splashRegion);

		pDashTitle = new Image(splashDrawable);
		pDashTitle.setX(70);
		pDashTitle.setY(380);

		splashRegion = getAtlas().findRegion("menu/text/buttons/play");
		splashDrawable = new TextureRegionDrawable(splashRegion);

		playButton = new Image(splashDrawable);
		playButton.setX(30);
		playButton.setY(280);

		splashRegion = getAtlas().findRegion("menu/text/buttons/options");
		splashDrawable = new TextureRegionDrawable(splashRegion);

		optionsButton = new Image(splashDrawable);
		optionsButton.setX(30);
		optionsButton.setY(230);

		splashRegion = getAtlas().findRegion("menu/text/buttons/highscores");
		splashDrawable = new TextureRegionDrawable(splashRegion);

		highscoresButton = new Image(splashDrawable);
		highscoresButton.setX(30);
		highscoresButton.setY(180);

		splashRegion = getAtlas().findRegion("menu/text/buttons/customization");
		splashDrawable = new TextureRegionDrawable(splashRegion);

		customButton = new Image(splashDrawable);
		customButton.setX(30);
		customButton.setY(120);

		playButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				GameAudio.click();
				game.setScreen(new GameScreen(game));
			}
		});

		optionsButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				GameAudio.click();
				game.setScreen(new OptionsScreen(game));
			}
		});

		highscoresButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				GameAudio.click();
				game.setScreen(new SplashScreen(game));
			}
		});

		customButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				GameAudio.click();
				game.setScreen(new SplashScreen(game));
			}
		});

		stage.addActor(bgImage);
		stage.addActor(pDashTitle);
		stage.addActor(playButton);
		stage.addActor(optionsButton);
		stage.addActor(highscoresButton);
		stage.addActor(customButton);

	}
	@Override
	public void dispose() {
		Gdx.app.log(DogeDashCore.LOG, "Disposing screen: " + getName());

		GameAudio.stopBG();
	}
}
