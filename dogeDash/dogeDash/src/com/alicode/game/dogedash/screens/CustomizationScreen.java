package com.alicode.game.dogedash.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.ArrayList;
import java.util.List;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.models.WindowOverlay;
import com.alicode.game.dogedash.sql.Costume;
import com.alicode.game.dogedash.sql.Misc;
import com.alicode.game.dogedash.utils.GameAudio;
import com.alicode.game.dogedash.utils.txt.GameText;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

	private Image image_customTitle, image_currentBox, image_currentTab, image_next, image_previous, image_currentItemsTxt, image_shopNo,
			image_shopYes, image_currentCoins, image_notEnough, image_curBuyingItem, image_curBuyingBox;

	private Image backImagePreview, noseImagePreview, eyesImagePreview, headImagePreview;

	private Image backImageEquip, noseImageEquip, eyesImageEquip, headImageEquip;
	private Array<Image> itemEquipBox;

	private WindowOverlay winOverlay;

	private Array<Image> image_shopCurrentItem;
	private Array<Image> image_shopCurrentItemBox;
	private Array<Image> image_shopCurrentItemLock;

	private Array<GameText> shopCurrentItemPriceTag;

	GameText shopItemNameText;

	private Array<TextureRegion> currentTextureList;
	private Array<TextureRegion> noseShopItems;
	private Array<TextureRegion> headShopItems;
	private Array<TextureRegion> backShopItems;
	private Array<TextureRegion> eyesShopItems;

	private Array<TextureRegion> noseShopItemsPreview;
	private Array<TextureRegion> headShopItemsPreview;
	private Array<TextureRegion> backShopItemsPreview;
	private Array<TextureRegion> eyesShopItemsPreview;

	private List<Costume> currentItemList;

	private Actor noseButton, eyesButton, headButton, backButton;

	private DogeDashCore game;

	private Image image_shop_after, image_shop_price;

	private GameText dogeCoins, leftOverCoins, selectedItem, currentDogeCoins;
	private String tableName;
	private static int buyingId, leftOverInt, helpInt;

	private Drawable tempDrawable;
	private Stage stage;
	private WindowOverlay winO;
	private MotherDoge motherDoge;

	private List<Costume> backCostumeList;
	private List<Costume> noseCostumeList;
	private List<Costume> headCostumeList;
	private List<Costume> eyesCostumeList;

	CustomState state = CustomState.Nose;
	MenuState menuState = MenuState.Ready;

	public CustomizationScreen(DogeDashCore game) {
		this.game = game;
		stage = new Stage();
		motherDoge = new MotherDoge();

		backCostumeList = DogeDashCore.db.getCostumeList("backTable");
		noseCostumeList = DogeDashCore.db.getCostumeList("noseTable");
		headCostumeList = DogeDashCore.db.getCostumeList("headTable");
		eyesCostumeList = DogeDashCore.db.getCostumeList("eyesTable");

		noseButton = new Actor();
		eyesButton = new Actor();
		headButton = new Actor();
		backButton = new Actor();
		winOverlay = new WindowOverlay();

		backImagePreview = new Image(Assets.acc_devilwings);
		backImagePreview.setPosition(-100, -100);
		noseImagePreview = new Image(Assets.acc_clownnose);
		noseImagePreview.setPosition(-100, -100);
		eyesImagePreview = new Image(Assets.acc_hipster);
		eyesImagePreview.setPosition(-100, -100);
		headImagePreview = new Image(Assets.acc_horns);
		headImagePreview.setPosition(-100, -100);

		backImageEquip = new Image(Assets.acc_horns);
		backImageEquip.setPosition(-100, -100);
		noseImageEquip = new Image(Assets.acc_horns);
		noseImageEquip.setPosition(-100, -100);
		eyesImageEquip = new Image(Assets.acc_horns);
		eyesImageEquip.setPosition(-100, -100);
		headImageEquip = new Image(Assets.acc_horns);
		headImageEquip.setPosition(-100, -100);

		backShopItems = new Array<TextureRegion>();
		noseShopItems = new Array<TextureRegion>();
		headShopItems = new Array<TextureRegion>();
		eyesShopItems = new Array<TextureRegion>();

		currentItemList = new ArrayList<Costume>();

		image_shopCurrentItem = new Array<Image>();
		image_shopCurrentItemBox = new Array<Image>();
		image_shopCurrentItemLock = new Array<Image>();
		itemEquipBox = new Array<Image>();
		shopCurrentItemPriceTag = new Array<GameText>();

		noseShopItemsPreview = new Array<TextureRegion>();
		headShopItemsPreview = new Array<TextureRegion>();
		backShopItemsPreview = new Array<TextureRegion>();
		eyesShopItemsPreview = new Array<TextureRegion>();

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

	}

	public void updateDatabaseItems() {
		backCostumeList = DogeDashCore.db.getCostumeList("backTable");
		noseCostumeList = DogeDashCore.db.getCostumeList("noseTable");
		headCostumeList = DogeDashCore.db.getCostumeList("headTable");
		eyesCostumeList = DogeDashCore.db.getCostumeList("eyesTable");

		Gdx.app.log(DogeDashCore.LOG, "------------------------------------------------------------------------------------------------");
		for (Costume obj : backCostumeList) {

			String log = "Id: " + obj.getId() + " Name: " + obj.getItemName() + " , owned: " + obj.getIsOwned() + " , price: " + obj.getItemPrice();

			Gdx.app.log(DogeDashCore.LOG, log);
		}
		Gdx.app.log(DogeDashCore.LOG, "------------------------------------------------------------------------------------------------");
		for (Costume obj : noseCostumeList) {

			String log = "Id: " + obj.getId() + " Name: " + obj.getItemName() + " , owned: " + obj.getIsOwned() + " , price: " + obj.getItemPrice();

			Gdx.app.log(DogeDashCore.LOG, log);

		}
		Gdx.app.log(DogeDashCore.LOG, "------------------------------------------------------------------------------------------------");
		for (Costume obj : headCostumeList) {

			String log = "Id: " + obj.getId() + " Name: " + obj.getItemName() + " , owned: " + obj.getIsOwned() + " , price: " + obj.getItemPrice();

			Gdx.app.log(DogeDashCore.LOG, log);
		}
		Gdx.app.log(DogeDashCore.LOG, "------------------------------------------------------------------------------------------------");
		for (Costume obj : eyesCostumeList) {

			String log = "Id: " + obj.getId() + " Name: " + obj.getItemName() + " , owned: " + obj.getIsOwned() + " , price: " + obj.getItemPrice();

			Gdx.app.log(DogeDashCore.LOG, log);
		}
		Gdx.app.log(DogeDashCore.LOG, "------------------------------------------------------------------------------------------------");

		dogeCoins.setText("" + DogeDashCore.db.getMisc(1).getDogeCoins());

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
		noseButton.setY(330);
		noseButton.setHeight(40);
		noseButton.setWidth(100);

		eyesButton.setX(noseButton.getX() + 100 + 20);
		eyesButton.setY(330);
		eyesButton.setHeight(40);
		eyesButton.setWidth(100);

		headButton.setX(eyesButton.getX() + 110 + 20);
		headButton.setY(330);
		headButton.setHeight(40);
		headButton.setWidth(100);

		backButton.setX(headButton.getX() + 110 + 20);
		backButton.setY(330);
		backButton.setHeight(40);
		backButton.setWidth(100);

		tempDrawable = new TextureRegionDrawable(Assets.tab_nose);
		image_currentTab = new Image(tempDrawable);
		image_currentTab.setX(270);
		image_currentTab.setY(100);

		tempDrawable = new TextureRegionDrawable(Assets.itemsEquipText);
		image_currentItemsTxt = new Image(tempDrawable);
		image_currentItemsTxt.setX(40);
		image_currentItemsTxt.setY(170);

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

		dogeCoins = new GameText(640, 428, DogeDashCore.db.getMisc(1).getDogeCoins() + "");

	}

	private void changeContent() {
		currentTextureList = new Array<TextureRegion>();

		for (int i = 0; i < image_shopCurrentItem.size; i++) {
			currentItemList.get(i).remove();
			image_shopCurrentItem.get(i).remove();
			image_shopCurrentItemBox.get(i).remove();

		}
		for (int i = 0; i < image_shopCurrentItemLock.size; i++) {
			image_shopCurrentItemLock.get(i).remove();
			shopCurrentItemPriceTag.get(i).remove();
		}

		if (image_shopCurrentItem.size > 1) {
			shopCurrentItemPriceTag.clear();
			image_shopCurrentItem.clear();
			image_shopCurrentItemBox.clear();
			image_shopCurrentItemLock.clear();
			currentTextureList.clear();
		}

		if (state == CustomState.Nose) {
			currentTextureList = noseShopItems;
			currentItemList = noseCostumeList;
		}
		if (state == CustomState.Eyes) {
			currentTextureList = eyesShopItems;
			currentItemList = eyesCostumeList;
		}
		if (state == CustomState.Head) {
			currentTextureList = headShopItems;
			currentItemList = headCostumeList;
		}
		if (state == CustomState.Back) {
			currentTextureList = backShopItems;
			currentItemList = backCostumeList;
		}

		for (int i = 0; i < currentTextureList.size; i++) {

			tempDrawable = new TextureRegionDrawable(currentTextureList.get(i));
			image_shopCurrentItem.add(new Image(tempDrawable));
			image_shopCurrentItem.get(i).setDrawable(tempDrawable);
			image_shopCurrentItem.get(i).setX(285 + 65 * i);
			image_shopCurrentItem.get(i).setY(240);

			tempDrawable = new TextureRegionDrawable(Assets.shopItemBox);
			image_shopCurrentItemBox.add(new Image(tempDrawable));
			image_shopCurrentItemBox.get(i).setX(285 + 65 * i);
			image_shopCurrentItemBox.get(i).setY(240);

			stage.addActor(image_shopCurrentItemBox.get(i));

			tempDrawable = new TextureRegionDrawable(Assets.itemLock);
			image_shopCurrentItemLock.add(new Image(tempDrawable));
			image_shopCurrentItemLock.get(i).setX(287 + 65 * i);
			image_shopCurrentItemLock.get(i).setY(282);

			shopCurrentItemPriceTag.add(new GameText(322 + 65 * i, 255, "" + currentItemList.get(i).getItemPrice()));
			shopCurrentItemPriceTag.get(i).setAlpha(0.6f);
			shopCurrentItemPriceTag.get(i).setSize(0.5f);

			if (currentItemList.get(i).getIsOwned() == 0) {
				stage.addActor(shopCurrentItemPriceTag.get(i));
				stage.addActor(image_shopCurrentItemLock.get(i));
			} else {
				image_shopCurrentItemLock.get(i).remove();
				shopCurrentItemPriceTag.get(i).remove();
			}

			buyingId = i;

			stage.addActor(image_shopCurrentItem.get(i));

			image_shopCurrentItem.get(i).addListener(new InputListener() {
				int myId = buyingId;

				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					if (menuState == MenuState.Ready && currentItemList.get(myId).getIsOwned() == 0) {
						GameAudio.dogeBark();
						menuState = MenuState.Buying;
						processItem(myId);
					} else {
						putDogeClothesOn(myId);
					}

				}

			});
		}
	}

	protected void putDogeClothesOn(int myId) {

		switch (state) {
		case Back:
			Statics.backCostume = myId;
			tempDrawable = new TextureRegionDrawable(backShopItemsPreview.get(myId));
			backImagePreview.setDrawable(tempDrawable);
			backImagePreview.setX(60);
			backImagePreview.setY(240);

			tempDrawable = new TextureRegionDrawable(backShopItems.get(myId));
			backImageEquip.setDrawable(tempDrawable);
			backImageEquip.setX(20 + 60 * 0);
			backImageEquip.setY(100);
			backImageEquip.setSize(backShopItems.get(myId).getRegionWidth(), backShopItems.get(myId).getRegionHeight());

			backImageEquip.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					backImageEquip.setDrawable(null);
					backImagePreview.setDrawable(null);
					Statics.backCostume = -1;
				}
			});
			break;
		case Eyes:
			Statics.eyeCostume = myId;

			tempDrawable = new TextureRegionDrawable(eyesShopItemsPreview.get(myId));
			eyesImagePreview.setDrawable(tempDrawable);
			eyesImagePreview.setX(60);
			eyesImagePreview.setY(240);

			tempDrawable = new TextureRegionDrawable(eyesShopItems.get(myId));
			eyesImageEquip.setDrawable(tempDrawable);
			eyesImageEquip.setX(20 + 60 * 2);
			eyesImageEquip.setY(100);
			eyesImageEquip.setSize(eyesShopItems.get(myId).getRegionWidth(), eyesShopItems.get(myId).getRegionHeight());

			eyesImageEquip.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					eyesImageEquip.setDrawable(null);
					eyesImagePreview.setDrawable(null);
					Statics.eyeCostume = -1;
				}
			});

			break;
		case Head:
			Statics.headCostume = myId;

			tempDrawable = new TextureRegionDrawable(headShopItemsPreview.get(myId));
			headImagePreview.setDrawable(tempDrawable);
			headImagePreview.setX(60);
			headImagePreview.setY(240);

			tempDrawable = new TextureRegionDrawable(headShopItems.get(myId));
			headImageEquip.setDrawable(tempDrawable);
			headImageEquip.setX(20 + 60 * 1);
			headImageEquip.setY(100);
			headImageEquip.setSize(headShopItems.get(myId).getRegionWidth(), headShopItems.get(myId).getRegionHeight());

			headImageEquip.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					headImageEquip.setDrawable(null);
					headImagePreview.setDrawable(null);
					Statics.headCostume = -1;
				}
			});

			break;
		case Nose:
			Statics.noseCostume = myId;

			tempDrawable = new TextureRegionDrawable(noseShopItemsPreview.get(myId));
			noseImagePreview.setDrawable(tempDrawable);
			noseImagePreview.setX(60);
			noseImagePreview.setY(240);

			tempDrawable = new TextureRegionDrawable(noseShopItems.get(myId));
			noseImageEquip.setDrawable(tempDrawable);
			noseImageEquip.setX(20 + 60 * 3);
			noseImageEquip.setY(100);
			noseImageEquip.setSize(noseShopItems.get(myId).getRegionWidth(), noseShopItems.get(myId).getRegionHeight());

			noseImageEquip.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					noseImageEquip.setDrawable(null);
					noseImagePreview.setDrawable(null);
					Statics.noseCostume = -1;
				}
			});

			break;
		}

	}

	private void processItem(int itemId) {
		if (menuState == MenuState.Buying) {
			winO = new WindowOverlay();
			winO.setX(200);
			winO.setY(40);
			winO.setAlpha(0.8f);
			winO.setWidth(stage.getWidth() / 2);
			winO.setHeight(350);

			tempDrawable = new TextureRegionDrawable(Assets.shop_price);
			image_shop_price = new Image(tempDrawable);
			image_shop_price.setX(210);
			image_shop_price.setY(250);

			tempDrawable = new TextureRegionDrawable(Assets.shop_currentcoins);
			image_currentCoins = new Image(tempDrawable);
			image_currentCoins.setX(210);
			image_currentCoins.setY(200);

			tempDrawable = new TextureRegionDrawable(Assets.shop_after);
			image_shop_after = new Image(tempDrawable);
			image_shop_after.setX(210);
			image_shop_after.setY(150);

			tempDrawable = new TextureRegionDrawable(Assets.shop_no);
			image_shopNo = new Image(tempDrawable);
			image_shopNo.setX(495);
			image_shopNo.setY(50);

			tempDrawable = new TextureRegionDrawable(Assets.shop_yes);
			image_shopYes = new Image(tempDrawable);
			image_shopYes.setX(210);
			image_shopYes.setY(50);

			image_curBuyingItem = image_shopCurrentItem.get(itemId);
			image_curBuyingItem.setX(370);
			image_curBuyingItem.setY(300);

			image_curBuyingBox = image_shopCurrentItemBox.get(itemId);
			image_curBuyingBox.setX(370);
			image_curBuyingBox.setY(300);

			leftOverInt = DogeDashCore.db.getMisc(1).getDogeCoins() - currentItemList.get(itemId).getItemPrice();
			selectedItem = new GameText(500, 275, "" + currentItemList.get(itemId).getItemPrice());
			currentDogeCoins = new GameText(500, 225, "" + DogeDashCore.db.getMisc(1).getDogeCoins());
			leftOverCoins = new GameText(500, 175, "" + leftOverInt);
			shopItemNameText = new GameText(322, 390, "" + currentItemList.get(itemId).getItemName());

			if (leftOverInt < 0)
				leftOverCoins.setColor(Color.RED);

			stage.addActor(winO);
			stage.addActor(image_shop_after);
			stage.addActor(image_shop_price);
			stage.addActor(image_shopNo);
			stage.addActor(image_shopYes);
			stage.addActor(image_currentCoins);
			stage.addActor(image_curBuyingBox);
			stage.addActor(image_curBuyingItem);

			stage.addActor(leftOverCoins);
			stage.addActor(currentDogeCoins);
			stage.addActor(selectedItem);
			stage.addActor(shopItemNameText);

			helpInt = itemId;
			image_shopYes.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					if (menuState == MenuState.Buying && leftOverInt >= 0) {
						GameAudio.dogeBark();
						buyItem(helpInt);
					} else {
						if (image_notEnough != null)
							image_notEnough.remove();

						tempDrawable = new TextureRegionDrawable(Assets.shop_not_enough);
						image_notEnough = new Image(tempDrawable);
						image_notEnough.setX(255);
						image_notEnough.setY(330);
						stage.addActor(image_notEnough);
						image_notEnough.setOrigin(image_notEnough.getWidth() / 2, image_notEnough.getHeight() / 2);
						image_notEnough.addAction(Actions.repeat(3,
								Actions.sequence(Actions.scaleBy(0.2f, 0.2f, 0.5f), Actions.scaleBy(-0.2f, -0.2f, 0.5f))));

					}

				}

			});

			image_shopNo.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}

				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					if (menuState == MenuState.Buying) {
						GameAudio.dogeBark();
						removeActors();
					}

				}

			});

			Gdx.app.log(DogeDashCore.LOG, "Menu state: " + menuState + " Doge state: " + state + " table: " + tableName + " image_shopCurrentItem "
					+ image_shopCurrentItem.size + " currentTextureList " + currentTextureList.size + " shopCurrentItemPriceTag: "
					+ shopCurrentItemPriceTag.size);

		}
	}

	private void buyItem(int currentItemId) {

		switch (state) {
		case Back:
			DogeDashCore.db.updateCostume(new Costume(currentItemId + 1, currentItemList.get(helpInt).getItemName(), 1, currentItemList.get(helpInt)
					.getItemPrice()), "backTable");
			Gdx.app.log(DogeDashCore.LOG, " currentItemId " + currentItemId);
			break;
		case Eyes:
			DogeDashCore.db.updateCostume(new Costume(currentItemId + 1, currentItemList.get(helpInt).getItemName(), 1, currentItemList.get(helpInt)
					.getItemPrice()), "eyesTable");
			Gdx.app.log(DogeDashCore.LOG, " currentItemId " + currentItemId);
			break;
		case Head:
			DogeDashCore.db.updateCostume(new Costume(currentItemId + 1, currentItemList.get(helpInt).getItemName(), 1, currentItemList.get(helpInt)
					.getItemPrice()), "headTable");
			Gdx.app.log(DogeDashCore.LOG, " currentItemId " + currentItemId);
			break;
		case Nose:
			DogeDashCore.db.updateCostume(new Costume(currentItemId + 1, currentItemList.get(helpInt).getItemName(), 1, currentItemList.get(helpInt)
					.getItemPrice()), "noseTable");
			Gdx.app.log(DogeDashCore.LOG, " currentItemId " + currentItemId);
			break;
		}

		DogeDashCore.db.updateMisc(new Misc(1, leftOverInt));
		removeActors();
		Gdx.app.log(DogeDashCore.LOG, " currentItemId " + currentItemId);

	}

	private void removeActors() {
		updateDatabaseItems();
		winO.remove();
		image_shop_after.remove();
		image_shop_price.remove();
		image_shopNo.remove();
		image_shopYes.remove();
		image_currentCoins.remove();
		image_curBuyingItem.remove();
		image_curBuyingBox.remove();
		leftOverCoins.remove();
		currentDogeCoins.remove();
		selectedItem.remove();
		menuState = MenuState.Ready;
		shopItemNameText.remove();

		if (image_notEnough != null)
			image_notEnough.remove();

		changeContent();
	}

	private void initInput() {

		noseButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (menuState == MenuState.Ready) {
					GameAudio.dogeBark();
					tempDrawable = new TextureRegionDrawable(Assets.tab_nose);
					image_currentTab.setDrawable(tempDrawable);
					tableName = "noseTable";
					state = CustomState.Nose;
					changeContent();
				}

			}

		});

		eyesButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (menuState == MenuState.Ready) {
					GameAudio.dogeBark();
					tempDrawable = new TextureRegionDrawable(Assets.tab_eyes);
					image_currentTab.setDrawable(tempDrawable);
					tableName = "eyesTable";
					state = CustomState.Eyes;
					changeContent();
				}
			}
		});

		headButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (menuState == MenuState.Ready) {
					GameAudio.dogeBark();
					tempDrawable = new TextureRegionDrawable(Assets.tab_head);
					image_currentTab.setDrawable(tempDrawable);
					tableName = "headTable";
					state = CustomState.Head;
					changeContent();
				}
			}
		});

		backButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (menuState == MenuState.Ready) {
					GameAudio.dogeBark();
					tempDrawable = new TextureRegionDrawable(Assets.tab_back);
					image_currentTab.setDrawable(tempDrawable);
					tableName = "backTable";
					state = CustomState.Back;
					changeContent();
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
		stage.addActor(dogeCoins);

		stage.addActor(backImagePreview);
		stage.addActor(eyesImagePreview);
		stage.addActor(noseImagePreview);
		stage.addActor(headImagePreview);

		for (int i = 0; i < 4; i++) {

			tempDrawable = new TextureRegionDrawable(Assets.shopItemBox);
			itemEquipBox.add(new Image(tempDrawable));
			itemEquipBox.get(i).setX(20 + 60 * i);
			itemEquipBox.get(i).setY(100);
			stage.addActor(itemEquipBox.get(i));

		}

		stage.addActor(backImageEquip);
		stage.addActor(eyesImageEquip);
		stage.addActor(noseImageEquip);
		stage.addActor(headImageEquip);

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