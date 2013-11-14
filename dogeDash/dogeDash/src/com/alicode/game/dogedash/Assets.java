package com.alicode.game.dogedash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Assets {
	public static TextureAtlas atlas;

	public static TextureRegion menu_blackpup, menu_blackpup2, menu_bluepup, menu_creampup_body, menu_creampup_paw, menu_creampup_paw2, menu_mom_nose_paw, menu_mombody;
	public static TextureRegion menu, splash, background, background2;

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

	public static TextureRegion enemyBee, enemyBee2, enemyBee3, night_enemyBee, night_enemyBee2, night_enemyBee3;

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

	public static TextureRegion custom_txt, custom_title, currentBox, chowcoin, itemLock, shopItemBox;

	public static TextureRegion num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, num0_2, num1_2, num2_2, num3_2, num4_2, num5_2, num6_2,
			num7_2, num8_2, num9_2, numMinus, numMinus_2;

	public static TextureRegion player_score;

	public static TextureRegion tab_back, tab_eyes, tab_nose, tab_head, button_next, button_previous, itemsEquipText, tutorials;

	public static TextureRegion pup_missed, pup_points, pause_menu, pause_resume, dogecoins_text;

	public static TextureRegion shop_after, shop_buyitem, shop_currentcoins, shop_no, shop_price, shop_yes, chowcoin_2, shop_not_enough;

	public static TextureRegion arrow_left, arrow_right, tutorial_1, tutorial_2, tutorial_3, tutorial_4, tutorial_5, tutorial_resume,
			tutorial_mainmenu, tutorial_levelselect;

	public static TextureRegion night_light_1, night_light_2, night_light_3;

	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("core/core.pack"));
		
		//splash
		splash =atlas.findRegion("menu/main/splash2");
		
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
		
		//main menu context
		puppydash = atlas.findRegion("menu/text/titles/puppydash");
		play = atlas.findRegion("menu/text/buttons/play");
		options = atlas.findRegion("menu/text/buttons/options");
		highscores = atlas.findRegion("menu/text/buttons/highscores");
		customization = atlas.findRegion("menu/text/buttons/customization");
	}

	public static void dispose() {
		atlas.dispose();
	}

}
