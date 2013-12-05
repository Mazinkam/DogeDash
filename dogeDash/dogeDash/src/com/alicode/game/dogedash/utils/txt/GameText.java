package com.alicode.game.dogedash.utils.txt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameText extends Actor {

	private BitmapFont font;
	private String text;
	private float x, y;

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
	public float getLineHeight()
	{
		return font.getLineHeight();
	}
	public float getLineWidth()
	{
		return text.length() * (font.getSpaceWidth() * 2)  * 0.9f;
	}
	
	
	
	public void setSize(float x) {
		font.setScale(x);
	}
	
	public void setColor(Color c)
	{
		font.setColor(c);
	}
	
	public void setAlpha(float a)
	{
		font.setColor(0, 0, 0, a);
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

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}