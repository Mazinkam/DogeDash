package com.alicode.game.dogedash.sql;

public class Misc {
	private int id;
	private int dogeCoins;

	public Misc() {

	}

	public Misc(int id) {
		this.id = id;
	}
	
	public Misc(int id, int dogeCoins)
	{
		this.id = id;
		this.dogeCoins = dogeCoins;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDogeCoins() {
		return dogeCoins;
	}

	public void setDogeCoins(int dogeCoins) {
		this.dogeCoins = dogeCoins;
	}

}
