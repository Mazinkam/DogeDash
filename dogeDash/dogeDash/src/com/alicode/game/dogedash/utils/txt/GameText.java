package com.alicode.game.dogedash.utils.txt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameText extends Actor {

	private BitmapFont font;
	private String text;
	private int x, y;

	public GameText() {
		font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
	}

	public GameText(int x, int y) {
		font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
		this.x = x;
		this.y = y;
	}

	public GameText(int x, int y, String text) {
		font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
		this.x = x;
		this.y = y;
		this.text = text;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		font.draw(batch, text, x, y);

	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}