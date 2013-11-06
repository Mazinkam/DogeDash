package com.alicode.game.dogedash.managers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class AudioManager {

	public void playSound(Sound s, float volume) {
		if (GameSettingsManager.isSoundOn()) {
			s.play(volume);
		}
	}

	public void playMusic(Music m, boolean isLooping, float volume) {
		if (GameSettingsManager.isMusicOn()) {
			m.setLooping(isLooping);
			m.setVolume(volume);
			m.play();
		}
	}

}
