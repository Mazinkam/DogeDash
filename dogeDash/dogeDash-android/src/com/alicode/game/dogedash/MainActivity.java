package com.alicode.game.dogedash;

import android.os.Bundle;

import com.alicode.game.dogedash.sql.GameDatabase;
import com.alicode.game.dogedash.sql.GameDatabaseInterface;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        GameDatabaseInterface db = new GameDatabase();
        cfg.useGL20 = true;
        
        initialize(new DogeDashCore(db), cfg);
    }
}