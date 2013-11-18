package com.alicode.game.dogedash.sql;

public class Level {

	int id, highScore, stylePoints, timeAlive, caughtPuppyNum, missedPuppyNum, puppyPoints;

	public Level() {
		// TODO Auto-generated constructor stub
	}

	public Level(int id) {
		this.id = id;
	}

	public Level(int highScore, int stylePoints, int timeAlive, int caughtPuppyNum,
			int missedPuppyNum, int puppyPoints) {
		this.highScore = highScore;
		this.stylePoints = stylePoints;
		this.timeAlive = timeAlive;
		this.caughtPuppyNum = caughtPuppyNum;
		this.missedPuppyNum = missedPuppyNum;
		this.puppyPoints = puppyPoints;

	}

	public Level(int id, int highScore, int stylePoints, int timeAlive, int caughtPuppyNum,
			int missedPuppyNum, int puppyPoints) {
		this.id = id;
		this.highScore = highScore;
		this.stylePoints = stylePoints;
		this.timeAlive = timeAlive;
		this.caughtPuppyNum = caughtPuppyNum;
		this.missedPuppyNum = missedPuppyNum;
		this.puppyPoints = puppyPoints;

	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int i) {
		this.highScore = i;
	}

	public int getId() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String printScore() {
		return "" + getHighScore();
	}

	public int getStylePoints() {
		return stylePoints;
	}

	public void setStylePoints(int stylePoints) {
		this.stylePoints = stylePoints;
	}

	public int getTimeAlive() {
		return timeAlive;
	}

	public void setTimeAlive(int timeAlive) {
		this.timeAlive = timeAlive;
	}

	public int getCaughtPuppyNum() {
		return caughtPuppyNum;
	}

	public void setCaughtPuppyNum(int caughtPuppyNum) {
		this.caughtPuppyNum = caughtPuppyNum;
	}

	public int getMissedPuppyNum() {
		return missedPuppyNum;
	}

	public void setMissedPuppyNum(int missedPuppyNum) {
		this.missedPuppyNum = missedPuppyNum;
	}

	public int getPuppyPoints() {
		return puppyPoints;
	}

	public void setPuppyPoints(int puppyPoints) {
		this.puppyPoints = puppyPoints;
	}
}
