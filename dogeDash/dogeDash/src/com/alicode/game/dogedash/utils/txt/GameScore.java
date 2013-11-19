package com.alicode.game.dogedash.utils.txt;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.Statics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameScore extends Actor {

	private BitmapFont font;

	public GameScore() {
		font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		batch.draw(Assets.player_score, 520, 440, 600, 460, Assets.player_score.getRegionWidth(), Assets.player_score.getRegionHeight(), 1, 1, 0);
		font.draw(batch, ""+Statics.gamePoints, 620, 465);
	}

}