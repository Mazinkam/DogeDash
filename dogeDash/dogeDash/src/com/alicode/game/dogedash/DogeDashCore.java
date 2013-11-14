package com.alicode.game.dogedash;

import com.alicode.game.dogedash.screens.OptionsScreen;
import com.alicode.game.dogedash.screens.SplashScreen;
import com.badlogic.gdx.Game;

public class DogeDashCore extends Game {
	
	public static final String VERSION ="0.0.0.01 Pre-Alpha";
	public static final String LOG ="Doge Dash";
	public static final boolean DEV_MODE = true;
	
	@Override
	public void create() {
		Assets.load();
		setScreen(new OptionsScreen(this));
	}

	@Override
	public void dispose() {
		Assets.dispose();
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}