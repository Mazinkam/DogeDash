package com.alicode.game.dogedash;

public class GamePoints {
	public static int currentScore;

	public static int bonusPointStatic;

	public static int finalScore;

	public static int puppyCaughtNum;
	public static int puppyMissedNum;

	public static int finalScore() {
		finalScore = (currentScore + bonusPointStatic + puppyPoints());
		return finalScore;
	}

	public static int caughtPuppies() {
		return puppyCaughtNum * 500;

	}

	public static int missedPuppies() {
		return puppyMissedNum * 300;

	}

	public static int puppyPoints() {
		return caughtPuppies() - missedPuppies();
	}

	public static void clearTheNumbers() {
		currentScore = 0;
		finalScore = 0;
		bonusPointStatic = 0;
		puppyCaughtNum = 0;
		puppyMissedNum = 0;
	}
}