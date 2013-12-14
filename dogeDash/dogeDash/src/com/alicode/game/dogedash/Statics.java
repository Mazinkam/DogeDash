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
	public static boolean objectsAlive = true;

	public static int puddleTimer = 400;
	public static int logTimer = 600;

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
	public static int superDogeTimer = 200;

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
		objectsAlive = true;
		playerX = 110;
		playerY = 220;
		playerZ = 0;
	}

	public static void cleanSlate() {
		state = GameState.Ready;

		cleanseEnemies = false;
		playerJump = false;

		isSuperD = false;
		playerJump = false;
		playerHitByBush = false;
		playerHitByBee = false;
		playerHitByPuppy = false;
		playerHitByMud = false;
		playerHitByPuddle = false;
		playerHitByLog = false;
		playerHitAnimation = false;
		backgroundSpeed = 4;
		enemySpeed = 6;
		puppySpeed = 6;
		puddleTimer = 0;
		logTimer = 0;
		superDogeTimer = 0;

		enemiesAlive = false;
		puppiesAlive = false;
		bushesAlive = false;
		flowersAlive = false;
		objectsAlive = false;

		GamePoints.clearTheNumbers();
		beesOnPlayer = 0;
	}

	public static void SuperDogeMode() {
		backgroundSpeed = 8;
		enemySpeed = 12;
		puppySpeed = 12;
		puddleTimer = 0;
		logTimer = 0;
	}

	public static void normalMode() {
		backgroundSpeed = 4;
		enemySpeed = 6;
		puppySpeed = 6;
	}

}
