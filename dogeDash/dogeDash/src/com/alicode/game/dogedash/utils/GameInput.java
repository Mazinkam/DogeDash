package com.alicode.game.dogedash.utils;

import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.worlds.WorldTerminal;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GameInput implements InputProcessor {
	private WorldTerminal worldTerminal;
	private Vector2 touch = new Vector2();
	private Vector2 vec2Touch = new Vector2();
	private MotherDoge motherDoge;

	public GameInput(WorldTerminal worldTerminal) {
		this.worldTerminal = worldTerminal;
	}

	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		touch.set(screenX, screenY);
		motherDoge = worldTerminal.getMotherDoge();
		
		if (motherDoge.getY() + 40 > touch.y + 20) {
//			player.rotate = -0.1f;
//			player.getVelocity().y = -50;
//			// player.getPosition().y -= 2;
			worldTerminal.motherDoge.normalDogeMovement(110, touch.y);
		} else if (motherDoge.getY()+ 40 < touch.y - 20) {
//			player.rotate = 0.2f;
//			player.getVelocity().y = 50;
//			// player.getPosition().y += 2;
			worldTerminal.motherDoge.normalDogeMovement(110,  touch.y);
		} else {
//			player.rotate = 0f;
//			player.getVelocity().y = 0;
		}

		return false;
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
