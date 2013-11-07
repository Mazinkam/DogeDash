package com.alicode.game.dogedash.utils;

public class GameUtilsRandomNum {

	public static int getRandomInclusive(int min, int max) {
		return min + (int)(Math.random()*max); 
	}

}
