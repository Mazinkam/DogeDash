package com.alicode.game.dogedash.models.enemies;

import com.alicode.game.dogedash.models.player.Player;
import com.badlogic.gdx.math.Vector2;

public class EnemyBee extends EnemyObject {

	float ROTATION_SPEED = 500;
	
	public EnemyBee(float SPEED, float rotation, float width, float height, Vector2 position) {
		super(SPEED, rotation, width, height, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta, Player player) {
//		position.lerp(player.getPosition(), delta);
//
//		rotation += delta * ROTATION_SPEED;
//
//		if (rotation > 360)
//			rotation -= 360;
//

		position.x-=SPEED;
		
		if(position.x < -20)
		{
			position.x =  900;	
		}
		super.update(player);
			

	}

}
