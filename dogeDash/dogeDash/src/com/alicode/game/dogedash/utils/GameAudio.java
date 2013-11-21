package com.alicode.game.dogedash.utils;

import com.alicode.game.dogedash.screens.OptionsScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameAudio {

	private GameAudio() {
	}

	public static Music menuBG = Gdx.audio.newMusic(Gdx.files.internal("sound/background/menu_theme.ogg"));
	public static Sound dogeBark = Gdx.audio.newSound(Gdx.files.internal("sound/effects/dogeSound.ogg"));

	//
	public static void playMenuBG(boolean looping) {
		menuBG.setLooping(looping);
		menuBG.play();
	}

	public static void stopBG() {
		menuBG.stop();
	}

	public static void dogeBark() {
		if (OptionsScreen.isSoundOn == 1)
			dogeBark.play();
	}

	public static void dispose() {
		dogeBark.dispose();
		menuBG.dispose();
	}

}
