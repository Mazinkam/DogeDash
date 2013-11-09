package com.alicode.game.dogedash.Screens;

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

public class OptionsScreen extends AbstractScreen {

	private Image bgImage, menuTitle, menuTitleTxt, soundButton, musicButton, vibrationButton, backButton;
	private AtlasRegion imageRegion;
	private Drawable drawRegion;
	
	public OptionsScreen(DogeDashCore game) {
		super(game);
	}

	@Override
	public void show() {
		super.show();

		imageRegion = getAtlas().findRegion("menu/main/menu_background");
		drawRegion = new TextureRegionDrawable(imageRegion);

		bgImage = new Image(drawRegion, Scaling.stretch);
		bgImage.setFillParent(true);

		imageRegion = getAtlas().findRegion("menu/text/titles/options_title");
		drawRegion = new TextureRegionDrawable(imageRegion);

		menuTitle = new Image(drawRegion);
		menuTitle.setX(70);
		menuTitle.setY(400);

		imageRegion = getAtlas().findRegion("menu/text/txt/options_text");
		drawRegion = new TextureRegionDrawable(imageRegion);

		menuTitleTxt = new Image(drawRegion);
		menuTitleTxt.setX(30);
		menuTitleTxt.setY(340);

		imageRegion = getAtlas().findRegion("menu/text/buttons/soundoff");
		drawRegion = new TextureRegionDrawable(imageRegion);

		soundButton = new Image(drawRegion);
		soundButton.setX(30);
		soundButton.setY(230);

		imageRegion = getAtlas().findRegion("menu/text/buttons/musicoff");
		drawRegion = new TextureRegionDrawable(imageRegion);

		musicButton = new Image(drawRegion);
		musicButton.setX(30);
		musicButton.setY(180);

		imageRegion = getAtlas().findRegion("menu/text/buttons/vibrationoff");
		drawRegion = new TextureRegionDrawable(imageRegion);

		vibrationButton = new Image(drawRegion);
		vibrationButton.setX(30);
		vibrationButton.setY(120);

		imageRegion = getAtlas().findRegion("menu/text/buttons/back");
		drawRegion = new TextureRegionDrawable(imageRegion);

		backButton = new Image(drawRegion);
		backButton.setX(660);
		backButton.setY(20);

		soundButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				GameAudio.click();
			}
		});

		musicButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				GameAudio.click();

			}
		});

		vibrationButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				GameAudio.click();

			}
		});

		backButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				GameAudio.click();
				game.setScreen(new MenuScreen(game));
			}
		});

		stage.addActor(bgImage);
		stage.addActor(menuTitle);
		stage.addActor(menuTitleTxt);
		stage.addActor(soundButton);
		stage.addActor(musicButton);
		stage.addActor(vibrationButton);
		stage.addActor(backButton);

	}
}
