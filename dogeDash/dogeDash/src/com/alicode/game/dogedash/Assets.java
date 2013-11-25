package com.alicode.game.dogedash;

import java.util.logging.Level;

import com.alicode.game.dogedash.screens.OptionsScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sun.xml.internal.ws.api.pipe.NextAction;

public class Assets {
	public static TextureAtlas atlas;
	public static TextureRegion menu_blackpup, menu_blackpup2, menu_bluepup, menu_creampup_body, menu_creampup_paw, menu_creampup_paw2,
			menu_mom_nose_paw, menu_mombody;
	public static TextureRegion menu, splash, background, background2, ninePatchBox;

	public static TextureRegion character, character2, characterJump, characterHit, characterHit2, characterDie, characterDieCircle, dogsuper1,
			dogsuper2, energy1, energy2, energy3, night_dogsuper2;

	public static TextureRegion night_character, night_character2, night_characterJump, night_characterHit, night_characterHit2, night_characterDie,
			night_characterDieCircle, night_dogsuper1, night_energy1, night_energy2, night_energy3;

	public static TextureRegion blackPup, blackPup2, creamPup, creamPup2, bluePup, bluePup2, redPup, redPup2;

	public static TextureRegion night_blackPup, night_blackPup2, night_creamPup, night_creamPup2, night_bluePup, night_bluePup2, night_redPup,
			night_redPup2;

	public static TextureRegion gamePuddle, gamePuddle2, gameMud, gameMud2, hurdleLog, pow, style, flower1, flower2, flower3, flower4, flower5,
			gameBush, night_gameMud, night_gameMud2, night_gamePuddle2, night_gamePuddle, night_hurdleLog, treat1, treat2, shield_pickup, shield;

	public static TextureRegion night_flower1, night_flower2, night_flower3, night_flower4, night_flower5, night_gameBush;

	public static TextureRegion enemyBee, enemyBee2, enemyBee3, enemyMoth, enemyMoth2, enemyMoth3;

	public static TextureRegion back, customization, difficulty, credit, easy, hard, highscores, lvl1, lvl2, lvlselect, lvlselect_txt, musicoff,
			musicon, normal, options, options_txt, options_title, play, puppydash, soundoff, soundon, tutorial, vibrationoff, vibrationon, retry,
			gameovertitle, gametokens, mainmenu, puppiescaught, stylepoints, time, totalscore;

	public static TextureRegion hardSmall, easySmall, normalSmall;

	public static TextureRegion acc_angel, acc_angelj, acc_monocle, acc_tophatj, acc_tophat, acc_monoclej, acc_shades, acc_shadesj, acc_halo,
			acc_haloj, acc_hipster, acc_hipsterj, acc_horns, acc_hornsj, acc_moustache, acc_moustachej, acc_clownnose, acc_clownnosej,
			acc_devilwings, acc_devilwingsj, acc_pumpkin, acc_pumpkinj, acc_santahat, acc_santahatj, acc_unibrow, acc_unibrowj;

	public static TextureRegion mudDrop, waterDrop;

	public static TextureRegion shop_shades, shop_monocle, shop_tophat, shop_halo, shop_horns, shop_hipster, shop_moustache, shop_angel,
			shop_clownnose, shop_devilwings, shop_pumpkin, shop_unibrow, shop_santahat;

	public static TextureRegion custom_title, currentBox, chowcoin, itemLock, shopItemBox;

	public static TextureRegion num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, num0_2, num1_2, num2_2, num3_2, num4_2, num5_2, num6_2,
			num7_2, num8_2, num9_2, numMinus, numMinus_2;

	public static TextureRegion player_score;

	public static TextureRegion tab_back, tab_eyes, tab_nose, tab_head, button_next, button_previous, itemsEquipText, tutorials;

	public static TextureRegion pup_missed, pup_points, pause_menu, pause_resume, dogecoins_text;

	public static TextureRegion shop_after, shop_buyitem, shop_currentcoins, shop_no, shop_price, shop_yes, chowcoin_2, shop_not_enough;

	public static TextureRegion arrow_left, arrow_right, tutorial_1, tutorial_2, tutorial_3, tutorial_4, tutorial_5, tutorial_resume,
			tutorial_mainmenu, tutorial_levelselect, tutorial_select;

	public static Texture bg_big_day,bg_big_night;

	public static TextureRegion night_light_1, night_light_2, night_light_3;

	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("core/core.pack"));

		loadAccs();
		loadEffects();
		loadEnemies();
		loadObjects();
		loadPlayer();
		loadPuppies();
		loadLevelSelection();
		loadMain();
		loadShop();
		loadText();
		loadButtons();
		initSound();

		// bgs
		bg_big_day = new Texture(Gdx.files.internal("core/background_big.png"));
		bg_big_night = new Texture(Gdx.files.internal("core/background_big2.png"));

	}

	private static void initSound() {
		OptionsScreen.isSoundOn = DogeDashCore.db.getSettings(1).getSoundSettings();
		OptionsScreen.isMuiscOn = DogeDashCore.db.getSettings(1).getMusicSettings();
		OptionsScreen.isVibrationOn = DogeDashCore.db.getSettings(1).getVibrationSettings();
	}

	private static void loadButtons() {
		// main menu
		play = atlas.findRegion("menu/buttons/play");
		options = atlas.findRegion("menu/buttons/options");
		highscores = atlas.findRegion("menu/buttons/highscores");
		customization = atlas.findRegion("menu/buttons/customization");

		back = atlas.findRegion("menu/buttons/back");
		button_next = atlas.findRegion("menu/buttons/button_next");
		button_previous = atlas.findRegion("menu/buttons/button_previous");
		credit = atlas.findRegion("menu/buttons/credit");

		mainmenu = atlas.findRegion("menu/buttons/mainmenu");

		arrow_left = atlas.findRegion("menu/buttons/arrow_left");
		arrow_right = atlas.findRegion("menu/buttons/arrow_right");

		// shop
		shop_no = atlas.findRegion("menu/buttons/shop_no");
		shop_yes = atlas.findRegion("menu/buttons/shop_yes");

		// pause
		pause_menu = atlas.findRegion("menu/buttons/pause_menu");
		pause_resume = atlas.findRegion("menu/buttons/pause_resume");
		tutorial_resume = atlas.findRegion("menu/buttons/tutorial_resume");
		tutorial_levelselect = atlas.findRegion("menu/buttons/tutorial_levelselect");
		tutorial_mainmenu = atlas.findRegion("menu/buttons/tutorial_mainmenu");

		// options
		soundon = atlas.findRegion("menu/buttons/soundon");
		soundoff = atlas.findRegion("menu/buttons/soundoff");
		musicon = atlas.findRegion("menu/buttons/musicon");
		musicoff = atlas.findRegion("menu/buttons/musicoff");
		vibrationon = atlas.findRegion("menu/buttons/vibrationon");
		vibrationoff = atlas.findRegion("menu/buttons/vibrationoff");

	}

	private static void loadLevelSelection() {
		lvl1 = atlas.findRegion("menu/level_selection/lvl1-screenshot");
		lvl2 = atlas.findRegion("menu/level_selection/lvl2-screenshot");
		tutorial_select = atlas.findRegion("menu/level_selection/tutorial-screenshot");

	}

	private static void loadMain() {
		chowcoin = atlas.findRegion("menu/main/chowcoin");
		splash = atlas.findRegion("menu/main/splash2");

		// main menu bg
		menu = atlas.findRegion("menu/main/menu_bg");
		menu_blackpup = atlas.findRegion("menu/main/menu_blackpup");
		menu_blackpup2 = atlas.findRegion("menu/main/menu_blackpup2");
		menu_creampup_body = atlas.findRegion("menu/main/menu_creampup_body");
		menu_creampup_paw = atlas.findRegion("menu/main/menu_creampup_paw1");
		menu_creampup_paw2 = atlas.findRegion("menu/main/menu_creampup_paw2");
		menu_bluepup = atlas.findRegion("menu/main/menu_bluepup");
		menu_mom_nose_paw = atlas.findRegion("menu/main/menu_mom_nose_paw");
		menu_mombody = atlas.findRegion("menu/main/menu_mombody");

	}

	private static void loadShop() {
		itemLock = atlas.findRegion("menu/shop/itemLock");

		shop_angel = atlas.findRegion("menu/shop/shop_angel");
		shop_clownnose = atlas.findRegion("menu/shop/shop_clownnose");
		shop_devilwings = atlas.findRegion("menu/shop/shop_devilwings");
		shop_halo = atlas.findRegion("menu/shop/shop_halo");
		shop_hipster = atlas.findRegion("menu/shop/shop_hipster");
		shop_horns = atlas.findRegion("menu/shop/shop_horns");
		shop_monocle = atlas.findRegion("menu/shop/shop_monocle");
		shop_moustache = atlas.findRegion("menu/shop/shop_moustache");
		shop_pumpkin = atlas.findRegion("menu/shop/shop_pumpkin");
		shop_santahat = atlas.findRegion("menu/shop/shop_santahat");
		shop_shades = atlas.findRegion("menu/shop/shop_shades");
		shop_tophat = atlas.findRegion("menu/shop/shop_tophat");
		shop_unibrow = atlas.findRegion("menu/shop/shop_unibrow");

		tab_back = atlas.findRegion("menu/shop/tab_back_open");
		tab_eyes = atlas.findRegion("menu/shop/tab_eyes_open");
		tab_head = atlas.findRegion("menu/shop/tab_head_open");
		tab_nose = atlas.findRegion("menu/shop/tab_nose_open");

		shopItemBox = atlas.findRegion("menu/shop/shopItemBox");
		ninePatchBox = atlas.findRegion("menu/shop/shopItemBox.9");
		currentBox = atlas.findRegion("menu/shop/currentBox");

	}

	private static void loadText() {
		// splash

		// world selection
		difficulty = atlas.findRegion("menu/txt/difficulty");
		hard = atlas.findRegion("menu/txt/hard");
		easy = atlas.findRegion("menu/txt/easy");
		normal = atlas.findRegion("menu/txt/normal");

		hardSmall = atlas.findRegion("menu/txt/hard2");
		easySmall = atlas.findRegion("menu/txt/easy2");
		normalSmall = atlas.findRegion("menu/txt/normal2");

		// gameover stuff
		player_score = atlas.findRegion("menu/txt/player_score");
		pup_missed = atlas.findRegion("menu/txt/pup_missed");
		puppiescaught = atlas.findRegion("menu/txt/puppiescaught");
		pup_points = atlas.findRegion("menu/txt/pup_points");
		stylepoints = atlas.findRegion("menu/txt/stylepoints");
		time = atlas.findRegion("menu/txt/time");
		totalscore = atlas.findRegion("menu/txt/totalsocre");
		tutorial_1 = atlas.findRegion("menu/txt/tutorial_1");
		tutorial_2 = atlas.findRegion("menu/txt/tutorial_2");
		tutorial_3 = atlas.findRegion("menu/txt/tutorial_3");
		tutorial_4 = atlas.findRegion("menu/txt/tutorial_4");
		tutorial_5 = atlas.findRegion("menu/txt/tutorial_5");

		// shop
		shop_after = atlas.findRegion("menu/txt/shop_after");
		shop_not_enough = atlas.findRegion("menu/txt/shop_not_enough");
		shop_price = atlas.findRegion("menu/txt/shop_price");
		// titles/title txt
		puppydash = atlas.findRegion("menu/txt/puppydash");

		options_title = atlas.findRegion("menu/txt/options_title");
		options_txt = atlas.findRegion("menu/txt/options_text");

		lvlselect = atlas.findRegion("menu/txt/lvlselect");
		lvlselect_txt = atlas.findRegion("menu/txt/lvlselect_text");

		custom_title = atlas.findRegion("menu/txt/custom_title");
		itemsEquipText = atlas.findRegion("menu/txt/itemsEquipText");

	}

	private static void loadAccs() {
		// accs
		acc_angel = atlas.findRegion("game/accessories/acc_angel");
		acc_angelj = atlas.findRegion("game/accessories/acc_angelj");

		acc_clownnose = atlas.findRegion("game/accessories/acc_clownnose");
		acc_clownnosej = atlas.findRegion("game/accessories/acc_clownnosej");

		acc_devilwings = atlas.findRegion("game/accessories/acc_devilwings");
		acc_devilwingsj = atlas.findRegion("game/accessories/acc_devilwings");

		acc_halo = atlas.findRegion("game/accessories/acc_halo");
		acc_haloj = atlas.findRegion("game/accessories/acc_haloj");

		acc_hipster = atlas.findRegion("game/accessories/acc_hipster");
		acc_hipsterj = atlas.findRegion("game/accessories/acc_hipsterj");

		acc_horns = atlas.findRegion("game/accessories/acc_horns");
		acc_hornsj = atlas.findRegion("game/accessories/acc_hornsj");

		acc_monocle = atlas.findRegion("game/accessories/acc_monocle");
		acc_monoclej = atlas.findRegion("game/accessories/acc_monoclej");

		acc_moustache = atlas.findRegion("game/accessories/acc_moustache");
		acc_moustachej = atlas.findRegion("game/accessories/acc_moustachej");

		acc_pumpkin = atlas.findRegion("game/accessories/acc_pumpkin");
		acc_pumpkinj = atlas.findRegion("game/accessories/acc_pumpkinj");

		acc_santahat = atlas.findRegion("game/accessories/acc_santahat");
		acc_santahatj = atlas.findRegion("game/accessories/acc_santahatj");

		acc_shades = atlas.findRegion("game/accessories/acc_shades");
		acc_shadesj = atlas.findRegion("game/accessories/acc_shadesj");

		acc_tophat = atlas.findRegion("game/accessories/acc_tophat");
		acc_tophatj = atlas.findRegion("game/accessories/acc_tophatj");

		acc_unibrow = atlas.findRegion("game/accessories/acc_unibrow");
		acc_unibrowj = atlas.findRegion("game/accessories/acc_unibrowj");

	}

	private static void loadEffects() {
		// player effects
		pow = atlas.findRegion("game/effects/pow");
		style = atlas.findRegion("game/effects/style");
		shield = atlas.findRegion("game/effects/shield");

		energy1 = atlas.findRegion("game/effects/energy1");
		energy2 = atlas.findRegion("game/effects/energy2");
		energy3 = atlas.findRegion("game/effects/energy3");

	}

	private static void loadEnemies() {
		// enemies
		enemyBee = atlas.findRegion("game/enemies/enemyBee");
		enemyBee2 = atlas.findRegion("game/enemies/enemyBee2");
		enemyBee3 = atlas.findRegion("game/enemies/enemyBee3");

		enemyMoth = atlas.findRegion("game/enemies/enemyMoth1");
		enemyMoth2 = atlas.findRegion("game/enemies/enemyMoth2");
		enemyMoth3 = atlas.findRegion("game/enemies/enemyMoth3");

	}

	private static void loadObjects() {
		gameBush = atlas.findRegion("game/objects/gameBush");

		gameMud = atlas.findRegion("game/objects/gameMud");
		gameMud2 = atlas.findRegion("game/objects/gameMud2");

		gamePuddle = atlas.findRegion("game/objects/gamePuddle");
		gamePuddle2 = atlas.findRegion("game/objects/gamePuddle2");

		hurdleLog = atlas.findRegion("game/objects/hurdleLog");

		chowcoin_2 = atlas.findRegion("game/objects/chowcoin_2");

		flower1 = atlas.findRegion("game/objects/flower1");
		flower2 = atlas.findRegion("game/objects/flower2");
		flower3 = atlas.findRegion("game/objects/flower3");
		flower4 = atlas.findRegion("game/objects/flower4");
		flower5 = atlas.findRegion("game/objects/flower5");

	}

	private static void loadPlayer() {
		// Player
		character = atlas.findRegion("game/player/character");
		character2 = atlas.findRegion("game/player/character2");
		characterHit = atlas.findRegion("game/player/characterHit");
		characterHit2 = atlas.findRegion("game/player/characterHit2");
		characterDie = atlas.findRegion("game/player/characterDie");
		characterJump = atlas.findRegion("game/player/characterJump");

		// super doge
		dogsuper1 = atlas.findRegion("game/player/dogsuper1");
		dogsuper2 = atlas.findRegion("game/player/dogsuper2");

	}

	private static void loadPuppies() {
		// Puppies
		redPup = atlas.findRegion("game/puppies/redPup");
		redPup2 = atlas.findRegion("game/puppies/redPup2");

		blackPup = atlas.findRegion("game/puppies/blackPup");
		blackPup2 = atlas.findRegion("game/puppies/blackPup2");

		creamPup = atlas.findRegion("game/puppies/creamPup");
		creamPup2 = atlas.findRegion("game/puppies/creamPup2");

		bluePup = atlas.findRegion("game/puppies/bluePup");
		bluePup2 = atlas.findRegion("game/puppies/bluePup2");

	}

	public static void dispose() {
		atlas.dispose();
	}

}
