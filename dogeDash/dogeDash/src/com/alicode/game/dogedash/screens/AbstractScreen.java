package com.alicode.game.dogedash.screens;

import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.game.AbstractGame;
import com.alicode.game.dogedash.settings.GameMasterSettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class AbstractScreen implements Screen {
	private static final String LOGTAG = "GameScreenLog";
	public static final int VIEWPORT_WIDTH = 800, VIEWPORT_HEIGHT = 480;
	public static boolean tumblr_active = true;

	private DogeDashCore game;
	private Stage stage;
	
	private String screenName = "NoTitleScreen";
	
	private long startTime = System.nanoTime();
	private long timeInSeconds = 0L;
	
	private float animTime = .0f;
	
	private boolean backButtonActive = false;
	
	private float r = .0f;
	private float g = .0f;
	private float b = .0f;
	private float alpha = .0f;
	

	public AbstractScreen(AbstractGame game, String screenName) {
		
	}

}
