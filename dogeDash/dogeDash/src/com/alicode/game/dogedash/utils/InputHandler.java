package com.alicode.game.dogedash.utils;

import com.alicode.game.dogedash.models.player.Player;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InputHandler implements InputProcessor {

	World world;
	Player player;
	Vector3 touch = new Vector3();
	Vector2 vec2Touch = new Vector2();

	public InputHandler(World world) {
		this.world = world;
	}

	@Override
	public boolean keyDown(int keycode) {
		player = world.getPlayer();
		switch (keycode) {
		case Keys.W:
			player.getVelocity().y = 50;
			player.rotate += 0.2f;
			break;
		case Keys.S:
			player.getVelocity().y = -50;
			player.rotate += -0.1f;
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		player = world.getPlayer();
		switch (keycode) {
		case Keys.W:
			if (player.getVelocity().y > 9)
				player.getVelocity().y = 0;
			player.rotate = 0;
			break;
		case Keys.S:
			if (player.getVelocity().y < -9)
				player.getVelocity().y = 0;
			player.rotate= 0;
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		player.rotate = 0;
		player.getVelocity().y = 0;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		touch.set(screenX, screenY, 0);

		world.getRenderer().getCamera().unproject(touch);

		vec2Touch.set(touch.x, touch.y);

		player = world.getPlayer();
		if (player.getPosition().y+40 > touch.y +50) {
			player.rotate = -0.1f;
			player.getVelocity().y  = -50;
//			player.getPosition().y -= 2;
		}
		else if(player.getPosition().y+40 < touch.y -50){
			player.rotate = 0.2f;
			player.getVelocity().y  =50;
//			player.getPosition().y += 2;
		}
		else{
			player.rotate = 0f;
			player.getVelocity().y  =0;
		}
		return true;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
