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
	public static float backgroundSpeed = Consts.BACKGROUND_SPEED;
	public static float enemySpeed = Consts.ENEMY_SPEED;
	public static float puppySpeed = Consts.PUPPY_SPEED;

	public static boolean cleanseEnemies = false;

	// objvars
	public static boolean enemiesAlive = true;
	public static boolean puppiesAlive = true;
	public static boolean bushesAlive = true;
	public static boolean flowersAlive = true;
	public static boolean objectsAlive = true;

	public static int easyMaxEnemies = 10;
	public static int normalMaxEnemies = 30;
	public static int hardMaxEnemies = 50;

	public static int puddleTimer = Consts.PUDDLE_TIMER;

	public static int logTimer = Consts.LOG_TIMER;

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

	public static int playerVisionRadius = Consts.PLAYER_VISION;

	public static boolean playerGotSwag = false;
	public static boolean playerGotPow = false;

	public static int superDogeTimer = Consts.SUPERD_TIMER;
	public static int dogeLampTimer = Consts.LAMP_TIMER;
	public static int playerJumpCooldown = Consts.JUMP_CD;

	public static int playerMaxHP = 4;

	public static int enemiesOnPlayer = 0;

	public static int playerHpLeft = playerMaxHP - enemiesOnPlayer;

	// costume vars
	public static int eyeCostume = Consts.COSTUME_INIT;
	public static int headCostume = Consts.COSTUME_INIT;
	public static int noseCostume = Consts.COSTUME_INIT;
	public static int backCostume = Consts.COSTUME_INIT;

	// lvl vars
	public static boolean darknessOn = false;

	public static void createLife() {
		enemiesAlive = true;
		puppiesAlive = true;
		bushesAlive = true;
		flowersAlive = true;
		objectsAlive = true;
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

		backgroundSpeed = Consts.BACKGROUND_SPEED;
		enemySpeed = Consts.ENEMY_SPEED;
		puppySpeed = Consts.PUPPY_SPEED;
		puddleTimer = 0;
		logTimer = 0;

		if (Statics.isSuperD)
			superDogeTimer = 0;

		enemiesAlive = false;
		puppiesAlive = false;
		bushesAlive = false;
		flowersAlive = false;
		objectsAlive = false;
		playerVisionRadius = Consts.PLAYER_VISION;

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
		// enemiesOnPlayer = 0;
	}

	public static void normalMode() {
		backgroundSpeed = Consts.BACKGROUND_SPEED;
		enemySpeed = Consts.ENEMY_SPEED;
		playerVisionRadius = Consts.PLAYER_VISION;
		puppySpeed = Consts.PUPPY_SPEED;
	}

	public static void initCostumes() {
		eyeCostume = Consts.COSTUME_INIT;
		headCostume = Consts.COSTUME_INIT;
		noseCostume = Consts.COSTUME_INIT;
		backCostume = Consts.COSTUME_INIT;

	}

}
