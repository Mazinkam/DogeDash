package com.alicode.game.dogedash.models.misc;

import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.models.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Background extends MiscObject {

	public Background(float SPEED, float rotation, float width, float height, Vector2 position) {
		super(SPEED, rotation, width, height, position);
	}

	@Override
	public void update(float delta, Player player) {
		position.x -= 10;
		if (position.x <= -2397) {
			position.x += 4794;
			Gdx.app.log(DogeDashCore.LOG, "position.x: " + position.x);
		}
		super.update(player);

	}

}
