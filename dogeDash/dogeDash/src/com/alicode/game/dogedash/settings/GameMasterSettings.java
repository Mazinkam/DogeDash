package com.alicode.game.dogedash.settings;

import com.badlogic.gdx.Gdx;

public class GameMasterSettings {
	public final static String LOGTAG = "MasterSettrings";
	public static boolean tumblr_active = true;

	public static float SCREEN_W = .0f;
	public static float SCREEN_H = .0F;
	public static float TARGET_WORLD_WIDTH = .0f;
	public static float TARGET_WORLD_HEIGHT = .0f;

	private static float TW_portraitWidth = .0f;
	private static float TW_portraitHeight = .0f;
	private static float TW_landscapeWidth = .0f;
	private static float TW_landscapeHeight = .0f;

	public static float WORLD_WIDTH = .0f;
	public static float WORLD_HEIGHT = .0f;

	public static boolean isGameSettingsSet = false;

	public enum GameScreenOrientation {
		LANDSCAPE, PORTRAIT
	}

	public static void initGameApp(float screenW, float screenH, float worldW, float worldH) {
		reset();
		SCREEN_W = screenW;
		SCREEN_H = screenH;
		TARGET_WORLD_WIDTH = worldW;
		TARGET_WORLD_HEIGHT = worldH;

		TW_portraitWidth = worldW;
		TW_portraitHeight = worldH;
		TW_landscapeWidth = worldW;
		TW_landscapeHeight = worldH;

		isGameSettingsSet = true;

	}
	
	public static void initGameApp() {
		SCREEN_W = Gdx.graphics.getWidth();
		SCREEN_H = Gdx.graphics.getHeight();

		TARGET_WORLD_WIDTH = 800;
		TARGET_WORLD_HEIGHT = 480;

		TW_portraitWidth = 480;
		TW_portraitHeight = 800;

		TW_landscapeWidth = 800;
		TW_landscapeHeight = 480;

		WORLD_WIDTH = Gdx.graphics.getWidth();
		WORLD_HEIGHT = Gdx.graphics.getHeight();

		isGameSettingsSet = true;
		GameMasterLog.startBlogging(tumblr_active, true, LOGTAG, "GameMasterSettings Initialized.");

	}

	public static boolean IS_PORTRAIT;

	public static void initGameOrientation() {
		if (Gdx.graphics.getWidth() <= Gdx.graphics.getHeight()) {

			SCREEN_W = Gdx.graphics.getWidth();
			SCREEN_H = Gdx.graphics.getHeight();

			WORLD_WIDTH = Gdx.graphics.getWidth();
			WORLD_HEIGHT = Gdx.graphics.getHeight();

			TARGET_WORLD_WIDTH = TW_portraitWidth;
			TARGET_WORLD_HEIGHT = TW_portraitHeight;

			IS_PORTRAIT = true;
			GameMasterLog.startBlogging(tumblr_active, true, LOGTAG, "Changed to portrait mode.");
		} else {
			SCREEN_W = Gdx.graphics.getWidth();
			SCREEN_H = Gdx.graphics.getHeight();

			WORLD_WIDTH = Gdx.graphics.getWidth();
			WORLD_HEIGHT = Gdx.graphics.getHeight();

			TARGET_WORLD_WIDTH = TW_landscapeWidth;
			TARGET_WORLD_HEIGHT = TW_landscapeHeight;

			IS_PORTRAIT = true;
			GameMasterLog.startBlogging(tumblr_active, true, LOGTAG, "Changed to landscape mode.");
		}
	}

	private static void reset() {
		SCREEN_W = .0f;
		SCREEN_H = .0f;
		WORLD_WIDTH = .0f;
		WORLD_HEIGHT = .0f;
		TARGET_WORLD_WIDTH = .0f;
		TARGET_WORLD_HEIGHT = .0f;

		TW_portraitWidth = .0f;
		TW_portraitHeight = .0f;
		TW_landscapeWidth = .0f;
		TW_landscapeHeight = .0f;

		isGameSettingsSet = true;

	}

}
