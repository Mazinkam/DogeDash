package com.alicode.game.dogedash.worlds;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.GamePoints;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.Statics.GameState;
import com.alicode.game.dogedash.models.DogeCostumes;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.models.WindowOverlay;
import com.alicode.game.dogedash.screens.MenuScreen;
import com.alicode.game.dogedash.screens.OptionsScreen;
import com.alicode.game.dogedash.screens.WorldSelection;
import com.alicode.game.dogedash.sql.Settings;
import com.alicode.game.dogedash.utils.GameAudio;
import com.alicode.game.dogedash.utils.GameInput;
import com.alicode.game.dogedash.utils.GameVibrate;
import com.alicode.game.dogedash.utils.txt.GameText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

public class WorldTerminal implements Screen {

	private Stage stage;

	private WorldOne worldOne;
	private WorldTwo worldTwo;

	private GameInput gameInput;
	private Group readyGroup, gameoverGroup, costumesGroup, pauseGroup, gameGroup;
	private String tableName;

	private MotherDoge motherDoge;
	private DogeCostumes dogeCostumes;
	private Drawable tempDrawable;

	private WindowOverlay readyOverlay, gameoverOverlay, pauseOverlay;
	private DogeDashCore game;
	private Image imageGameOver, imageTime, imageStylePoints, imagePuppyCaught, imagePuppyMissed, imagePuppyPoints, imageDogeCoins, imageTotalScore, imageRetry, imageBack, imagePauseButton,
			imagePauseResume, imagePauseMenu, imagePauseMusic, imagePauseSound, imagePauseVibration;

	private GameText textInto, textTime, textStylePoints, textPuppyCaught, textPuppyMissed, textPuppyPoints, textDogeCoins, textTotalScore;

	private InputMultiplexer inputMultiplexer;

	public WorldTerminal(DogeDashCore game, float delta) {
		this.game = game;
		stage = new Stage();
		readyOverlay = new WindowOverlay();
		gameoverOverlay = new WindowOverlay();
		pauseOverlay = new WindowOverlay();
		inputMultiplexer = new InputMultiplexer();
		

		Statics.state = Statics.GameState.Ready;

		gameGroup = new Group();
		readyGroup = new Group();
		gameoverGroup = new Group();
		pauseGroup = new Group();
		costumesGroup = new Group();

		textInto = new GameText();

		stage.addActor(gameGroup);
		stage.addActor(readyGroup);
		stage.addActor(costumesGroup);
		Statics.cleanSlate();

		switch (Statics.gameLevel) {
		case 1:
			worldOne = new WorldOne();
			motherDoge = worldOne.getMotherDoge();
			dogeCostumes = worldOne.getDogeCostumes();
			gameGroup.addActor(worldOne);
			tableName = "levelDay";
			break;
		case 2:
			worldTwo = new WorldTwo();
			motherDoge = worldTwo.getMotherDoge();
			dogeCostumes = worldTwo.getDogeCostumes();
			gameGroup.addActor(worldTwo);
			tableName = "levelNight";
			break;
		}
		
		gameInput = new GameInput(motherDoge, dogeCostumes, this);

		defineReady();
		defineRunning();
		defineGameOver();
		definePaused();
	}

	private void definePaused() {
		tempDrawable = new TextureRegionDrawable(Assets.pause_resume);
		imagePauseResume = new Image(tempDrawable);
		imagePauseResume.setX(235);
		imagePauseResume.setY(280);

		tempDrawable = new TextureRegionDrawable(Assets.pause_menu);
		imagePauseMenu = new Image(tempDrawable);
		imagePauseMenu.setX(280);
		imagePauseMenu.setY(140);

		if (OptionsScreen.isSoundOn == 1)
			tempDrawable = new TextureRegionDrawable(Assets.pauseSoundOn);
		else
			tempDrawable = new TextureRegionDrawable(Assets.pauseSoundOff);
		imagePauseSound = new Image(tempDrawable);
		imagePauseSound.setX(600);
		imagePauseSound.setY(420);

		if (OptionsScreen.isMusicOn == 1)
			tempDrawable = new TextureRegionDrawable(Assets.pauseMusicOn);
		else
			tempDrawable = new TextureRegionDrawable(Assets.pauseMusicOff);
		imagePauseMusic = new Image(tempDrawable);
		imagePauseMusic.setX(650);
		imagePauseMusic.setY(420);

		if (OptionsScreen.isVibrationOn == 1)
			tempDrawable = new TextureRegionDrawable(Assets.pauseVibrationOn);
		else
			tempDrawable = new TextureRegionDrawable(Assets.pauseVibrationOff);
		imagePauseVibration = new Image(tempDrawable);
		imagePauseVibration.setX(700);
		imagePauseVibration.setY(420);

		pauseGroup.addActor(pauseOverlay);
		pauseGroup.addActor(imagePauseResume);
		pauseGroup.addActor(imagePauseMenu);
		pauseGroup.addActor(imagePauseVibration);
		pauseGroup.addActor(imagePauseMusic);
		pauseGroup.addActor(imagePauseSound);
		pausedInput();

	}

	private void pausedInput() {
		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (Statics.state == Statics.GameState.Paused) {
					float desiredX = imagePauseResume.getX();
					float desiredY = imagePauseResume.getY();

					float desiredWidth = imagePauseResume.getX() + imagePauseResume.getImageWidth();
					float desiredHeight = imagePauseResume.getY() + imagePauseResume.getImageHeight();

					if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth && event.getStageY() <= desiredHeight) {
						Statics.state = GameState.Running;
						pauseGroup.remove();

						DogeDashCore.db.updateSettings(new Settings(1, OptionsScreen.isSoundOn, OptionsScreen.isMusicOn, OptionsScreen.isVibrationOn));

					}

					desiredX = imagePauseSound.getX();
					desiredY = imagePauseSound.getY();

					desiredWidth = imagePauseSound.getX() + imagePauseSound.getImageWidth();
					desiredHeight = imagePauseSound.getY() + imagePauseSound.getImageHeight();

					if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth && event.getStageY() <= desiredHeight) {
						OptionsScreen.isSoundOn ^= 1;
						if (OptionsScreen.isSoundOn == 1) {
							tempDrawable = new TextureRegionDrawable(Assets.pauseSoundOn);
						} else {
							tempDrawable = new TextureRegionDrawable(Assets.pauseSoundOff);
						}
						GameAudio.dogeBark();
						imagePauseSound.setDrawable(tempDrawable);
					}

					desiredX = imagePauseMusic.getX();
					desiredY = imagePauseMusic.getY();

					desiredWidth = imagePauseMusic.getX() + imagePauseMusic.getImageWidth();
					desiredHeight = imagePauseMusic.getY() + imagePauseMusic.getImageHeight();

					if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth && event.getStageY() <= desiredHeight) {
						OptionsScreen.isMusicOn ^= 1;
						if (OptionsScreen.isMusicOn == 1) {
							tempDrawable = new TextureRegionDrawable(Assets.pauseMusicOn);

						} else {
							tempDrawable = new TextureRegionDrawable(Assets.pauseMusicOff);

						}

						imagePauseMusic.setDrawable(tempDrawable);

					}

					desiredX = imagePauseVibration.getX();
					desiredY = imagePauseVibration.getY();

					desiredWidth = imagePauseVibration.getX() + imagePauseVibration.getImageWidth();
					desiredHeight = imagePauseVibration.getY() + imagePauseVibration.getImageHeight();

					if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth && event.getStageY() <= desiredHeight) {
						OptionsScreen.isVibrationOn ^= 1;
						if (OptionsScreen.isVibrationOn == 1) {
							tempDrawable = new TextureRegionDrawable(Assets.pauseVibrationOn);
						} else {
							tempDrawable = new TextureRegionDrawable(Assets.pauseVibrationOff);
						}
						GameVibrate.vibrate(100);
						imagePauseVibration.setDrawable(tempDrawable);

					}

					desiredX = imagePauseMenu.getX();
					desiredY = imagePauseMenu.getY();

					desiredWidth = imagePauseMenu.getX() + imagePauseMenu.getImageWidth();
					desiredHeight = imagePauseMenu.getY() + imagePauseMenu.getImageHeight();

					if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth && event.getStageY() <= desiredHeight) {
						stage.removeListener(this);

						DogeDashCore.db.updateSettings(new Settings(1, OptionsScreen.isSoundOn, OptionsScreen.isMusicOn, OptionsScreen.isVibrationOn));
						game.setScreen(new MenuScreen(game));

					}
				}
			}

		});

	}

	private void defineReady() {
		readyGroup.addActor(gameoverOverlay);
		readyGroup.addActor(textInto);
		readyInput();

	}

	private void defineRunning() {

		tempDrawable = new TextureRegionDrawable(Assets.pause_button);
		imagePauseButton = new Image(tempDrawable);
		imagePauseButton.setX(660);
		imagePauseButton.setY(20);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				float desiredX = imagePauseButton.getX();
				float desiredY = imagePauseButton.getY();

				float desiredWidth = imagePauseButton.getX() + imagePauseButton.getImageWidth();
				float desiredHeight = imagePauseButton.getY() + imagePauseButton.getImageHeight();

				if ((event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth && event.getStageY() <= desiredHeight)
						&& Statics.state == Statics.GameState.Running) {
					Statics.state = GameState.Paused;
					stage.addActor(pauseGroup);

				}

			}
		});

		gameGroup.addActor(imagePauseButton);

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

		gameoverGroup.addActor(readyOverlay);

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

		updatePlayerHealth();
		// updatePlayerCostume();
		stage.act(delta);
		stage.draw();
	}

	private void updatePlayerHealth() {
		if (Statics.enemiesOnPlayer >= 4 && Statics.state == GameState.Running) {
			Statics.state = GameState.GameOver;
			stage.addActor(gameoverGroup);
			updateGameoverScore();
			updateHighscore();
			gameoverInput();
		}

	}

	private void readyInput() {
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

				if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth && event.getStageY() <= desiredHeight) {
					Statics.state = GameState.Running;
					Statics.createLife();
					readyGroup.remove();
					stage.removeListener(this);
				}

			}
		});
	}

	private void gameoverInput() {
		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				float desiredX = imageBack.getX();
				float desiredY = imageBack.getY();

				float desiredWidth = imageBack.getX() + imageBack.getWidth();
				float desiredHeight = imageBack.getY() + imageBack.getHeight();

				if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth && event.getStageY() <= desiredHeight) {

					GameAudio.dogeBark();
					imageBack.clearActions();
					imageBack.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing), new Action() {
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

				if (event.getStageX() >= desiredX && event.getStageY() >= desiredY && event.getStageX() <= desiredWidth && event.getStageY() <= desiredHeight) {

					GameAudio.dogeBark();
					imageRetry.clearActions();
					imageRetry.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing), new Action() {
						public boolean act(float delta) {
							Statics.cleanSlate();
							gameoverGroup.remove();
							defineReady();
							game.setScreen(new WorldTerminal(game, Gdx.graphics.getDeltaTime()));
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
			DogeDashCore.db.updateLevelHighscore(new com.alicode.game.dogedash.sql.Level(Statics.gameLevelDifficulty, GamePoints.finalScore(), GamePoints.bonusPointStatic, GamePoints.currentScore,
					GamePoints.puppyCaughtNum, GamePoints.puppyMissedNum, GamePoints.puppyPoints()), tableName);
			Gdx.app.log(DogeDashCore.LOG, "Updated Highscore!");
		}

	}

	@Override
	public void show() {

		InputProcessor backProcessor = new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {

				if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK)) {

					if (Statics.state == Statics.GameState.Running) {
						Statics.state = Statics.GameState.Paused;
						definePaused();
						stage.addActor(pauseGroup);
					}
					if (Statics.state == Statics.GameState.GameOver || Statics.state == Statics.GameState.Ready) {
						game.setScreen(new MenuScreen(game));
					}

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
