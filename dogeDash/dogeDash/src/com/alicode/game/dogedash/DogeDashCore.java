package com.alicode.game.dogedash;

import com.alicode.game.dogedash.screens.CustomizationScreen;
import com.alicode.game.dogedash.screens.WorldSelection;
import com.alicode.game.dogedash.sql.GameDatabaseInterface;
import com.badlogic.gdx.Game;

public class DogeDashCore extends Game {
	
	public static final String VERSION ="0.0.0.01 Pre-Alpha";
	public static final String LOG ="Doge Dash";
	public final static int WIDTH = 800;
	public final static int HEIGHT = 480;
	public static GameDatabaseInterface db;
	public static final boolean DEV_MODE = true;
	
	public DogeDashCore(GameDatabaseInterface db)
	{
		super();
		this.db = db;
	}
	
	public DogeDashCore() {
		//super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		//db = new GameDatabase();
		db.create();
		Assets.load();
		setScreen(new WorldSelection(this));
	}

	@Override
	public void dispose() {
		Assets.dispose();
		db.dispose();
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