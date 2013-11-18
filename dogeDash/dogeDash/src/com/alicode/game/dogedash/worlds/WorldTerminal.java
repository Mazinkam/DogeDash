package com.alicode.game.dogedash.worlds;

import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.utils.GameInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WorldTerminal implements Screen{

	private Stage stage;
	private WorldOne worldOne;
	private WorldTwo worldTwo;
	private GameInput gameInput;
	private MotherDoge m;
	
	
	public WorldTerminal(float delta) {
		stage = new Stage();
		switch(Statics.gameLevel)
		{
		case 1:
			worldOne = new WorldOne();
			m = worldOne.getMotherDoge();
			stage.addActor(worldOne);
			break;
		case 2:
			worldTwo = new WorldTwo();
			m = worldTwo.getMotherDoge();
			stage.addActor(worldTwo);
			break;
		}

		gameInput = new GameInput(m, this);
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



	public Stage getStage() {
		return stage;
	}



	public void setStage(Stage stage) {
		this.stage = stage;
	}



	public WorldOne getWorldOne() {
		return worldOne;
	}



	public void setWorldOne(WorldOne worldOne) {
		this.worldOne = worldOne;
	}



	public GameInput getGameInput() {
		return gameInput;
	}



	public void setGameInput(GameInput gameInput) {
		this.gameInput = gameInput;
	}

}
