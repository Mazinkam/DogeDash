package com.alicode.game.dogedash.worlds;

import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.utils.GameInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WorldDay implements Screen{

	private Stage stage;
	private WorldTerminal worldTerminal;
	private GameInput gameInput;
	
	
	public WorldDay(float delta) {
		stage = new Stage();
		worldTerminal = new WorldTerminal();
		gameInput = new GameInput(worldTerminal);
		stage.addActor(worldTerminal);
	}

	
	
	public void resize(int width, int height) {
		stage.setViewport(DogeDashCore.WIDTH, DogeDashCore.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}

	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(gameInput);
	}

	@Override 
    public void hide() {
    	Gdx.input.setInputProcessor(null);
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
