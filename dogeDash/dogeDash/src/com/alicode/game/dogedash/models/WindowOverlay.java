package com.alicode.game.dogedash.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class WindowOverlay extends Actor {

	private TextureRegion chosenType;
	private Rectangle bounds = new Rectangle();
	
	ShapeRenderer renderer;
	private float x, y;
	private float width, height, alpha;
	public WindowOverlay() {
	       renderer = new ShapeRenderer();
	       x = 0;
	       y = 0;
	       width = 800;
	       height = 480;
	       alpha=0.6f;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.end();
		Gdx.gl.glEnable(GL20.GL_BLEND);
        renderer.begin(ShapeType.FilledRectangle);
        renderer.setColor(0, 0, 0, alpha);
        renderer.filledRect(x, y, width, height);
        renderer.end();
        batch.begin();
	}
	public void setAlpha( float a)
	{
		alpha = a;
	}

	public Rectangle getBounds() {
		return bounds;
	}

}