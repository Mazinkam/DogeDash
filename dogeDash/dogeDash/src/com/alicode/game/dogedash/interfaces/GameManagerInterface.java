package com.alicode.game.dogedash.interfaces;

public interface GameManagerInterface {

	//load data from db/xml/json/etc
	public void loadGameData();
	//set up game managers that need to be up before game launches
	public void loadGamePreManagers();
	//set up game managers that need to be up after game launches
	public void loadGamePostManagers();
	//load the world
	public void loadGameWorld();
	//load desired lvl
	public void loadGameLevel(int levelNum);
	//used for checking game state
	public void checkGameState();
	//used for screen's render, updating world, chars, enemies, etc
	public void update(float delta);
	//saves game, should be used in hide and pause methods of screen
	public void saveGame();
}
