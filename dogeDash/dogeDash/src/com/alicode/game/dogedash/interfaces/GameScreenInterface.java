package com.alicode.game.dogedash.interfaces;

public interface GameScreenInterface {

	// load game manager in game screen. add gm.update(); to render, handles
	// most game related things like creating world and management
	public void loadGameManager();
	
	//Loads game menu for game
	public void loadGameMenu();

}
