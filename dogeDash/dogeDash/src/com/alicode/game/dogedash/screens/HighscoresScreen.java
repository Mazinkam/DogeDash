package com.alicode.game.dogedash.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.utils.GameAudio;
import com.alicode.game.dogedash.utils.GameGesture;
import com.alicode.game.dogedash.utils.GameGesture.DirectionListener;
import com.alicode.game.dogedash.utils.txt.GameCustomTextImage;
import com.alicode.game.dogedash.utils.txt.GameImage;
import com.alicode.game.dogedash.utils.txt.GameText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
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

	private Image image_menu, image_lvl1, image_lvl2, image_lvl3, image_back, image_menu_mom_nose_paw, image_menu_mombody, image_menu_creampup_body,
			image_menu_creampup_paw, image_menu_creampup_paw2, image_menu_bluepup;

	private Image image_easyHighscore, image_normalHighscore, image_hardHighscore;
	private Image image_chosenTime, image_chosenCaughtPups, image_chosenMissedPups, image_chosenStyle, image_chosenPupPoints;
	
	private GameText easyHighscore, normalHighscore, hardHighscore;
	private GameText chosenTime, chosenCaughtPups, chosenMissedPups, chosenStyle, chosenPupPoints;

	private DogeDashCore game;
	private Drawable tempDrawable;
	private int levelNum, difficultyNum;
	private String tableName;
	private Stage stage;
	private InputMultiplexer inputMuiltiplex;

	public HighscoresScreen(DogeDashCore game) {
		this.game = game;
		inputMuiltiplex = new InputMultiplexer();
		easyHighscore = new GameText();
		normalHighscore = new GameText();
		hardHighscore = new GameText();

		chosenTime = new GameText();
		chosenCaughtPups = new GameText();
		chosenMissedPups = new GameText();
		chosenPupPoints = new GameText();
		chosenStyle = new GameText();
	
		stage = new Stage();
	}

	@Override
	public void show() {
		// Gdx.input.setInputProcessor(stage);
		inputMuiltiplex.addProcessor(stage);
		// inputMuiltiplex.addProcessor(new GameGesture(new DirectionListener()
		// {
		// @Override
		// public void onUp() {
		// GameAudio.dogeBark();
		// }
		//
		// @Override
		// public void onRight() {
		// GameAudio.dogeBark();
		// }
		//
		// @Override
		// public void onLeft() {
		// GameAudio.dogeBark();
		// }
		//
		// @Override
		// public void onDown() {
		// GameAudio.dogeBark();
		// }
		// }));
		Gdx.input.setInputProcessor(inputMuiltiplex);

		// background shit
		tempDrawable = new TextureRegionDrawable(Assets.menu);
		image_menu = new Image(tempDrawable, Scaling.stretch);
		image_menu.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mombody);
		image_menu_mombody = new Image(tempDrawable, Scaling.stretch);
		image_menu_mombody.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mom_nose_paw);
		image_menu_mom_nose_paw = new Image(tempDrawable, Scaling.stretch);
		image_menu_mom_nose_paw.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_mombody);
		image_menu_mombody = new Image(tempDrawable, Scaling.stretch);
		image_menu_mombody.setFillParent(true);

		tempDrawable = new TextureRegionDrawable(Assets.menu_bluepup);
		image_menu_bluepup = new Image(tempDrawable);
		image_menu_bluepup.setX(460);
		image_menu_bluepup.setY(45);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_body);
		image_menu_creampup_body = new Image(tempDrawable);
		image_menu_creampup_body.setX(200);
		image_menu_creampup_body.setY(-10);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_paw);
		image_menu_creampup_paw = new Image(tempDrawable);
		image_menu_creampup_paw.setX(200);
		image_menu_creampup_paw.setY(10);

		tempDrawable = new TextureRegionDrawable(Assets.menu_creampup_paw2);
		image_menu_creampup_paw2 = new Image(tempDrawable);
		image_menu_creampup_paw2.setX(290);
		image_menu_creampup_paw2.setY(10);

		tempDrawable = new TextureRegionDrawable(Assets.lvl1);
		image_lvl1 = new Image(tempDrawable);
		image_lvl1.setX(30);
		image_lvl1.setY(340);

		tempDrawable = new TextureRegionDrawable(Assets.lvl2);

		image_lvl2 = new Image(tempDrawable);
		image_lvl2.setX(30);
		image_lvl2.setY((340 - Assets.lvl1.getRegionHeight() * 1) - 10);

		tempDrawable = new TextureRegionDrawable(Assets.tutorial_select);
		image_lvl3 = new Image(tempDrawable);
		image_lvl3.setX(30);
		image_lvl3.setY((340 - Assets.lvl1.getRegionHeight() * 2) - 20);

		tempDrawable = new TextureRegionDrawable(Assets.tutorial_select);
		image_lvl3 = new Image(tempDrawable);
		image_lvl3.setX(30);
		image_lvl3.setY((340 - Assets.lvl1.getRegionHeight() * 2) - 20);

		
		tempDrawable = new TextureRegionDrawable(Assets.easySmall);
		image_easyHighscore = new Image(tempDrawable);
		image_easyHighscore.setX(360);
		image_easyHighscore.setY(435);
		easyHighscore.setX(600);
		easyHighscore.setY(435+Assets.easySmall.getRegionHeight());
		easyHighscore.setText("Choose");
		
		tempDrawable = new TextureRegionDrawable(Assets.normalSmall);
		image_normalHighscore = new Image(tempDrawable);
		image_normalHighscore.setX(360);
		image_normalHighscore.setY(385);
		normalHighscore.setX(600);
		normalHighscore.setY(385+Assets.normalSmall.getRegionHeight());
		normalHighscore.setText("a");


		
		tempDrawable = new TextureRegionDrawable(Assets.hardSmall);
		image_hardHighscore = new Image(tempDrawable);
		image_hardHighscore.setX(360);
		image_hardHighscore.setY(335);
		hardHighscore.setX(600);
		hardHighscore.setY(335+Assets.hardSmall.getRegionHeight());
		hardHighscore.setText("stage");

		tempDrawable = new TextureRegionDrawable(Assets.time);
		image_chosenTime = new Image(tempDrawable);
		image_chosenTime.setX(360);
		image_chosenTime.setY(220);
		chosenTime.setX(600);
		chosenTime.setY(220+Assets.time.getRegionHeight());
		chosenTime.setText("and");
		
		tempDrawable = new TextureRegionDrawable(Assets.stylepoints);
		image_chosenStyle = new Image(tempDrawable);
		image_chosenStyle.setX(360);
		image_chosenStyle.setY(190);
		chosenStyle.setX(600);
		chosenStyle.setY(190+Assets.stylepoints.getRegionHeight());
		chosenStyle.setText("a");
		
		tempDrawable = new TextureRegionDrawable(Assets.puppiescaught);
		image_chosenCaughtPups = new Image(tempDrawable);
		image_chosenCaughtPups.setX(360);
		image_chosenCaughtPups.setY(160);
		chosenCaughtPups.setX(600);
		chosenCaughtPups.setY(160+Assets.puppiescaught.getRegionHeight());
		chosenCaughtPups.setText("difficulty");
		
		tempDrawable = new TextureRegionDrawable(Assets.pup_missed);
		image_chosenMissedPups = new Image(tempDrawable);
		image_chosenMissedPups.setX(360);
		image_chosenMissedPups.setY(130);
		chosenMissedPups.setX(600);
		chosenMissedPups.setY(130+Assets.pup_missed.getRegionHeight());
		chosenMissedPups.setText("to display");

		tempDrawable = new TextureRegionDrawable(Assets.pup_points);
		image_chosenPupPoints = new Image(tempDrawable);
		image_chosenPupPoints.setX(360);
		image_chosenPupPoints.setY(100);
		chosenPupPoints.setX(600);
		chosenPupPoints.setY(100+Assets.pup_points.getRegionHeight());
		chosenPupPoints.setText("your stats!");

		tempDrawable = new TextureRegionDrawable(Assets.back);
		image_back = new Image(tempDrawable);
		image_back.setX(660);
		image_back.setY(20);

		image_lvl1.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						image_lvl2.clearActions();
						image_lvl3.clearActions();
						image_lvl1.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f))));
						return true;
					}
				};
				GameAudio.dogeBark();
				tableName = "levelDay";
				levelNum = 1;
				image_lvl1.setOrigin(image_lvl1.getWidth() / 4, image_lvl1.getHeight() / 2);
				image_lvl1.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_lvl1.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));

				easyHighscore.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getHighScore());
				normalHighscore.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getHighScore());
				hardHighscore.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getHighScore());

			}
		});

		image_lvl2.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						image_lvl1.clearActions();
						image_lvl3.clearActions();
						image_lvl2.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f))));
						return true;
					}
				};
				tableName = "levelNight";
				GameAudio.dogeBark();
				image_lvl2.setOrigin(image_lvl2.getWidth() / 4, image_lvl2.getHeight() / 2);
				image_lvl2.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_lvl2.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));

				easyHighscore.setText("" + DogeDashCore.db.getLevelHighscore(1, "levelNight").getHighScore());
				normalHighscore.setText("" + DogeDashCore.db.getLevelHighscore(2, "levelNight").getHighScore());
				hardHighscore.setText("" + DogeDashCore.db.getLevelHighscore(3, "levelNight").getHighScore());

			}
		});

		image_lvl3.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						image_lvl1.clearActions();
						image_lvl2.clearActions();
						image_lvl3.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f))));
						return true;
					}
				};
				GameAudio.dogeBark();
				image_lvl3.setOrigin(image_lvl3.getWidth() / 4, image_lvl3.getHeight() / 2);
				image_lvl3.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_lvl3.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
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
		
		easyHighscore.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						image_lvl2.clearActions();
						image_lvl3.clearActions();
						image_lvl1.clearActions();
				
						easyHighscore.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f))));
						return true;
					}
				};
				GameAudio.dogeBark();
				levelNum = 1;
				easyHighscore.setOrigin(easyHighscore.getWidth() / 4, easyHighscore.getHeight() / 2);
				easyHighscore.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				easyHighscore.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));


				chosenTime.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getTimeAlive());
				chosenStyle.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getStylePoints());
				chosenCaughtPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getCaughtPuppyNum());
				chosenMissedPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getMissedPuppyNum());
				chosenPupPoints.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getPuppyPoints());
			}
		});
		image_easyHighscore.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						image_normalHighscore.clearActions();
						image_hardHighscore.clearActions();
						image_easyHighscore.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f))));
						return true;
					}
				};
				GameAudio.dogeBark();
				image_easyHighscore.setOrigin(easyHighscore.getWidth() / 4, easyHighscore.getHeight() / 2);
				image_easyHighscore.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_easyHighscore.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
				if(levelNum >0){
				chosenTime.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getTimeAlive());
				chosenStyle.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getStylePoints());
				chosenCaughtPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getCaughtPuppyNum());
				chosenMissedPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getMissedPuppyNum());
				chosenPupPoints.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getPuppyPoints());
				}
			}
		});
		
		image_normalHighscore.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						image_easyHighscore.clearActions();
						image_hardHighscore.clearActions();
						image_normalHighscore.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f))));
						return true;
					}
				};
				GameAudio.dogeBark();
				image_normalHighscore.setOrigin(easyHighscore.getWidth() / 4, easyHighscore.getHeight() / 2);
				image_normalHighscore.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_normalHighscore.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
				if(levelNum >0){
				chosenTime.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getTimeAlive());
				chosenStyle.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getStylePoints());
				chosenCaughtPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getCaughtPuppyNum());
				chosenMissedPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getMissedPuppyNum());
				chosenPupPoints.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getPuppyPoints());
				}
			}
		});
		
		image_hardHighscore.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						image_easyHighscore.clearActions();
						image_normalHighscore.clearActions();
						image_hardHighscore.addAction(Actions.forever(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f))));
						return true;
					}
				};
				GameAudio.dogeBark();
				image_hardHighscore.setOrigin(image_hardHighscore.getWidth() / 4, image_hardHighscore.getHeight() / 2);
				image_hardHighscore.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_hardHighscore.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
				if(levelNum >0){
				chosenTime.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getTimeAlive());
				chosenStyle.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getStylePoints());
				chosenCaughtPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getCaughtPuppyNum());
				chosenMissedPups.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getMissedPuppyNum());
				chosenPupPoints.setText("" + DogeDashCore.db.getLevelHighscore(levelNum, tableName).getPuppyPoints());
				}
			}
		});


		image_back.addListener(new InputListener() {
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
				GameAudio.dogeBark();
				image_back.setOrigin(image_back.getWidth() / 4, image_back.getHeight() / 2);
				image_back.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_back.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

		stage.addActor(image_menu);

		stage.addActor(image_back);
		stage.addActor(image_menu_mombody);
		image_menu_bluepup.setOrigin(image_menu_bluepup.getWidth() / 2, image_menu_bluepup.getHeight());

		image_menu_bluepup.addAction(forever(sequence(rotateBy(5, 2), delay(0.5f), sequence(rotateBy(-5, 2)))));
		stage.addActor(image_menu_bluepup);
		stage.addActor(image_menu_mom_nose_paw);

		image_menu_creampup_body.setOrigin(image_menu_creampup_body.getWidth() / 2, image_menu_creampup_body.getHeight() / 2);
		image_menu_creampup_body.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		stage.addActor(image_menu_creampup_body);
		image_menu_creampup_paw.setOrigin(image_menu_creampup_paw.getWidth() / 2, image_menu_creampup_paw.getHeight() / 2);
		image_menu_creampup_paw.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		image_menu_creampup_paw.addAction(forever(sequence(rotateBy(20, 1), delay(0.5f), sequence(rotateBy(-20, 1)))));
		stage.addActor(image_menu_creampup_paw);
		image_menu_creampup_paw2.setOrigin(image_menu_creampup_paw.getWidth() / 2, image_menu_creampup_paw.getHeight() / 2);
		image_menu_creampup_paw2.addAction(forever(sequence(moveBy(0, 10, 1), delay(0.5f), sequence(moveBy(0, -10, 1)))));
		image_menu_creampup_paw2.addAction(forever(sequence(rotateBy(-20, 1), delay(0.5f), sequence(rotateBy(20, 1)))));
		stage.addActor(image_menu_creampup_paw2);
		// stage.addActor(new Actor() {
		// @Override
		// public void draw(SpriteBatch batch, float arg1) {
		// batch.end();
		// bg_rect.begin(ShapeType.FilledRectangle);
		// bg_rect.filledRect(0, 0, 800, 480);
		// bg_rect.setColor(0, 0f, 0f, 0.2f);
		// bg_rect.end();
		// batch.begin();
		// }
		// });

		stage.addActor(image_lvl1);
		stage.addActor(image_back);
		stage.addActor(image_lvl2);
		stage.addActor(image_lvl3);


		
		stage.addActor(image_normalHighscore);
		stage.addActor(image_hardHighscore);
		stage.addActor(image_easyHighscore);
		
		stage.addActor(image_chosenTime);
		stage.addActor(image_chosenCaughtPups);
		stage.addActor(image_chosenMissedPups);
		stage.addActor(image_chosenStyle);
		stage.addActor(image_chosenPupPoints);
		
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
		// TODO Auto-generated method stub

	}

}
