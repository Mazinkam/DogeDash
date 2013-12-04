package com.alicode.game.dogedash.worlds;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.models.WindowOverlay;
import com.alicode.game.dogedash.screens.MenuScreen;
import com.alicode.game.dogedash.utils.GameAudio;
import com.alicode.game.dogedash.utils.GameInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class WorldTerminal implements Screen {

	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	private Stage stage;
	private WorldOne worldOne;
	private WorldTwo worldTwo;
	private GameInput gameInput;
	private MotherDoge m;
	private WindowOverlay winOverlay;
	private InputMultiplexer inputMuiltiplex;

	public WorldTerminal(float delta) {
		stage = new Stage();
		winOverlay = new WindowOverlay();
		inputMuiltiplex = new InputMultiplexer();

		switch (Statics.gameLevel) {
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
		if (state == GameState.Ready)
			updateReady();
		if (state == GameState.Running)
			updateRunning();
		if (state == GameState.Paused)
			updatePaused();
		if (state == GameState.GameOver)
			updateGameOver();

		stage.act(delta);
		stage.draw();
	}

	private void updateReady() {
		Statics.gameRunning = false;

	}

	private void updateRunning() {
		// TODO Auto-generated method stub
		Statics.gameRunning = true;

	}

	private void updatePaused() {
		Statics.gameRunning = false;

	}

	private void updateGameOver() {
		Statics.gameRunning = false;

	}

	@Override
	public void show() {


		inputMuiltiplex.addProcessor(gameInput);
		inputMuiltiplex.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMuiltiplex);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				state = GameState.Running;
				Gdx.app.log(DogeDashCore.LOG, "state " + state);
				winOverlay.remove();
				stage.removeListener(this);

			}
		});
		
		stage.addActor(winOverlay);

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
