package com.alicode.game.dogedash.models.misc;

import com.alicode.game.dogedash.models.MovableGameObject;
import com.alicode.game.dogedash.models.player.Player;
import com.badlogic.gdx.math.Vector2;

public abstract class MiscObject extends MovableGameObject{

	public MiscObject(float SPEED, float rotation, float width, float height, Vector2 position) {
		super(SPEED, rotation, width, height, position);
		// TODO Auto-generated constructor stub
	}

	public abstract void update(float delta, Player player);
}
