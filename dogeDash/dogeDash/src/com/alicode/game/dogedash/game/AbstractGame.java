package com.alicode.game.dogedash.game;

import com.alicode.game.dogedash.interfaces.GameInterface;
import com.alicode.game.dogedash.managers.GameSettingsManager;
import com.alicode.game.dogedash.managers.MusicManager;
import com.alicode.game.dogedash.screens.AbstractScreen;
import com.alicode.game.dogedash.settings.GameMasterLog;
import com.alicode.game.dogedash.utils.GameUtilsDisposer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public abstract class AbstractGame extends Game implements GameInterface {

	protected final String LOGTAG = "AbstractGame";
	public static boolean tumblr_active = true;

	private AbstarctAssets assets;
	private MusicManager musicManager;

	private boolean screenIsInitialized;
	private AbstractScreen prevScreenToDispose;
	private boolean isDisposable = false;

	@Override
	public void create() {
		screenIsInitialized = false;
		musicManager = new MusicManager();

		loadGameSettings();
		loadGameAssets();
		loadGameLoadingScreen();
		GameSettingsManager.setSettings();

	}

	@Override
	public void render() {
		super.render();
		if (isDisposable && prevScreenToDispose != null) {
			try {
				GameUtilsDisposer.disposeScreen(prevScreenToDispose); // need to do
				prevScreenToDispose = null;
				isDisposable = false;
			} catch (Exception e) {
				GameMasterLog.startBlogging(tumblr_active, true, LOGTAG, "Failed to dispose previous screen after transition. FIX THIS!!!");
				Gdx.app.log("MtxImportant", e.getMessage());
				prevScreenToDispose = null;
				isDisposable = false;
			}
		}
	}

	@Override
	public void resume() {
		super.resume();
		GameSettingsManager.setSettings();
	}

	public AbstarctAssets getAssets() {
		return assets;
	}

	public void setAssets(AbstarctAssets assets) {
		this.assets = assets;
	}

	public MusicManager getmusicManager() {
		return musicManager;
	}

	public void setmusicManager(MusicManager musicManager) {
		this.musicManager = musicManager;
	}

	public boolean isScreenIsInitialized() {
		return screenIsInitialized;
	}

	public void setScreenIsInitialized(boolean screenIsInitialized) {
		this.screenIsInitialized = screenIsInitialized;
	}

}
