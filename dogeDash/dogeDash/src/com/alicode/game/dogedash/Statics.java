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
	public static float backgroundSpeed = 5;
	public static float enemySpeed = 7;
	public static float puppySpeed = 7;

	public static boolean cleanseEnemies = false;

	// objvars

	public static boolean enemiesAlive = true;
	public static boolean puppiesAlive = true;
	public static boolean bushesAlive = true;
	public static boolean flowersAlive = true;
	public static boolean objectsAlive = true;

	public static int easyMaxEnemies = 10;
	public static int normalMaxEnemies = 10;
	public static int hardMaxEnemies = 10;

	public static int logTimerInit = 3000;
	public static int puddleTimerInit = 400;

	public static int puddleTimer = puddleTimerInit;

	public static int logTimer = logTimerInit;

	// player vars
	public static boolean isSuperD = false;
	public static boolean dogeLampActive = false;
	
	public static boolean playerJump = false;
	public static boolean playerHitByBush = false;
	public static boolean playerHitByBee = false;
	public static boolean playerHitByPuppy = false;
	public static boolean playerHitByMud = false;
	public static boolean playerHitByPuddle = false;
	public static boolean playerHitByLog = false;
	public static boolean playerHitAnimation = false;

	
	public static int visionReduction = 50;
	
	public static final int playerVisionRadiusInit = 1000;
	
	public static int playerVisionRadius = playerVisionRadiusInit;

	public static boolean playerGotSwag = false;
	public static boolean playerGotPow = false;

	public static float playerX, playerY, playerZ;
	
	public static int superDogeTimerInit = 800;
	public static int dogeLampTimerInit = 400;
	public static int playerJumpCooldownInit = 50;

	public static int superDogeTimer = superDogeTimerInit;
	public static int dogeLampTimer = dogeLampTimerInit;
	public static int playerJumpCooldown = playerJumpCooldownInit;

	public static int enemiesOnPlayer = 0;

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

		playerGotSwag = false;
		playerGotPow = false;

		backgroundSpeed = 5;
		enemySpeed = 7;
		puppySpeed = 7;
		puddleTimer = 0;
		logTimer = 0;
		if (Statics.isSuperD)
			superDogeTimer = 0;

		enemiesAlive = false;
		puppiesAlive = false;
		bushesAlive = false;
		flowersAlive = false;
		objectsAlive = false;
		playerVisionRadius = playerVisionRadiusInit;

		GamePoints.clearTheNumbers();
		enemiesOnPlayer = 0;
	}

	public static void SuperDogeMode() {
		backgroundSpeed = 9;
		enemySpeed = 13;
		puppySpeed = 13;
		puddleTimer = 0;
		logTimer = 0;
		playerJump = false;
		playerHitByBush = false;
		playerHitByBee = false;
		playerHitByPuppy = false;
		playerHitByMud = false;
		playerHitByPuddle = false;
		playerHitByLog = false;
		playerHitAnimation = false;
		cleanseEnemies = true;
		puddleTimer = 0;
		logTimer = 0;
		enemiesOnPlayer = 0;
		//playerVisionRadius = 1000;
	}

	public static void normalMode() {
		backgroundSpeed = 4;
		enemySpeed = 6;
		playerVisionRadius = 600;
		puppySpeed = 6;
	}

}
