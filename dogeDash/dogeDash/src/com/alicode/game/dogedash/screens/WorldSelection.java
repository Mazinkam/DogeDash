package com.alicode.game.dogedash.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.logging.Level;

import javax.sound.midi.Sequence;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.WindowOverlay;
import com.alicode.game.dogedash.utils.GameAudio;
import com.alicode.game.dogedash.worlds.WorldTerminal;
import com.alicode.game.dogedash.worlds.WorldTwo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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

public class WorldSelection implements Screen {

	enum MenuState {
		Ready, Chosing
	}

	private MenuState menuState = MenuState.Ready;
	private Image imageMenuBg, imageMenuMomNosePaw, imageMenuMomBody, imageMenuBlackPup, imageMenuBlackPup2, imageMenuCreamPupBody,
			imageMenuCreamPupPaw, imageMenuCreamPupPaw2, imageMenuBluePup;

	private Image imageLevelSelect, imageLevelSelectText, imageHard, imageEasy, imageNormal, imageSelectDifficulty, imageLevel1, imageLevel2,
			imageTutorialLevelSelect, imageBackButton;

	private DogeDashCore game;
	private Stage stage;
	private Drawable tempDrawable;
	private WindowOverlay winOverlay, winOverlay2;

	public WorldSelection(DogeDashCore game) {
		this.game = game;
		winOverlay = new WindowOverlay();
		winOverlay2 = new WindowOverlay();
		stage = new Stage();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		initBackground();
		initForeground();
		initInput();
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

		tempDrawable = new TextureRegionDrawable(Assets.menu_blackpup);
		imageMenuBlackPup = new Image(tempDrawable);
		imageMenuBlackPup.setX(10);
		imageMenuBlackPup.setY(40);

		tempDrawable = new TextureRegionDrawable(Assets.menu_blackpup2);
		imageMenuBlackPup2 = new Image(tempDrawable);
		imageMenuBlackPup2.setX(260);
		imageMenuBlackPup2.setY(250);

		tempDrawable = new TextureRegionDrawable(Assets.back);
		imageBackButton = new Image(tempDrawable);
		imageBackButton.setX(660);
		imageBackButton.setY(20);

	}

	private void initForeground() {
		tempDrawable = new TextureRegionDrawable(Assets.lvlselect);
		imageLevelSelect = new Image(tempDrawable);
		imageLevelSelect.setX(50);
		imageLevelSelect.setY(360);

		tempDrawable = new TextureRegionDrawable(Assets.lvlselect_txt);
		imageLevelSelectText = new Image(tempDrawable);
		imageLevelSelectText.setX(30);
		imageLevelSelectText.setY(340);

		tempDrawable = new TextureRegionDrawable(Assets.lvl1);
		imageLevel1 = new Image(tempDrawable);
		imageLevel1.setX(30);
		imageLevel1.setY(160);

		tempDrawable = new TextureRegionDrawable(Assets.lvl2);
		imageLevel2 = new Image(tempDrawable);
		imageLevel2.setX(30 + imageLevel1.getWidth() + 30);
		imageLevel2.setY(160);

		tempDrawable = new TextureRegionDrawable(Assets.tutorial_select);
		imageTutorialLevelSelect = new Image(tempDrawable);
		imageTutorialLevelSelect.setX(30);
		imageTutorialLevelSelect.setY(10);

		tempDrawable = new TextureRegionDrawable(Assets.difficulty);
		imageSelectDifficulty = new Image(tempDrawable);
		imageSelectDifficulty.setX(300);
		imageSelectDifficulty.setY(400);

		tempDrawable = new TextureRegionDrawable(Assets.hard);
		imageHard = new Image(tempDrawable);
		imageHard.setX(300);
		imageHard.setY(300);

		tempDrawable = new TextureRegionDrawable(Assets.normal);
		imageNormal = new Image(tempDrawable);
		imageNormal.setX(300);
		imageNormal.setY(200);

		tempDrawable = new TextureRegionDrawable(Assets.easy);
		imageEasy = new Image(tempDrawable);
		imageEasy.setX(300);
		imageEasy.setY(100);

	}

	private void initInput() {
		imageLevel1.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						// game.setScreen(new WorldTerminal(game,
						// Gdx.graphics.getDeltaTime()));
						Statics.gameLevel = 1;
						menuState = MenuState.Chosing;
						return true;
					}
				};
				GameAudio.dogeBark();
				imageLevel1.setOrigin(imageLevel1.getWidth() / 4, imageLevel1.getHeight() / 2);
				imageLevel1.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageLevel1.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

		imageLevel2.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						// game.setScreen(new WorldTerminal(game,
						// Gdx.graphics.getDeltaTime()));
						Statics.gameLevel = 2;
						menuState = MenuState.Chosing;
						return true;
					}
				};
				GameAudio.dogeBark();

				imageLevel2.setOrigin(imageLevel2.getWidth() / 4, imageLevel2.getHeight() / 2);
				imageLevel2.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageLevel2.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

		imageTutorialLevelSelect.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						// game.setScreen(new SplashScreen(game));

						return true;
					}
				};
				GameAudio.dogeBark();
				imageTutorialLevelSelect.setOrigin(imageTutorialLevelSelect.getWidth() / 4, imageTutorialLevelSelect.getHeight() / 2);
				imageTutorialLevelSelect.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageTutorialLevelSelect.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f),
						rotateBy(-5, 0.3f, Interpolation.swing), completeAction)));

			}

		});

		imageBackButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						if (menuState == MenuState.Ready) {
							game.setScreen(new MenuScreen(game));
						} else {
							menuState = MenuState.Ready;
						}

						return true;
					}
				};
				GameAudio.dogeBark();
				imageBackButton.setOrigin(imageBackButton.getWidth() / 4, imageBackButton.getHeight() / 2);
				imageBackButton.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageBackButton.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});
		
		imageHard.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						Statics.gameLevelDifficulty = 1;
						game.setScreen(new WorldTerminal(game, Gdx.graphics.getDeltaTime()));
						return true;
					}
				};
				GameAudio.dogeBark();
				imageHard.setOrigin(imageHard.getWidth() / 4, imageHard.getHeight() / 2);
				imageHard.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageHard.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});
		imageNormal.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						if (menuState == MenuState.Ready) {
							game.setScreen(new MenuScreen(game));
						} else {
							menuState = MenuState.Ready;
						}

						return true;
					}
				};
				GameAudio.dogeBark();
				imageNormal.setOrigin(imageNormal.getWidth() / 4, imageNormal.getHeight() / 2);
				imageNormal.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageNormal.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});
		imageEasy.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						if (menuState == MenuState.Ready) {
							game.setScreen(new MenuScreen(game));
						} else {
							menuState = MenuState.Ready;
						}

						return true;
					}
				};
				GameAudio.dogeBark();
				imageEasy.setOrigin(imageEasy.getWidth() / 4, imageEasy.getHeight() / 2);
				imageEasy.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				imageEasy.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
			}
		});

	}

	private void initActors() {
		stage.addActor(imageMenuBg);

		stage.addActor(imageMenuBlackPup);
		stage.addActor(imageMenuBlackPup2);

		stage.addActor(imageMenuMomBody);
		imageMenuBluePup.setOrigin((imageMenuBluePup.getWidth() / 2), imageMenuBluePup.getHeight());

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

		stage.addActor(imageLevelSelect);
		stage.addActor(imageLevelSelectText);

		stage.addActor(imageLevel1);
		stage.addActor(imageLevel2);
		stage.addActor(imageTutorialLevelSelect);
	

		stage.addActor(winOverlay2);
		stage.addActor(imageBackButton);
		stage.addActor(imageNormal);
		stage.addActor(imageSelectDifficulty);
		stage.addActor(imageHard);
		stage.addActor(imageEasy);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		drawLevelDifficulty();

	}

	private void drawLevelDifficulty() {
		if (menuState == MenuState.Chosing) {
			imageSelectDifficulty.setVisible(true);
			imageHard.setVisible(true);
			imageNormal.setVisible(true);
			imageEasy.setVisible(true);
			winOverlay2.setVisible(true);
		}
		else{
			imageSelectDifficulty.setVisible(false);
			imageHard.setVisible(false);
			imageNormal.setVisible(false);
			imageEasy.setVisible(false);
			winOverlay2.setVisible(false);
		}

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
