package com.alicode.game.dogedash.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.models.WindowOverlay;
import com.alicode.game.dogedash.screens.CustomizationScreen.MenuState;
import com.alicode.game.dogedash.utils.GameAudio;
import com.alicode.game.dogedash.utils.GameGesture;
import com.alicode.game.dogedash.utils.GameGesture.DirectionListener;
import com.alicode.game.dogedash.utils.txt.GameCustomTextImage;
import com.alicode.game.dogedash.utils.txt.GameImage;
import com.alicode.game.dogedash.utils.txt.GameText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class HighscoresScreen implements Screen {

	private Image imageMenuBg, imageBackButton, imageMenuMomNosePaw, imageMenuMomBody, imageMenuCreamPupBody, imageMenuCreamPupPaw,
			imageMenuCreamPupPaw2, imageMenuBluePup;

	private Image imageEasyHighscore, imageNormalHighscore, imageHardHighscore;
	private Image imageLevel1, imageLevel2, imageLevel3;
	private Image imageChosenTime, imageChosenCaughtPups, imageChosenMissedPups, imageChosenStyle, imageChosenPupPoints;

	private GameText easyHighscore, normalHighscore, hardHighscore;
	private GameText chosenTime, chosenCaughtPups, chosenMissedPups, chosenStyle, chosenPupPoints;

	private DogeDashCore game;
	private Drawable tempDrawable;
	private int levelNum, difficultyNum;
	private String tableName;
	private Stage stage;
	private WindowOverlay winOverlay;
	private InputMultiplexer inputMultiplexer;

	public HighscoresScreen(DogeDashCore game) {
		this.game = game;

		easyHighscore = new GameText();
		normalHighscore = new GameText();
		hardHighscore = new GameText();
		winOverlay = new WindowOverlay();

		chosenTime = new GameText();
		chosenCaughtPups = new GameText();
		chosenMissedPups = new GameText();
		chosenPupPoints = new GameText();
		chosenStyle = new GameText();

		stage = new Stage();
		inputMultiplexer = new InputMultiplexer(stage);
	}

	@Override
	public void show() {
		initInput();
		initBackground();
		initForeground();

		initActors();

	}

	private void initBackground() {
		// background shit
		tempDrawable = new TextureRegionDrawable(Assets.menu);
		imageMenuBg = new Image(tempDrawable, Scaling.stretch);
		imageMenuBg.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mombody);
		imageMenuMomBody = new Image(tempDrawable, Scaling.stretch);
		imageMenuMomBody.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mom_nose_paw);
		imageMenuMomNosePaw = new Image(tempDrawable, Scaling.stretch);
		imageMenuMomNosePaw.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mombody);
		imageMenuMomBody = new Image(tempDrawable, Scaling.stretch);
		imageMenuMomBody.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_bluepup);
		imageMenuBluePup = new Image(tempDrawable);
		imageMenuBluePup.setX(460);
		imageMenuBluePup.setY(45);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_body);
		imageMenuCreamPupBody = new Image(tempDrawable);
		imageMenuCreamPupBody.setX(200);
		imageMenuCreamPupBody.setY(-10);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_paw);
		imageMenuCreamPupPaw = new Image(tempDrawable);
		imageMenuCreamPupPaw.setX(200);
		imageMenuCreamPupPaw.setY(10);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_paw2);
		imageMenuCreamPupPaw2 = new Image(tempDrawable);
		imageMenuCreamPupPaw2.setX(290);
		imageMenuCreamPupPaw2.setY(10);

		tempDrawable = new TextureRegionDrawable(Assets.back);
		imageBackButton = new Image(tempDrawable);
		imageBackButton.setX(660);
		imageBackButton.setY(20);

	}

	private void initForeground() {
		tempDrawable = new TextureRegionDrawable(Assets.lvl1);
		imageLevel1 = new Image(tempDrawable);
		imageLevel1.setX(30);
		imageLevel1.setY(340);

		tempDrawable = new TextureRegionDrawable(Assets.lvl2);
		imageLevel2 = new Image(tempDrawable);
		imageLevel2.setX(30);
		imageLevel2.setY((340 - Assets.lvl1.getRegionHeight() * 1) - 10);

		tempDrawable = new TextureRegionDrawable(Assets.tutorial_select);
		imageLevel3 = new Image(tempDrawable);
		imageLevel3.setX(30);
		imageLevel3.setY((340 - Assets.lvl1.getRegionHeight() * 2) - 20);

		tempDrawable = new TextureRegionDrawable(Assets.tutorial_select);
		imageLevel3 = new Image(tempDrawable);
		imageLevel3.setX(30);
		imageLevel3.setY((340 - Assets.lvl1.getRegionHeight() * 2) - 20);

		tempDrawable = new TextureRegionDrawable(Assets.easySmall);
		imageEasyHighscore = new Image(tempDrawable);
		imageEasyHighscore.setX(360);
		imageEasyHighscore.setY(435);
		easyHighscore.setX(600);
		easyHighscore.setY(435 + Assets.easySmall.getRegionHeight());
		easyHighscore.setText("Choose");

		tempDrawable = new TextureRegionDrawable(Assets.normalSmall);
		imageNormalHighscore = new Image(tempDrawable);
		imageNormalHighscore.setX(360);
		imageNormalHighscore.setY(385);
		normalHighscore.setX(600);
		normalHighscore.setY(385 + Assets.normalSmall.getRegionHeight());
		normalHighscore.setText("a");

		tempDrawable = new TextureRegionDrawable(Assets.hardSmall);
		imageHardHighscore = new Image(tempDrawable);
		imageHardHighscore.setX(360);
		imageHardHighscore.setY(335);
		hardHighscore.setX(600);
		hardHighscore.setY(335 + Assets.hardSmall.getRegionHeight());
		hardHighscore.setText("stage");

		tempDrawable = new TextureRegionDrawable(Assets.time);
		imageChosenTime = new Image(tempDrawable);
		imageChosenTime.setX(360);
		imageChosenTime.setY(220);
		chosenTime.setX(600);
		chosenTime.setY(220 + Assets.time.getRegionHeight());
		chosenTime.setText("and");

		tempDrawable = new TextureRegionDrawable(Assets.stylepoints);
		imageChosenStyle = new Image(tempDrawable);
		imageChosenStyle.setX(360);
		imageChosenStyle.setY(190);
		chosenStyle.setX(600);
		chosenStyle.setY(190 + Assets.stylepoints.getRegionHeight());
		chosenStyle.setText("a");

		tempDrawable = new TextureRegionDrawable(Assets.puppiescaught);
		imageChosenCaughtPups = new Image(tempDrawable);
		imageChosenCaughtPups.setX(360);
		imageChosenCaughtPups.setY(160);
		chosenCaughtPups.setX(600);
		chosenCaughtPups.setY(160 + Assets.puppiescaught.getRegionHeight());
		chosenCaughtPups.setText("difficulty");

		tempDrawable = new TextureRegionDrawable(Assets.pup_missed);
		imageChosenMissedPups = new Image(tempDrawable);
		imageChosenMissedPups.setX(360);
		imageChosenMissedPups.setY(130);
		chosenMissedPups.setX(600);
		chosenMissedPups.setY(130 + Assets.pup_missed.getRegionHeight());
		chosenMissedPups.setText("to display");

		tempDrawable = new TextureRegionDrawable(Assets.pup_points);
		imageChosenPupPoints = new Image(tempDrawable);
		imageChosenPupPoints.setX(360);
		imageChosenPupPoints.setY(100);
		chosenPupPoints.setX(600);
		chosenPupPoints.setY(100 + Assets.pup_points.getRegionHeight());
		chosenPupPoints.setText("your stats!");

	}

	private void initInput() {

		InputProcessor backProcessor = new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {

				if ((keycode == Keys.ESCAPE) || (keycode == Keys.BACK)) {

					game.setScreen(new MenuScreen(game));

				}

				return false;
			}
		};
		inputMultiplexer.addProcessor(backProcessor);
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);

		imageLevel1.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						imageLevel2.clearActions();
						imageLevel3.clearActions();
						imageLevel1.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f))));
						return true;
					}
				};
				// GameAudio.dogeBark();
				tableName = "levelDay";
				levelNum = 1;
				imageLevel1.setOrigin(imageLevel1.getWidth() / 4, imageLevel1.getHeight() / 2);
				imageLevel1.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageLevel1.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));

				easyHighscore.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getHighScore());
				normalHighscore.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getHighScore());
				hardHighscore.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getHighScore());

			}
		});

		imageLevel2.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						imageLevel1.clearActions();
						imageLevel3.clearActions();
						imageLevel2.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f))));
						return true;
					}
				};
				tableName = "levelNight";
				levelNum = 2;
				// GameAudio.dogeBark();
				imageLevel2.setOrigin(imageLevel2.getWidth() / 4, imageLevel2.getHeight() / 2);
				imageLevel2.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageLevel2.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));

				easyHighscore.setText("" + DogeDashCore.db.getLevelHighscore(1, "levelNight").getHighScore());
				normalHighscore.setText("" + DogeDashCore.db.getLevelHighscore(2, "levelNight").getHighScore());
				hardHighscore.setText("" + DogeDashCore.db.getLevelHighscore(3, "levelNight").getHighScore());

			}
		});

		imageLevel3.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						imageLevel1.clearActions();
						imageLevel2.clearActions();
						imageLevel3.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f))));
						return true;
					}
				};
				// GameAudio.dogeBark();
				levelNum = 3;
				imageLevel3.setOrigin(imageLevel3.getWidth() / 4, imageLevel3.getHeight() / 2);
				imageLevel3.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageLevel3.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));

				easyHighscore.setText("missingNo");
				normalHighscore.setText("missingNo");
				hardHighscore.setText("missingNo");

				chosenTime.setText("missingNo");
				chosenStyle.setText("missingNo");
				chosenCaughtPups.setText("missingNo");
				chosenMissedPups.setText("missingNo");
				chosenPupPoints.setText("missingNo");
			}

		});

		imageEasyHighscore.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						imageNormalHighscore.clearActions();
						imageHardHighscore.clearActions();
						imageEasyHighscore.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f),
								delay(0.5f))));
						return true;
					}
				};
				// GameAudio.dogeBark();
				imageEasyHighscore.setOrigin(easyHighscore.getWidth() / 4, easyHighscore.getHeight() / 2);
				imageEasyHighscore.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageEasyHighscore.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
				if (levelNum > 0) {
					chosenTime.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getTimeAlive());
					chosenStyle.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getStylePoints());
					chosenCaughtPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getCaughtPuppyNum());
					chosenMissedPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getMissedPuppyNum());
					chosenPupPoints.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getPuppyPoints());
				}
			}
		});

		imageNormalHighscore.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						imageEasyHighscore.clearActions();
						imageHardHighscore.clearActions();
						imageNormalHighscore.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f),
								delay(0.5f))));
						return true;
					}
				};
				// GameAudio.dogeBark();
				imageNormalHighscore.setOrigin(easyHighscore.getWidth() / 4, easyHighscore.getHeight() / 2);
				imageNormalHighscore.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageNormalHighscore.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f),
						rotateBy(-5, 0.3f, Interpolation.swing), completeAction)));
				if (levelNum > 0) {
					chosenTime.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getTimeAlive());
					chosenStyle.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getStylePoints());
					chosenCaughtPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getCaughtPuppyNum());
					chosenMissedPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getMissedPuppyNum());
					chosenPupPoints.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getPuppyPoints());
				}
			}
		});

		imageHardHighscore.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						imageEasyHighscore.clearActions();
						imageNormalHighscore.clearActions();
						imageHardHighscore.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f),
								delay(0.5f))));
						return true;
					}
				};
				// GameAudio.dogeBark();
				imageHardHighscore.setOrigin(imageHardHighscore.getWidth() / 4, imageHardHighscore.getHeight() / 2);
				imageHardHighscore.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageHardHighscore.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
				if (levelNum > 0) {
					chosenTime.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getTimeAlive());
					chosenStyle.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getStylePoints());
					chosenCaughtPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getCaughtPuppyNum());
					chosenMissedPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getMissedPuppyNum());
					chosenPupPoints.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getPuppyPoints());
				}
			}
		});

		imageBackButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						game.setScreen(new MenuScreen(game));
						return true;
					}
				};
				// GameAudio.dogeBark();
				imageBackButton.setOrigin(imageBackButton.getWidth() / 4, imageBackButton.getHeight() / 2);
				imageBackButton.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageBackButton.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

	}

	private void initActors() {
		stage.addActor(imageMenuBg);

		stage.addActor(imageBackButton);
		stage.addActor(imageMenuMomBody);
		imageMenuBluePup.setOrigin(imageMenuBluePup.getWidth() / 2, imageMenuBluePup.getHeight());

		imageMenuBluePup.addAction(forever(sequence(rotateBy(5, 2), delay(0.5f), sequence(rotateBy(-5, 2)))));
		stage.addActor(imageMenuBluePup);
		stage.addActor(imageMenuMomNosePaw);

		imageMenuCreamPupBody.setOrigin(imageMenuCreamPupBody.getWidth() / 2, imageMenuCreamPupBody.getHeight() / 2);
		imageMenuCreamPupBody.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		stage.addActor(imageMenuCreamPupBody);
		imageMenuCreamPupPaw.setOrigin(imageMenuCreamPupPaw.getWidth() / 2, imageMenuCreamPupPaw.getHeight() / 2);
		imageMenuCreamPupPaw.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		imageMenuCreamPupPaw.addAction(forever(sequence(rotateBy(20, 1), delay(0.5f), sequence(rotateBy(-20, 1)))));
		stage.addActor(imageMenuCreamPupPaw);
		imageMenuCreamPupPaw2.setOrigin(imageMenuCreamPupPaw.getWidth() / 2, imageMenuCreamPupPaw.getHeight() / 2);
		imageMenuCreamPupPaw2.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		imageMenuCreamPupPaw2.addAction(forever(sequence(rotateBy(-20, 1), delay(0.5f), sequence(rotateBy(20, 1)))));
		stage.addActor(imageMenuCreamPupPaw2);
		stage.addActor(winOverlay);

		stage.addActor(imageBackButton);

		stage.addActor(imageLevel1);
		stage.addActor(imageLevel2);
		stage.addActor(imageLevel3);

		stage.addActor(imageNormalHighscore);
		stage.addActor(imageHardHighscore);
		stage.addActor(imageEasyHighscore);

		stage.addActor(imageChosenTime);
		stage.addActor(imageChosenCaughtPups);
		stage.addActor(imageChosenMissedPups);
		stage.addActor(imageChosenStyle);
		stage.addActor(imageChosenPupPoints);

		stage.addActor(normalHighscore);
		stage.addActor(hardHighscore);
		stage.addActor(easyHighscore);

		stage.addActor(chosenTime);
		stage.addActor(chosenCaughtPups);
		stage.addActor(chosenMissedPups);
		stage.addActor(chosenStyle);
		stage.addActor(chosenPupPoints);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(800, 480, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
		inputMultiplexer.clear();

	}

}
