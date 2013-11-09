package com.alicode.game.dogedash.utils;

import java.util.Iterator;

import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.models.enemies.EnemyBee;
import com.alicode.game.dogedash.models.enemies.EnemyObject;
import com.alicode.game.dogedash.models.misc.Background;
import com.alicode.game.dogedash.models.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	DogeDashCore game;
	Player player;
	Background background;
	Background background2;
	Array<EnemyObject> enemies = new Array<EnemyObject>();

	WorldRenderer wr;
	Iterator<EnemyObject> enemyIter;

	EnemyObject enemyObject;
	
	public World (DogeDashCore game){
		this.game = game;
		player = new Player(10f, 1, 1.6f, 1, new Vector2(5, 5));
		enemies.add(new EnemyBee(5f, 0, 1, 1, new Vector2(5, 5)));
		background = new Background(0.2f, 0, 42, 8, new Vector2(0, 0));
		background2 = new Background(0.2f, 0, 42, 8, new Vector2(42,0));
		Gdx.input.setInputProcessor(new InputHandler(this));
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Array<EnemyObject> getEnemies(){
		return enemies;
	}
	
	public void update(){
		player.update();
		background.update(Gdx.graphics.getDeltaTime(), player);
		background2.update(Gdx.graphics.getDeltaTime(), player);

		enemyIter = enemies.iterator();
		while(enemyIter.hasNext()){
			enemyObject = enemyIter.next();
			enemyObject.update(Gdx.graphics.getDeltaTime(), player);

			if(player.getBounds().overlaps(enemyObject.getBounds())) // need to handle in enemy class
				Gdx.app.log(DogeDashCore.LOG, "collision!!!!");
		}
	}
	
	public void addEnemyObject(EnemyObject b){
		enemies.add(b);
	}
	
	public Array<EnemyObject> getEnemyObjects(){
		return enemies;
	}
	
	public void setRenderer(WorldRenderer wr){
		this.wr = wr;
	}
	
	public WorldRenderer getRenderer(){
		return wr;
	}
	
	public void dispose(){
		
	}

	public Background getBackground() {
		return background;
	}
	public Background getBackground2() {
		return background2;
	}
}
