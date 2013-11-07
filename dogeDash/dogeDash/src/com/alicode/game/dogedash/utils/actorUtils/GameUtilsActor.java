package com.alicode.game.dogedash.utils.actorUtils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class GameUtilsActor {
	/**
	 * Get the rectangle of an actor from its current position and size
	 * */
	public static Rectangle getRectangleOfActor(Actor actor) {
		return new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
	}

	/**
	 * Set touchable for multiple actors at once
	 * */
	public static void setTouchable(Touchable touchable, Actor... actors) {
		for (Actor a : actors) {
			a.setTouchable(touchable);
		}
	}

	/**
	 * Set visible for multiple actors at once
	 * */
	public static void setVisible(boolean isVisible, Actor... actors) {
		for (Actor a : actors) {
			a.setVisible(isVisible);
		}
	}

	/**
	 * Set scale of multiple actors at once
	 * */
	public static void setScale(float sx, float sy, Actor... actors) {
		for (Actor a : actors) {
			a.setScale(sx, sy);
		}
	}

	/**
	 * Set size for multiple actors at once
	 * */
	public static void setSize(float w, float h, Actor... actors) {
		for (Actor a : actors) {
			a.setSize(w, h);
		}
	}
}
