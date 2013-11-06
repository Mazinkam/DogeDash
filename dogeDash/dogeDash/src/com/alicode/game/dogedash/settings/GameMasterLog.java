package com.alicode.game.dogedash.settings;

import com.alicode.game.dogedash.game.AbstractGame;
import com.alicode.game.dogedash.screens.AbstractScreen;
import com.alicode.game.dogedash.utils.GameUtilsDisposer;
import com.badlogic.gdx.Gdx;

public class GameMasterLog {
	private static boolean masterTumblr_active = true;

	public static void setTumblr(boolean masterTumblr_active) {

		GameMasterLog.masterTumblr_active = masterTumblr_active;
		GameMasterSettings.tumblr_active = true;

		AbstractGame.tumblr_active = true;
		AbstractScreen.tumblr_active = true;


		GameUtilsDisposer.tumblr_active = true;

	}

	public static void startBlogging(boolean activeObjectTumblr, boolean methodTumblrActive, String tag, String message) {

		if (masterTumblr_active && activeObjectTumblr && methodTumblrActive) {
			Gdx.app.log(tag, message);
		}
	}

}
