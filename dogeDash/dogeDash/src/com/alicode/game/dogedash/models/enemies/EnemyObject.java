package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.models.MovableGameObject;
import com.alicode.game.dogedash.models.player.Player;
import com.badlogic.gdx.math.Vector2;

public abstract class EnemyObject extends MovableGameObject{

	public EnemyObject(float SPEED, float rotation, float width, float height, Vector2 position) {
		super(SPEED, rotation, width, height, position);
	}

	public abstract void update(float delta, Player player);
	
}
