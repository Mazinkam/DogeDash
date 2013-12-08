package com.alicode.game.dogedash;

import com.alicode.game.dogedash.sql.GameDatabaseDesktop;
import com.alicode.game.dogedash.sql.GameDatabaseInterface;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "dogeDash";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 480;
		GameDatabaseInterface db = new GameDatabaseDesktop();
		
		new LwjglApplication(new DogeDashCore(db), cfg);
	}
}
