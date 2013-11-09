package com.alicode.game.dogedash;

import com.alicode.game.dogedash.Screens.SplashScreen;
import com.badlogic.gdx.Game;

public class DogeDashCore extends Game {
	
	public static final String VERSION ="0.0.0.01 Pre-Alpha";
	public static final String LOG ="Doge Dash";
	public static final boolean DEV_MODE = false;
	
	@Override
	public void create() {
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
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