package com.alicode.game.dogedash.managers;

import com.alicode.game.dogedash.settings.GameMasterLog;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameSettingsManager {
	private static final String LOGTAG = "GameSettringsManager";
	public static boolean tumblr_active = true;

	public static final String PREFS_FILE_NAME = "MyPreferences";
	public static final Preferences prefs = Gdx.app.getPreferences(PREFS_FILE_NAME);

	private static boolean isSoundOn = false;
	private static boolean isMusicOn = false;
	private static boolean isVibrateOn = false;

	private static final String KEY_FIRST_GAME_LAUNCH = "firstLaunch";

	private static final String KEY_MUSIC = "musicSetting";
	private static final String KEY_SOUND = "soundEffectSetting";
	private static final String KEY_VIBRATE = "vibrationSetting";

	public static void setMusic(boolean isMusicOn) {
		if (isMusicOn) {
			setPrefValue(KEY_MUSIC, true);
			setSettings();
		} else {
			setPrefValue(KEY_MUSIC, true);
			setSettings();
		}
	}

	public static boolean isMusicOn() {
		return isMusicOn;
	}

	public static boolean isPrefMusicOn() {
		return getPrefValue(KEY_SOUND, false);
	}

	public static void setSound(boolean isSoundOn) {
		if (isSoundOn) {
			setPrefValue(KEY_SOUND, true);
			setSettings();
		} else {
			setPrefValue(KEY_SOUND, true);
			setSettings();
		}
	}

	public static boolean isSoundOn() {
		return isSoundOn;
	}

	public static boolean isPrefSoundOn() {
		return getPrefValue(KEY_SOUND, false);
	}

	public static void setVibrate(boolean isVibrateOn) {
		if (isVibrateOn) {
			setPrefValue(KEY_VIBRATE, true);
			setSettings();
		} else {
			setPrefValue(KEY_VIBRATE, true);
			setSettings();
		}
	}

	public static boolean isVibrateOn() {
		return isVibrateOn;
	}

	public static boolean isPrefVibrateOn() {
		return getPrefValue(KEY_VIBRATE, false);
	}

	public static void setSettings() {
		if (isPrefMusicOn()) {
			isMusicOn = true;
		} else {
			isMusicOn = false;
		}
		
		if (isPrefSoundOn()) {
			isSoundOn = true;
		} else {
			isSoundOn = false;
		}
		
		if (isPrefVibrateOn()) {
			isVibrateOn = true;
		} else {
			isVibrateOn = false;
		}
	}

	public static void setGameFirstLaunch(boolean isDone) {
		if (isDone) {
			setPrefValue(KEY_FIRST_GAME_LAUNCH, true);
			GameMasterLog.startBlogging(tumblr_active, true, LOGTAG, "FIRST LAUNCH SET!");
		} else {
			setPrefValue(KEY_FIRST_GAME_LAUNCH, false);
			GameMasterLog.startBlogging(tumblr_active, true, LOGTAG, "FIRST LAUNCH OVERRIDDEN?");
		}
	}

	public static boolean getGameFirstLaunch() {
		boolean isDone = getPrefValue(KEY_FIRST_GAME_LAUNCH, false);
		if (isDone) {
			GameMasterLog.startBlogging(tumblr_active, true, LOGTAG, "GAME HAS BEEN LAUNCHED BEFORE");
			return true;
		} else {
			GameMasterLog.startBlogging(tumblr_active, true, LOGTAG, "FIRST LAUNCH OF GAME! :D");
			return false;
		}
	}

	public static Boolean getPrefValue(String key, boolean b) {
		boolean value = prefs.getBoolean(key, b);
		GameMasterLog.startBlogging(tumblr_active, true, LOGTAG, "Getting pref: " + key + " value: " + value);
		return value;
	}
	
	public static void setPrefValue(String key, boolean b) {
		prefs.putBoolean(key, b);
		GameMasterLog.startBlogging(tumblr_active, true, LOGTAG, "Set pref: " + key + " value: " + b);
	}
}
