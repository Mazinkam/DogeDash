package com.alicode.game.dogedash;

public class Statics {

	public static enum GameState {
		Ready, Running, Paused, GameOver
	}

	public static GameState state = GameState.Ready;

	// Game vars
	public static int gameLevel;
	public static int gameLevelDifficulty;

	public static boolean gameSound;
	public static boolean gameMusic;
	public static boolean gameVibration;

	// game play vars
	public static float backgroundSpeed = 4;
	public static float enemySpeed = 6;
	public static float puppySpeed = 6;

	public static boolean cleanseEnemies = false;

	// objvars

	public static boolean enemiesAlive = true;
	public static boolean puppiesAlive = true;
	public static boolean bushesAlive = true;
	public static boolean flowersAlive = true;

	// player vars
	public static boolean isSuperD = false;
	public static boolean playerJump = false;
	public static boolean playerHitByBush = false;
	public static boolean playerHitByBee = false;
	public static boolean playerHitByPuppy = false;
	public static boolean playerHitByMud = false;
	public static boolean playerHitByPuddle = false;
	public static boolean playerHitByLog = false;
	public static boolean playerHitAnimation = false;
	public static float playerX, playerY, playerZ;

	public static int beesOnPlayer = 0;

	// costume vars
	public static int eyeCostume;
	public static int headCostume;
	public static int noseCostume;
	public static int backCostume;

	public static void createLife() {
		enemiesAlive = true;
		puppiesAlive = true;
		bushesAlive = true;
		flowersAlive = true;
		playerX = 110;
		playerY = 220;
		playerZ = 0;
	}

	public static void cleanSlate() {
		state = GameState.Ready;

		cleanseEnemies = false;
		playerJump = false;

		playerHitByBush = false;
		playerHitByBee = false;
		playerHitByPuppy = false;

		enemiesAlive = false;
		puppiesAlive = false;
		bushesAlive = false;
		flowersAlive = false;

		GamePoints.clearTheNumbers();
		beesOnPlayer = 0;
	}

}
