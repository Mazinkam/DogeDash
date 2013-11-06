package com.alicode.game.dogedash.interfaces;

public interface GameAudioInterface {

	//loads games sound effects, theme music etc
	public void loadGameAudioFiles();
	
	//stops all the music/sound in game
	public void stopGameAudio();
	
	//pauses all the music/sound in game
	public void pauseGameAudio();
	
}
