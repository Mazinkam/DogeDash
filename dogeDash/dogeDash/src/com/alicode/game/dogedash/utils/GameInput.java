package com.alicode.game.dogedash.utils;

import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.worlds.WorldTerminal;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class GameInput implements InputProcessor {
	private WorldTerminal worldTerminal;
	private Vector3 touch = new Vector3();
	private Vector2 vec2Touch = new Vector2();
	private MotherDoge motherDoge;

	public GameInput(MotherDoge chosenWorldMother, WorldTerminal worldTerminal) {
		this.motherDoge = chosenWorldMother;
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
		motherDoge.addAction(Actions.rotateTo(0f, 0.5f));
		motherDoge.startJump();
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		touch.set(screenX, screenY, 0);

		worldTerminal.getStage().getCamera().unproject(touch);

		vec2Touch.set(touch.x, touch.y);

		if (motherDoge.getY() > touch.y + 40) {
			if (!Statics.playerHitByLog)
				motherDoge.addAction(Actions.sequence(Actions.rotateTo(-10, 0.5f), Actions.delay(0.5f), Actions.rotateTo(0f, 1.0f)));
			motherDoge.normalDogeMovement(110, touch.y);
		} else if (motherDoge.getY() < touch.y - 40) {
			if (!Statics.playerHitByLog)
				motherDoge.addAction(Actions.sequence(Actions.rotateTo(10, 0.5f), Actions.delay(0.5f), Actions.rotateTo(0f, 1.0f)));
			motherDoge.normalDogeMovement(110, touch.y);
		} else {
			if (!Statics.playerHitByLog)
				motherDoge.addAction(Actions.rotateTo(0f, 0.5f));
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
