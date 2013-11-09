package com.alicode.game.dogedash.Screens;

import com.alicode.game.dogedash.DogeDashCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AbstractScreen implements Screen {
	public static final int VIEWPORT_WIDTH = 800, VIEWPORT_HEIGHT = 480;

	protected final DogeDashCore game;
	protected final Stage stage;

	private BitmapFont font;
	private BitmapFont font_title;
	private SpriteBatch batch;
	private TextureAtlas atlas;

	public AbstractScreen(DogeDashCore game) {
		this.game = game;
		int width = VIEWPORT_WIDTH;
		int height = VIEWPORT_HEIGHT;
		this.stage = new Stage(width, height, true);
	}

	protected String getName() {
		return getClass().getSimpleName();
	}

	public SpriteBatch getBatch() {
		if (batch == null) {
			batch = new SpriteBatch();
		}
		return batch;
	}

	public TextureAtlas getAtlas() {
		if (atlas == null) {
			atlas = new TextureAtlas(Gdx.files.internal("core/core.pack"));
		}
		return atlas;
	}

	public BitmapFont getFont() {
		if (font == null) {
			font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
		}
		return font;
	}

	public BitmapFont getFontTitle() {
		if (font_title == null) {
			font_title = new BitmapFont(Gdx.files.internal("skin/title_font.fnt"), false);
		}
		return font_title;
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		Gdx.app.log(DogeDashCore.LOG, "Showing screen: " + getName());

	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(DogeDashCore.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height);
	}

	@Override
	public void render(float delta) {
		stage.act(delta);

		Gdx.gl.glClearColor(0.10f, 0.10f, 0.10f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// draw the actors
		stage.draw();
	}

	@Override
	public void hide() {
		Gdx.app.log(DogeDashCore.LOG, "Hiding screen: " + getName());

		dispose();
	}

	@Override
	public void pause() {
		Gdx.app.log(DogeDashCore.LOG, "Pausing screen: " + getName());
	}

	@Override
	public void resume() {
		Gdx.app.log(DogeDashCore.LOG, "Resuming screen: " + getName());
	}

	@Override
	public void dispose() {
		Gdx.app.log(DogeDashCore.LOG, "Disposing screen: " + getName());

		if (font != null)
			font.dispose();
		if (font_title != null)
			font_title.dispose();
		if (batch != null)
			batch.dispose();
		if (atlas != null)
			atlas.dispose();
	}

}
