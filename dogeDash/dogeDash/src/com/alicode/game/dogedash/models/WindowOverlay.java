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

	public WindowOverlay() {
	       renderer = new ShapeRenderer();
			
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
        renderer.setColor(0, 0, 0, 0.5f);
        renderer.filledRect(0, 0, 800, 480);
        renderer.end();
        batch.begin();
	}

	public void playerHit(boolean front, boolean above) {

		addAction(Actions.repeat(10,Actions.sequence(Actions.parallel(Actions.rotateTo(-10f, 0.1f)), Actions.rotateTo(10f, 0.1f))));
	}

	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public Rectangle getBounds() {
		return bounds;
	}

}