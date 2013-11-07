package com.alicode.game.dogedash.utils;

import com.alicode.game.dogedash.DogeDashCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InputHandler implements InputProcessor {

	World world;
	Vector3 touch = new Vector3();
	Vector2 vec2Touch = new Vector2();

	public InputHandler(World world) {
		this.world = world;
	}

	@Override
	public boolean keyDown(int keycode) {
		Gdx.app.log(DogeDashCore.LOG, "keycode: " + keycode);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		Gdx.app.log(DogeDashCore.LOG, "keycode: " + keycode);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		Gdx.app.log(DogeDashCore.LOG, "character: " + character);
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log(DogeDashCore.LOG, "touchDown: screenX: " + screenX + " screenY: " + screenY + " pointer: " + pointer + " button: " + button);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log(DogeDashCore.LOG, "touchUp: screenX: " + screenX + " screenY: " + screenY + " pointer: " + pointer + " button: " + button);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Gdx.app.log(DogeDashCore.LOG, "touchDragged: screenX: " + screenX + " screenY: " + screenY + " pointer: " + pointer);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Gdx.app.log(DogeDashCore.LOG, "mouseMoved: screenX: " + screenX + " screenY: " + screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		Gdx.app.log(DogeDashCore.LOG, "scrolled: amount: " + amount);
		return false;
	}

}
