package com.alicode.game.dogedash.utils.actorUtils;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameUtilsActorOrigin {
	public enum Origin {
		CENTER, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, LEFT_CENTER, TOP_CENTER, BOTTOM_CENTER, RIGHT_CENTER
	}

	public static void setActorOrigin(Actor a, Origin o) {
		setOrigin(a, o);
	}

	public static void setActorsOrigin(Origin o, Actor... actors) {
		for (Actor a : actors) {
			setOrigin(a, o);
		}
	}

	private static void setOrigin(Actor actor, Origin origin) {
		switch (origin) {
		case CENTER:
			actor.setOrigin(actor.getWidth() / 2.0f, actor.getHeight() / 2.0f);
			break;
		case TOP_LEFT:
			actor.setOrigin(0.0f, actor.getHeight());
			break;
		case TOP_RIGHT:
			actor.setOrigin(actor.getWidth(), actor.getHeight());
			break;
		case BOTTOM_LEFT:
			actor.setOrigin(0.0f, 0.0f);
			break;
		case BOTTOM_RIGHT:
			actor.setOrigin(actor.getWidth(), 0.0f);
			break;
		case LEFT_CENTER:
			actor.setOrigin(0.0f, actor.getHeight() / 2.0f);
			break;
		case TOP_CENTER:
			actor.setOrigin(actor.getWidth() / 2.0f, actor.getHeight());
			break;
		case BOTTOM_CENTER:
			actor.setOrigin(actor.getWidth() / 2.0f, 0.0f);
			break;
		case RIGHT_CENTER:
			actor.setOrigin(actor.getWidth(), actor.getHeight() / 2.0f);
			break;
		default:
			actor.setOrigin(actor.getOriginX(), actor.getOriginY());
			break;
		}
	}
}
