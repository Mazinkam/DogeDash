package com.alicode.game.dogedash.utils.txt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameCustomTextImage extends Actor {

	private BitmapFont font;
	private String text;
	private int x, y;
	private TextureRegion img;
	private int spaceBetween = 10;
	private boolean imageFirst = true;
	private boolean gesture = true;

	public GameCustomTextImage() {
		font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
	}

	public GameCustomTextImage(int x, int y) {
		font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
		this.x = x;
		this.y = y;
	}

	public GameCustomTextImage(int x, int y, String text) {
		font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
		this.x = x;
		this.y = y;
		this.text = text;
	}

	public GameCustomTextImage(TextureRegion img) {
		font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
		this.img = img;
	}

	public GameCustomTextImage(String text, TextureRegion img) {
		font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
		this.text = text;
		this.img = img;
	}

	public GameCustomTextImage(int x, int y, String text, TextureRegion img) {
		font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
		this.x = x;
		this.y = y;
		this.text = text;
		this.img = img;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (text.length() > 0) {
			if (imageFirst)
				font.draw(batch, text, x + spaceBetween + img.getRegionWidth(), y + img.getRegionHeight());
			else
				font.draw(batch, text, x, y + img.getRegionHeight());
		}
		if (img != null) {
			if (imageFirst)
				batch.draw(img, x, y, img.getRegionWidth() / 2, img.getRegionHeight() / 2, img.getRegionWidth(), img.getRegionHeight(), 1, 1, 0);
			else
				batch.draw(img, x + spaceBetween + img.getRegionWidth(), y, img.getRegionWidth() / 2, img.getRegionHeight() / 2,
						img.getRegionWidth(), img.getRegionHeight(), 1, 1, 0);
		}
	}

	public TextureRegion getImg() {
		return img;
	}

	public void setImg(TextureRegion img) {
		this.img = img;
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

	public boolean isImageFirst() {
		return imageFirst;
	}

	public void setImageFirst(boolean imageFirst) {
		this.imageFirst = imageFirst;
	}

	public int getSpaceBetween() {
		return spaceBetween;
	}

	public void setSpaceBetween(int spaceBetween) {
		this.spaceBetween = spaceBetween;
	}
}