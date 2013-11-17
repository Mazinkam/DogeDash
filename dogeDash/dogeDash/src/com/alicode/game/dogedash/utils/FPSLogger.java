package com.alicode.game.dogedash.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FPSLogger extends Actor {

    private BitmapFont font;

    public FPSLogger(){
        font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
    }


    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
         font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 40, 460);
         //Also remember that an actor uses local coordinates for drawing within
         //itself!
    }


}