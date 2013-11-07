package com.alicode.game.dogedash.utils.actorUtils;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameUtilsActorPos {
	public enum Position {
		SAME, CENTER, LEFT, TOP_LEFT, TOP_LEFT_CENTER, TOP_RIGHT, TOP_RIGHT_CENTER, BOTTOM_CENTER, BOTTOM_LEFT, BOTTOM_LEFT_CENTER, BOTTOM_RIGHT,
		BOTTOM_RIGHT_CENTER, RIGHT_CENTER, TOP_CENTER
	}

	/**
	 * Set position of an actor due to another actor's position, , since actors
	 * are complex objects, its variables pointing same reference with copies,
	 * so the position will be set in original object
	 * */

	public static void setActorPos(Actor actorToBePositioned, Actor actorStable, Position pos) {
		float actToPos_W = actorToBePositioned.getWidth();
		float actToPos_H = actorToBePositioned.getHeight();
		float actStable_X = actorStable.getX();
		float actStable_Y = actorStable.getY();
		float actStable_XW = actorStable.getWidth() + actStable_X;
		float actStable_YH = actorStable.getHeight() + actStable_Y;

		setPosition(actorToBePositioned, actToPos_W, actToPos_H, actStable_X, actStable_Y, actStable_XW, actStable_YH, pos);
	}

	public static void setActorPos(Actor actorToBePositioned, float x, float y, float width, float height, Position pos) {
		float actToPos_W = actorToBePositioned.getWidth();
		float actToPos_H = actorToBePositioned.getHeight();
		float actStable_X = x;
		float actStable_Y = y;
		float actStable_XW = width;
		float actStable_YH = height;

		setPosition(actorToBePositioned, actToPos_W, actToPos_H, actStable_X, actStable_Y, actStable_XW, actStable_YH, pos);
	}
	
	public static void setPosition(Actor actorToBePositioned, float actToPos_W, float actToPos_H, float actStable_X, float actStable_Y, float actStable_XW, float actStable_YH, Position position)
	{
		switch (position) {
		case CENTER:
			actorToBePositioned.setX((actStable_XW / 2.0f) - actToPos_W / 2.0f);
			actorToBePositioned.setY((actStable_YH / 2.0f) - actToPos_H / 2.0f);
			break;
		case SAME:
			actorToBePositioned.setPosition(actStable_X, actStable_Y);
			break;
		case LEFT:
			actorToBePositioned.setPosition(actStable_X, actStable_YH / 2.0f - actToPos_H / 2.0f);
			break;
		case TOP_LEFT:
			actorToBePositioned.setPosition(actStable_X, actStable_YH - actToPos_H);
			break;
		case TOP_LEFT_CENTER:
			actorToBePositioned.setPosition(actStable_X - actToPos_W / 2.0f, actStable_YH - actToPos_H
					/ 2.0f);
			break;
		case TOP_RIGHT:
			actorToBePositioned.setPosition(actStable_XW - actToPos_W, actStable_YH - actToPos_H);
			break;
		case TOP_RIGHT_CENTER:
			actorToBePositioned.setPosition(actStable_XW - actToPos_W / 2.0f, actStable_YH - actToPos_H
					/ 2.0f);
			break;
		case TOP_CENTER:
			actorToBePositioned.setPosition(actStable_XW / 2.0f - actToPos_W / 2.0f, actStable_YH
					- actToPos_H);
			break;
		case BOTTOM_LEFT:
			actorToBePositioned.setPosition(actStable_X, actStable_Y);
			break;
		case BOTTOM_LEFT_CENTER:
			actorToBePositioned.setPosition(actStable_X - actToPos_W / 2.0f, actStable_Y - actToPos_H
					/ 2.0f);
			break;
		case BOTTOM_RIGHT:
			actorToBePositioned.setPosition(actStable_XW - actToPos_W, actStable_Y);
			break;
		case BOTTOM_RIGHT_CENTER:
			actorToBePositioned.setPosition(actStable_XW - actToPos_W / 2.0f, actStable_Y - actToPos_H
					/ 2.0f);
			break;
		case BOTTOM_CENTER:
			actorToBePositioned.setPosition(actStable_XW / 2.0f - actToPos_W / 2.0f, actStable_Y);
			break;
		case RIGHT_CENTER:
			actorToBePositioned.setPosition(actStable_XW - actToPos_W, actStable_YH / 2.0f - actToPos_H
					/ 2.0f);
			break;
		default:
			actorToBePositioned.setPosition(actorToBePositioned.getX(),
					actorToBePositioned.getY());
			break;
		}
	}

}
