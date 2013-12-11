package com.alicode.game.dogedash.utils;

import com.alicode.game.dogedash.screens.OptionsScreen;
import com.badlogic.gdx.Gdx;

public class GameVibrate {

	public static void vibrate(int ms) {
		if (OptionsScreen.isVibrationOn == 1)
			Gdx.input.vibrate(ms);
	}

}
