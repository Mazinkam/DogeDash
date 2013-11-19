package com.alicode.game.dogedash.utils.txt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameImage extends Actor {
	private int x, y;
	private TextureRegion img;

	public GameImage() {

	}

	public GameImage(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public GameImage(TextureRegion img) {
		this.img = img;
	}

	public GameImage(int x, int y, TextureRegion img) {

		this.x = x;
		this.y = y;

		this.img = img;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		batch.draw(img, x, y, img.getRegionWidth() / 2, img.getRegionHeight() / 2, img.getRegionWidth(), img.getRegionHeight(), 1, 1, 0);

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

}
