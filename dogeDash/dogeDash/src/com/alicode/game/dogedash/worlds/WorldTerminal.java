package com.alicode.game.dogedash.worlds;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.models.WindowOverlay;
import com.alicode.game.dogedash.utils.GameInput;
import com.alicode.game.dogedash.utils.txt.GameText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class WorldTerminal implements Screen {

	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	private Stage stage;

	private WorldOne worldOne;
	private WorldTwo worldTwo;

	private GameInput gameInput;
	private Group uiGroup, gameGroup;

	private MotherDoge m;

	private WindowOverlay winOverlay;
	private GameText gameText;
	private Image uiTest;
	private InputMultiplexer inputMuiltiplex;

	public WorldTerminal(float delta) {
		stage = new Stage();
		winOverlay = new WindowOverlay();
		inputMuiltiplex = new InputMultiplexer();
		uiGroup = new Group();
		gameGroup = new Group();
		gameText = new GameText();

		Drawable tempDrawable = new TextureRegionDrawable(Assets.character);
		uiTest = new Image(tempDrawable);
		uiTest.setX(70);
		uiTest.setY(380);

		stage.addActor(gameGroup);
		stage.addActor(uiGroup);

		switch (Statics.gameLevel) {
		case 1:
			worldOne = new WorldOne();
			m = worldOne.getMotherDoge();
			gameGroup.addActor(worldOne);
			break;
		case 2:
			worldTwo = new WorldTwo();
			m = worldTwo.getMotherDoge();
			gameGroup.addActor(worldTwo);
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
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (state == GameState.Ready)
			updateReady();
		if (state == GameState.Running)
			updateRunning();
		if (state == GameState.Paused)
			updatePaused();
		if (state == GameState.GameOver)
			updateGameOver();

		updatePlayerHealth();
		stage.act(delta);
		stage.draw();
	}

	private void updatePlayerHealth() {
		if (Statics.beesOnPlayer > 4)
			state = GameState.GameOver;

	}

	private void updateReady() {
		Statics.gameRunning = false;
		gameText.setText("Press and hold down to move the doge, release to jump!");
		gameText.setX(20);
		gameText.setY(200);
		
		uiTest.setX(300);
		uiTest.setY(200);
		

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				float desiredX = uiTest.getX();
				float desiredY = uiTest.getY();
				float desiredWidth = uiTest.getX() + uiTest.getWidth();
				float desiredHeight = uiTest.getY() + uiTest.getHeight();
				if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth
						&& event.getStageY() <= desiredHeight) {
					 state = GameState.Running;
					 Gdx.app.log(DogeDashCore.LOG, "state " + state);

					 winOverlay.remove();
					 gameText.remove();
					 stage.removeListener(this);
					Gdx.app.log(DogeDashCore.LOG, "event.getStageX(): " + event.getStageX() + " event.getStageY(): " + event.getStageY());
					Gdx.app.log(DogeDashCore.LOG, "desiredX : " + desiredX + " desiredY : " + desiredY + " desiredWidth " + desiredWidth
							+ " desiredHeight. " + desiredHeight);
				}

			}
		});

		uiGroup.addActor(winOverlay);
		uiGroup.addActor(gameText);
		uiGroup.addActor(uiTest);

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
		uiGroup.addActor(winOverlay);

	}

	@Override
	public void show() {

		inputMuiltiplex.addProcessor(gameInput);
		inputMuiltiplex.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMuiltiplex);

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
