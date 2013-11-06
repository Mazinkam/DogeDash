package com.alicode.game.dogedash.managers;

import com.badlogic.gdx.Gdx;

public class VibrationManager {

	public static final int BUZZ = 50;
	public static final int DOT = 100;
	public static final int DASH = 500;
	public static final int SHORT_GAP = 200;
	public static final int MEDIUM_GAP = 500;
	public static final int LONG_GAP = 1000;

	public void normalVibrate(int ms) {
		if (GameSettingsManager.isVibrateOn()) {
			Gdx.input.vibrate(ms);
		}
	}
	public void patternVibrate(long[] pattern, int repeat)
	{
		if(GameSettingsManager.isVibrateOn())
		{
			Gdx.input.vibrate(pattern,repeat);
		}
	}
	public void stopVibration(){
		Gdx.input.cancelVibrate();
	}

}
