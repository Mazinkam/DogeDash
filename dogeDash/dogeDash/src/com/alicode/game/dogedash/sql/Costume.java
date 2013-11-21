package com.alicode.game.dogedash.sql;

import com.alicode.game.dogedash.Assets;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Costume extends TextureRegion{

	int id;
	int isOwned;
	int itemPrice;
	String itemName;

	public Costume() {
		// TODO Auto-generated constructor stub
	}

	public Costume(int id) {
		this.id = id;
	}

	public Costume(int id, String itemName, int isOwned, int itemPrice) {
		this.isOwned = isOwned;
		this.itemName = itemName;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
