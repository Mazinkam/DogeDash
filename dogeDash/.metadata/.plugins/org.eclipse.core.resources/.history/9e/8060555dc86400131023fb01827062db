package com.alicode.game.dogedash;

import com.alicode.game.dogedash.screens.HighscoresScreen;
import com.alicode.game.dogedash.screens.SplashScreen;
import com.alicode.game.dogedash.screens.WorldSelection;
import com.alicode.game.dogedash.sql.GameDatabase;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class DogeDashCore extends Game {
        
        public static final String VERSION ="0.0.0.01 Pre-Alpha";
        public static final String LOG ="Doge Dash";
        public final static int WIDTH = 800;
        public final static int HEIGHT = 480;
        public static GameDatabase db;
        public static final boolean DEV_MODE = true;
        
        @Override
        public void create() {
                db = new GameDatabase();
                Assets.load();
                Gdx.input.setCatchBackKey(true);
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