package com.alicode.game.dogedash;

public class GamePoints {
	public static int currentScore;

	public static int bonusPointstatic;

	public static int finalScore;

	public static int puppyCaughtNum;
	public static int puppyMissedNum;

	public static int finalScore() {
		finalScore = (currentScore + bonusPointstatic + puppyPoints());
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
}