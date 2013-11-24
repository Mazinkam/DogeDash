package com.alicode.game.dogedash.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.models.WindowOverlay;
import com.alicode.game.dogedash.models.enemies.EnemyBee;
import com.alicode.game.dogedash.sql.Costume;
import com.alicode.game.dogedash.utils.GameAudio;
import com.alicode.game.dogedash.utils.GameInput;
import com.alicode.game.dogedash.utils.txt.GameText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;

public class CustomizationScreen implements Screen {

	enum MenuState {
		Buying, Ready
	}

	enum CustomState {
		Nose, Eyes, Head, Back
	}

	private Image image_menu, image_back, image_menu_mom_nose_paw, image_menu_mombody, image_menu_creampup_body, image_menu_creampup_paw,
			image_menu_creampup_paw2, image_menu_bluepup, image_dogeCoin;

	private Image image_customTitle, image_currentBox, image_currentTab, image_next, image_previous, image_currentItemsTxt;

	private Image image_shopBackItem, image_shopHeadItem, image_shopNoseItem, image_shopBox, image_itemLock;
	private Image backImagePreview, noseImagePreview, eyesImagePreview, headImagePreview;
	
	private WindowOverlay winOverlay;

	private Array<Image> image_shopCurrentItem;
	private Array<Image> image_shopCurrentItemBox;
	private Array<Image> image_shopCurrentItemLock;
	Array<TextureRegion> currentList;
	ArrayList<Integer> currentItemOwnedList;
	ArrayList<Integer> currentItemPriceList;

	private Array<TextureRegion> noseShopItems;
	private Array<TextureRegion> headShopItems;
	private Array<TextureRegion> backShopItems;
	private Array<TextureRegion> eyesShopItems;

	private Array<TextureRegion> noseShopItemsPreview;
	private Array<TextureRegion> headShopItemsPreview;
	private Array<TextureRegion> backShopItemsPreview;
	private Array<TextureRegion> eyesShopItemsPreview;

	private ArrayList<Integer> backItemOwnedList, backitemPriceList;
	private ArrayList<Integer> noseItemOwnedList, noseitemPriceList;
	private ArrayList<Integer> eyesItemOwnedList, eyesitemPriceList;
	private ArrayList<Integer> headItemOwnedList, headitemPriceList;

	private Actor noseButton, eyesButton, headButton, backButton;

	private DogeDashCore game;

	private GameText dogeCoins, itemPrice, leftOverCoins, selectedItem;
	private String tableName;

	private Drawable tempDrawable;
	private Stage stage;
	private MotherDoge motherDoge;
	private Array<Actor> shopItems;

	private List<Costume> backCostumeList;
	private List<Costume> noseCostumeList;
	private List<Costume> headCostumeList;
	private List<Costume> eyesCostumeList;
	private GameInput gameInput;
	CustomState state = CustomState.Nose;
	MenuState menuState = MenuState.Ready;

	public CustomizationScreen(DogeDashCore game) {

		this.game = game;
		stage = new Stage();
		motherDoge = new MotherDoge();
		shopItems = new Array<Actor>();

		noseButton = new Actor();
		eyesButton = new Actor();
		headButton = new Actor();
		backButton = new Actor();
		winOverlay = new WindowOverlay();

		backCostumeList = DogeDashCore.db.getCostumeList("backTable");
		noseCostumeList = DogeDashCore.db.getCostumeList("noseTable");
		headCostumeList = DogeDashCore.db.getCostumeList("headTable");
		eyesCostumeList = DogeDashCore.db.getCostumeList("eyesTable");

		backShopItems = new Array<TextureRegion>();
		noseShopItems = new Array<TextureRegion>();
		headShopItems = new Array<TextureRegion>();
		eyesShopItems = new Array<TextureRegion>();
		image_shopCurrentItem = new Array<Image>();
		image_shopCurrentItemLock = new Array<Image>();
		image_shopCurrentItemBox = new Array<Image>();

		noseShopItemsPreview = new Array<TextureRegion>();
		headShopItemsPreview = new Array<TextureRegion>();
		backShopItemsPreview = new Array<TextureRegion>();
		eyesShopItemsPreview = new Array<TextureRegion>();

		backItemOwnedList = new ArrayList<Integer>();
		backitemPriceList = new ArrayList<Integer>();

		eyesItemOwnedList = new ArrayList<Integer>();
		eyesitemPriceList = new ArrayList<Integer>();

		headItemOwnedList = new ArrayList<Integer>();
		headitemPriceList = new ArrayList<Integer>();

		noseItemOwnedList = new ArrayList<Integer>();
		noseitemPriceList = new ArrayList<Integer>();

		backShopItems.add(Assets.shop_angel);
		backShopItems.add(Assets.shop_devilwings);
		backShopItemsPreview.add(Assets.acc_angel);
		backShopItemsPreview.add(Assets.acc_devilwings);

		noseShopItems.add(Assets.shop_moustache);
		noseShopItems.add(Assets.shop_clownnose);
		noseShopItemsPreview.add(Assets.acc_moustache);
		noseShopItemsPreview.add(Assets.acc_clownnose);

		eyesShopItems.add(Assets.shop_shades);
		eyesShopItems.add(Assets.shop_monocle);
		eyesShopItems.add(Assets.shop_hipster);
		eyesShopItems.add(Assets.shop_unibrow);
		eyesShopItemsPreview.add(Assets.acc_shades);
		eyesShopItemsPreview.add(Assets.acc_monocle);
		eyesShopItemsPreview.add(Assets.acc_hipster);
		eyesShopItemsPreview.add(Assets.acc_unibrow);

		headShopItems.add(Assets.shop_tophat);
		headShopItems.add(Assets.shop_horns);
		headShopItems.add(Assets.shop_halo);
		headShopItems.add(Assets.shop_pumpkin);
		headShopItems.add(Assets.shop_santahat);
		headShopItemsPreview.add(Assets.acc_tophat);
		headShopItemsPreview.add(Assets.acc_horns);
		headShopItemsPreview.add(Assets.acc_halo);
		headShopItemsPreview.add(Assets.acc_pumpkin);
		headShopItemsPreview.add(Assets.acc_santahat);

		for (Costume obj : backCostumeList) {
			backItemOwnedList.add(obj.getIsOwned());
			backitemPriceList.add(obj.getItemPrice());
		}
		for (Costume obj : noseCostumeList) {
			noseItemOwnedList.add(obj.getIsOwned());
			noseitemPriceList.add(obj.getItemPrice());

		}
		for (Costume obj : headCostumeList) {
			headItemOwnedList.add(obj.getIsOwned());
			headitemPriceList.add(obj.getItemPrice());

		}
		for (Costume obj : eyesCostumeList) {
			eyesItemOwnedList.add(obj.getIsOwned());
			eyesitemPriceList.add(obj.getItemPrice());

		}

		tableName = "noseTable";

	}

	@Override
	public void show() {

		Gdx.input.setInputProcessor(stage);
		// background shit
		initBackground();
		initMenuBody();
		initInput();
		initActors();

	}

	private void initMenuBody() {
		tempDrawable = new TextureRegionDrawable(Assets.custom_title);
		image_customTitle = new Image(tempDrawable);
		image_customTitle.setX(20);
		image_customTitle.setY(400);

		tempDrawable = new TextureRegionDrawable(Assets.chowcoin);
		image_dogeCoin = new Image(tempDrawable);
		image_dogeCoin.setX(600);
		image_dogeCoin.setY(400);

		tempDrawable = new TextureRegionDrawable(Assets.currentBox);
		image_currentBox = new Image(tempDrawable);
		image_currentBox.setX(20);
		image_currentBox.setY(224);

		noseButton.setX(280);
		noseButton.setY(270);
		noseButton.setHeight(100);
		noseButton.setWidth(100);

		eyesButton.setX(noseButton.getX() + 100 + 20);
		eyesButton.setY(270);
		eyesButton.setHeight(100);
		eyesButton.setWidth(100);

		headButton.setX(eyesButton.getX() + 110 + 20);
		headButton.setY(270);
		headButton.setHeight(100);
		headButton.setWidth(100);

		backButton.setX(headButton.getX() + 110 + 20);
		backButton.setY(270);
		backButton.setHeight(100);
		backButton.setWidth(100);

		tempDrawable = new TextureRegionDrawable(Assets.tab_nose);
		image_currentTab = new Image(tempDrawable);
		image_currentTab.setX(270);
		image_currentTab.setY(100);

		tempDrawable = new TextureRegionDrawable(Assets.itemsEquipText);
		image_currentItemsTxt = new Image(tempDrawable);
		image_currentItemsTxt.setX(30);
		image_currentItemsTxt.setY(160);

		tempDrawable = new TextureRegionDrawable(Assets.button_next);
		image_next = new Image(tempDrawable);
		image_next.setX(700);
		image_next.setY(110);

		tempDrawable = new TextureRegionDrawable(Assets.button_previous);
		image_previous = new Image(tempDrawable);
		image_previous.setX(285);
		image_previous.setY(110);

	}

	private void initBackground() {
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

		tempDrawable = new TextureRegionDrawable(Assets.back);
		image_back = new Image(tempDrawable);
		image_back.setX(660);
		image_back.setY(20);

		motherDoge.setX(60);
		motherDoge.setY(240);

	}

	private void changeContent() {
		currentList = new Array<TextureRegion>();
		currentItemOwnedList = new ArrayList<Integer>();
		currentItemPriceList = new ArrayList<Integer>();

		for (int i = 0; i < image_shopCurrentItem.size; i++) {

			image_shopCurrentItem.get(i).remove();
			image_shopCurrentItemBox.get(i).remove();
			image_shopCurrentItemLock.get(i).remove();
		}
		if (image_shopCurrentItem.size > 1) {

			image_shopCurrentItem.clear();
			image_shopCurrentItemBox.clear();
			image_shopCurrentItemLock.clear();
			currentList.clear();
			currentItemOwnedList.clear();
			currentItemPriceList.clear();
		}

		if (state == CustomState.Nose) {
			currentList = noseShopItems;
			currentItemOwnedList = noseItemOwnedList;
			currentItemPriceList = noseitemPriceList;
		}
		if (state == CustomState.Eyes) {
			currentList = eyesShopItems;
			currentItemOwnedList = eyesItemOwnedList;
			currentItemPriceList = eyesitemPriceList;
		}
		if (state == CustomState.Head) {
			currentList = headShopItems;
			currentItemOwnedList = headItemOwnedList;
			currentItemPriceList = headitemPriceList;
		}
		if (state == CustomState.Back) {
			currentList = backShopItems;
			currentItemOwnedList = backItemOwnedList;
			currentItemPriceList = backitemPriceList;

		}
		for (int i = 0; i < currentList.size; i++) {
			tempDrawable = new TextureRegionDrawable(currentList.get(i));
			image_shopCurrentItem.add(new Image(tempDrawable));
			image_shopCurrentItem.get(i).setDrawable(tempDrawable);
			image_shopCurrentItem.get(i).setX(285 + 65 * i);
			image_shopCurrentItem.get(i).setY(240);

			tempDrawable = new TextureRegionDrawable(Assets.shopItemBox);
			image_shopCurrentItemBox.add(new Image(tempDrawable));
			image_shopCurrentItemBox.get(i).setX(285 + 65 * i);
			image_shopCurrentItemBox.get(i).setY(240);

			if (currentItemOwnedList.get(i) == 0) {
				tempDrawable = new TextureRegionDrawable(Assets.itemLock);
				image_shopCurrentItemLock.add(new Image(tempDrawable));
				image_shopCurrentItemLock.get(i).setX(285 + 65 * i);
				image_shopCurrentItemLock.get(i).setY(240);
			}

			stage.addActor(image_shopCurrentItem.get(i));
			stage.addActor(image_shopCurrentItemLock.get(i));
			stage.addActor(image_shopCurrentItemBox.get(i));

			image_shopCurrentItemBox.get(i).addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					Action completeAction = new Action() {
						public boolean act(float delta) {
							// game.setScreen(new MenuScreen(game));
							return true;
						}
					};
					GameAudio.dogeBark();
					menuState = MenuState.Buying;
					buySelectedItem();

				}

			});
		}

		Gdx.app.log(DogeDashCore.LOG, "Cur state: " + state + " Curr table: " + tableName + " image_shopCurrentItem " + image_shopCurrentItem.size
				+ " currentList " + currentList.size);

	}

	private void buySelectedItem() {

	


	}

	private void initInput() {
		noseButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						// game.setScreen(new MenuScreen(game));
						return true;
					}
				};
				GameAudio.dogeBark();
				tempDrawable = new TextureRegionDrawable(Assets.tab_nose);
				image_currentTab.setDrawable(tempDrawable);
				tableName = "noseTable";
				state = CustomState.Nose;
				changeContent();

			}

		});

		eyesButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						// game.setScreen(new MenuScreen(game));
						return true;
					}
				};
				GameAudio.dogeBark();
				tempDrawable = new TextureRegionDrawable(Assets.tab_eyes);
				image_currentTab.setDrawable(tempDrawable);
				tableName = "eyesTable";
				state = CustomState.Eyes;
				changeContent();
			}
		});

		headButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						// game.setScreen(new MenuScreen(game));
						return true;
					}
				};
				GameAudio.dogeBark();
				tempDrawable = new TextureRegionDrawable(Assets.tab_head);
				image_currentTab.setDrawable(tempDrawable);
				tableName = "headTable";
				state = CustomState.Head;
				changeContent();
			}
		});

		backButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Action completeAction = new Action() {
					public boolean act(float delta) {
						// //game.setScreen(new MenuScreen(game));
						return true;
					}
				};
				GameAudio.dogeBark();
				image_back.setOrigin(image_back.getWidth() / 4, image_back.getHeight() / 2);
				image_back.addAction(sequence(Actions.scaleBy(.1f, 0.1f, 0.2f), Actions.scaleTo(1, 1, 0.2f), delay(0.5f)));
				image_back.addAction((sequence(rotateBy(5, 0.3f, Interpolation.swing), delay(0.2f), rotateBy(-5, 0.3f, Interpolation.swing),
						completeAction)));
				tempDrawable = new TextureRegionDrawable(Assets.tab_back);
				image_currentTab.setDrawable(tempDrawable);
				tableName = "backTable";
				state = CustomState.Back;
				changeContent();
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

	}

	private void initActors() {
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
		stage.addActor(winOverlay);
		stage.addActor(image_back);

		stage.addActor(image_customTitle);
		stage.addActor(image_dogeCoin);
		stage.addActor(image_currentBox);
		stage.addActor(image_currentTab);
		
		stage.addActor(image_next);
		stage.addActor(image_previous);
		stage.addActor(image_currentItemsTxt);
		stage.addActor(motherDoge);
		stage.addActor(noseButton);
		stage.addActor(eyesButton);
		stage.addActor(headButton);
		stage.addActor(backButton);
		
	

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
