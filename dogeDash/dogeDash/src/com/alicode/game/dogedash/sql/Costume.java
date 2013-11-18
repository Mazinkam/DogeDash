package com.alicode.game.dogedash.sql;

public class Costume {

	int id;
	int isOwned;
	int itemPrice;

	public Costume() {
		// TODO Auto-generated constructor stub
	}

	public Costume(int id) {
		this.id = id;
	}

	public Costume(int id, int isOwned, int itemPrice) {
		this.isOwned = isOwned;
		this.itemPrice = itemPrice;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsOwned() {
		return isOwned;
	}

	public void setIsOwned(int isOwned) {
		this.isOwned = isOwned;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

}
