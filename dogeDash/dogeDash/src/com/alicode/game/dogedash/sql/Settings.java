package com.alicode.game.dogedash.sql;

public class Settings {

	int id;
	int soundSettings;
	int musicSettings;
	int vibrationSettings;

	public Settings() {
		// TODO Auto-generated constructor stub
	}

	public Settings(int id) {
		this.id = id;
	}

	public Settings(int id, int soundSettings, int musicSettings, int vibrationSettings) {
		this.vibrationSettings = vibrationSettings;
		this.musicSettings = musicSettings;
		this.soundSettings = soundSettings;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSoundSettings() {
		return soundSettings;
	}

	public void setSoundSettings(int soundSettings) {
		this.soundSettings = soundSettings;
	}

	public int getMusicSettings() {
		return musicSettings;
	}

	public void setMusicSettings(int musicSettings) {
		this.musicSettings = musicSettings;
	}

	public int getVibrationSettings() {
		return vibrationSettings;
	}

	public void setVibrationSettings(int vibrationSettings) {
		this.vibrationSettings = vibrationSettings;
	}

}
