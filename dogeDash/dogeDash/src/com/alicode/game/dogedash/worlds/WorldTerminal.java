package com.alicode.game.dogedash.worlds;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.GamePoints;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.Statics.GameState;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.models.WindowOverlay;
import com.alicode.game.dogedash.screens.MenuScreen;
import com.alicode.game.dogedash.screens.WorldSelection;
import com.alicode.game.dogedash.utils.GameAudio;
import com.alicode.game.dogedash.utils.GameInput;
import com.alicode.game.dogedash.utils.txt.GameText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class WorldTerminal implements Screen {

	private Stage stage;

	private WorldOne worldOne;
	private WorldTwo worldTwo;

	private GameInput gameInput;
	private Group readyGroup, gameoverGroup, pauseGroup, gameGroup;
	private String tableName;

	private MotherDoge m;
	private Drawable tempDrawable;

	private WindowOverlay winOverlay, winOverlay2;
	private DogeDashCore game;
	private Image imageGameOver, imageTime, imageStylePoints, imagePuppyCaught, imagePuppyMissed, imagePuppyPoints, imageDogeCoins, imageTotalScore,
			imageRetry, imageBack, imagePauseButton;

	private GameText textInto, textTime, textStylePoints, textPuppyCaught, textPuppyMissed, textPuppyPoints, textDogeCoins, textTotalScore;

	private InputMultiplexer inputMultiplexer;

	public WorldTerminal(DogeDashCore game, float delta) {
		this.game = game;
		stage = new Stage();
		winOverlay = new WindowOverlay();
		winOverlay2 = new WindowOverlay();
		inputMultiplexer = new InputMultiplexer();

		Statics.state = Statics.GameState.Ready;
		Statics.cleanSlate();

		gameGroup = new Group();
		readyGroup = new Group();
		gameoverGroup = new Group();
		pauseGroup = new Group();

		textInto = new GameText();

		stage.addActor(gameGroup);
		stage.addActor(readyGroup);

		defineReady();
		defineGameOver();

		switch (Statics.gameLevel) {
		case 1:
			worldOne = new WorldOne();
			m = worldOne.getMotherDoge();
			gameGroup.addActor(worldOne);
			tableName = "levelDay";
			break;
		case 2:
			worldTwo = new WorldTwo();
			m = worldTwo.getMotherDoge();
			gameGroup.addActor(worldTwo);
			tableName = "levelNight";
			break;
		}
		gameInput = new GameInput(m, this);
	}

	private void defineReady() {
		readyGroup.addActor(winOverlay2);
		readyGroup.addActor(textInto);

	}

	private void defineGameOver() {

		tempDrawable = new TextureRegionDrawable(Assets.gameovertitle);
		imageGameOver = new Image(tempDrawable);
		imageGameOver.setX(300);
		imageGameOver.setY(400);

		tempDrawable = new TextureRegionDrawable(Assets.time);
		imageTime = new Image(tempDrawable);
		imageTime.setX(330);
		imageTime.setY(320);

		tempDrawable = new TextureRegionDrawable(Assets.stylepoints);
		imageStylePoints = new Image(tempDrawable);
		imageStylePoints.setX(330);
		imageStylePoints.setY(290);

		tempDrawable = new TextureRegionDrawable(Assets.puppiescaught);
		imagePuppyCaught = new Image(tempDrawable);
		imagePuppyCaught.setX(330);
		imagePuppyCaught.setY(260);

		tempDrawable = new TextureRegionDrawable(Assets.pup_missed);
		imagePuppyMissed = new Image(tempDrawable);
		imagePuppyMissed.setX(330);
		imagePuppyMissed.setY(230);

		tempDrawable = new TextureRegionDrawable(Assets.pup_points);
		imagePuppyPoints = new Image(tempDrawable);
		imagePuppyPoints.setX(330);
		imagePuppyPoints.setY(200);

		tempDrawable = new TextureRegionDrawable(Assets.dogecoins_text);
		imageDogeCoins = new Image(tempDrawable);
		imageDogeCoins.setX(330);
		imageDogeCoins.setY(170);

		tempDrawable = new TextureRegionDrawable(Assets.totalscore);
		imageTotalScore = new Image(tempDrawable);
		imageTotalScore.setX(330);
		imageTotalScore.setY(110);

		tempDrawable = new TextureRegionDrawable(Assets.retry);
		imageRetry = new Image(tempDrawable);
		imageRetry.setX(330);
		imageRetry.setY(20);

		tempDrawable = new TextureRegionDrawable(Assets.back);
		imageBack = new Image(tempDrawable);
		imageBack.setX(660);
		imageBack.setY(20);

		textTime = new GameText();
		textTime.setX(580);
		textTime.setY(350);
		textTime.setText(GamePoints.currentScore + "");

		textStylePoints = new GameText();
		textStylePoints.setX(580);
		textStylePoints.setY(320);
		textStylePoints.setText(GamePoints.bonusPointStatic + "");

		textPuppyCaught = new GameText();
		textPuppyCaught.setX(580);
		textPuppyCaught.setY(290);
		textPuppyCaught.setText(GamePoints.puppyCaughtNum + "");

		textPuppyMissed = new GameText();
		textPuppyMissed.setX(580);
		textPuppyMissed.setY(260);
		textPuppyMissed.setText(GamePoints.puppyMissedNum + "");

		textPuppyPoints = new GameText();
		textPuppyPoints.setX(580);
		textPuppyPoints.setY(230);
		textPuppyPoints.setText(GamePoints.puppyPoints() + "");

		textDogeCoins = new GameText();
		textDogeCoins.setX(580);
		textDogeCoins.setY(200);
		textDogeCoins.setText(GamePoints.dogeCoins + "");

		textTotalScore = new GameText();
		textTotalScore.setX(580);
		textTotalScore.setY(140);
		textTotalScore.setText(GamePoints.finalScore + "");

		gameoverGroup.addActor(winOverlay);

		gameoverGroup.addActor(imageGameOver);
		gameoverGroup.addActor(imageTime);
		gameoverGroup.addActor(imageStylePoints);
		gameoverGroup.addActor(imagePuppyCaught);
		gameoverGroup.addActor(imagePuppyMissed);
		gameoverGroup.addActor(imagePuppyPoints);
		gameoverGroup.addActor(imageDogeCoins);
		gameoverGroup.addActor(imageTotalScore);

		gameoverGroup.addActor(textTime);
		gameoverGroup.addActor(textStylePoints);
		gameoverGroup.addActor(textPuppyCaught);
		gameoverGroup.addActor(textPuppyMissed);
		gameoverGroup.addActor(textPuppyPoints);
		gameoverGroup.addActor(textDogeCoins);
		gameoverGroup.addActor(textTotalScore);

		gameoverGroup.addActor(imageRetry);
		gameoverGroup.addActor(imageBack);

	}

	public void resize(int width, int height) {
		stage.setViewport(DogeDashCore.WIDTH, DogeDashCore.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Statics.state == GameState.Ready)
			updateReady();
		if (Statics.state == GameState.Running)
			updateRunning();
		if (Statics.state == GameState.Paused)
			updatePaused();
		if (Statics.state == GameState.GameOver)
			updateGameOver();

		updatePlayerHealth();
		stage.act(delta);
		stage.draw();
	}

	private void updatePlayerHealth() {
		if (Statics.beesOnPlayer >= 4 && Statics.state == GameState.Running) {
			Statics.state = GameState.GameOver;
			stage.addActor(gameoverGroup);
		}

	}

	private void updateReady() {
		textInto.setText("Dodge bee's and catch as many pups as possible, woof!");
		textInto.setX(20);
		textInto.setY(200);
		stage.addActor(readyGroup);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				float desiredX = textInto.getX() - 10;
				float desiredY = textInto.getY() - 30;

				float desiredWidth = textInto.getX() + 800;
				float desiredHeight = textInto.getY() + 20;

				if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth
						&& event.getStageY() <= desiredHeight) {
					Statics.state = GameState.Running;
					readyGroup.remove();
					stage.removeListener(this);
				}

			}
		});

	}

	private void updateRunning() {
		Statics.createLife();

	}

	private void updatePaused() {

	}

	private void updateGameOver() {
		updateGameoverScore();
		updateHighscore();
		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				float desiredX = imageBack.getX();
				float desiredY = imageBack.getY();

				float desiredWidth = imageBack.getX() + imageBack.getWidth();
				float desiredHeight = imageBack.getY() + imageBack.getHeight();

				if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth
						&& event.getStageY() <= desiredHeight) {

					GameAudio.dogeBark();
					imageBack.clearActions();
					imageBack.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
							new Action() {
								public boolean act(float delta) {

									game.setScreen(new WorldSelection(game));

									return true;
								}

							})));
					stage.removeListener(this);
				}

				desiredX = imageRetry.getX();
				desiredY = imageRetry.getY();

				desiredWidth = imageRetry.getX() + imageRetry.getWidth();
				desiredHeight = imageRetry.getY() + imageRetry.getHeight();

				if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth
						&& event.getStageY() <= desiredHeight) {

					GameAudio.dogeBark();
					imageRetry.clearActions();
					imageRetry.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
							new Action() {
								public boolean act(float delta) {
									Statics.cleanSlate();
									gameoverGroup.remove();
									Gdx.app.log(DogeDashCore.LOG, "State: " + Statics.state);
									return true;
								}
							})));

					stage.removeListener(this);
				}

			}
		});

	}

	private void updateGameoverScore() {
		textTime.setText(GamePoints.currentScore + "");
		textStylePoints.setText(GamePoints.bonusPointStatic + "");
		textPuppyCaught.setText(GamePoints.puppyCaughtNum + "");
		textPuppyMissed.setText(GamePoints.puppyMissedNum + "");
		textPuppyPoints.setText(GamePoints.puppyPoints() + "");
		textTotalScore.setText(GamePoints.finalScore + "");
		textDogeCoins.setText(GamePoints.dogeCoins + "");
	}

	private void updateHighscore() {

		if ((DogeDashCore.db.getLevelHighscore(Statics.gameLevelDifficulty, tableName).getHighScore() < GamePoints.finalScore())) {
			DogeDashCore.db.updateLevelHighscore(
					new com.alicode.game.dogedash.sql.Level(Statics.gameLevelDifficulty, GamePoints.finalScore(), GamePoints.bonusPointStatic,
							GamePoints.currentScore, GamePoints.puppyCaughtNum, GamePoints.puppyMissedNum, GamePoints.puppyPoints()), tableName);
			Gdx.app.log(DogeDashCore.LOG, "Updated Highscore!");
		}

	}

	@Override
	public void show() {

		InputProcessor backProcessor = new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {

				if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK)) {

					// if (Statics.state == Statics.GameState.Running) {
					// Statics.state = Statics.GameState.GameOver;
					// }
					// // if (Statics.state == Statics.GameState.GameOver) {
					game.setScreen(new MenuScreen(game));
					// }

				}

				return false;
			}
		};
		inputMultiplexer.addProcessor(gameInput);
		inputMultiplexer.addProcessor(backProcessor);
		inputMultiplexer.addProcessor(stage);

		Gdx.input.setInputProcessor(inputMultiplexer);

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
		Gdx.input.setInputProcessor(null);

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
