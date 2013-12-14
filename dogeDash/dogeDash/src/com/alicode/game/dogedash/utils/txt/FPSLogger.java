package com.alicode.game.dogedash.utils.txt;

import com.alicode.game.dogedash.Statics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FPSLogger extends Actor {

	private BitmapFont font;
	private String JumpText;

	public FPSLogger() {
		font = new BitmapFont(Gdx.files.internal("skin/everything_else.fnt"), false);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (Statics.playerJumpCooldown < 0)
			JumpText = "   dogeJump ready!";
		if (Statics.playerJumpCooldown > 0)
			JumpText = "   dogeJump not ready!";

		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond() + JumpText, 40, 460);
	}

}