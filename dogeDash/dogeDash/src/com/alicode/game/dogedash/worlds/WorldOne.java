package com.alicode.game.dogedash.worlds;

import java.util.Iterator;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.models.Background;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.models.Puppy;
import com.alicode.game.dogedash.models.enemies.EnemyBee;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class WorldOne extends Table {

	private Background background, background2;
	private Array<EnemyBee> enemyBees;
	private Array<Puppy> puppies;
	private long dogeDelta = 0;
	public MotherDoge motherDoge;

	public WorldOne() {
		setBounds(0, 0, 800, 480);
		setClip(true);
		background = new Background(0, 0);
		addActor(background);
		background2 = new Background(Assets.bg_big.getWidth(), 0);
		addActor(background2);
		motherDoge = new MotherDoge();
		addActor(motherDoge);
		enemyBees = new Array<EnemyBee>();
		puppies = new Array<Puppy>();
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (TimeUtils.nanoTime() - dogeDelta > 300000000f){
			spawnBee();
			spawnPup();
		}

		Iterator<EnemyBee> beeIter = enemyBees.iterator();
		while (beeIter.hasNext()) {
			EnemyBee enemyBee = beeIter.next();
			if (enemyBee.getBounds().x + enemyBee.getWidth() <= 0) {
				beeIter.remove();
				removeActor(enemyBee);
			}
			if (enemyBee.getBounds().overlaps(motherDoge.getBounds())) {
				beeIter.remove();
				if (enemyBee.getX() > motherDoge.getX()) {
					if (enemyBee.getY() > motherDoge.getY())
						enemyBee.playerHit(true, true);
					else
						enemyBee.playerHit(true, false);
				} else {
					if (enemyBee.getY() > motherDoge.getY())
						enemyBee.playerHit(false, true);
					else
						enemyBee.playerHit(false, false);
				}
			}
		}

//		if (TimeUtils.nanoTime() - dogeDelta > 300000000f)
			

		Iterator<Puppy> iter = puppies.iterator();
		while (iter.hasNext()) {
			Puppy puppy = iter.next();
			if (puppy.getBounds().x + puppy.getWidth() <= 0) {
				iter.remove();
				removeActor(puppy);
			}
			if (puppy.getBounds().overlaps(motherDoge.getBounds())) {
				iter.remove();
				if (puppy.getX() > motherDoge.getX()) {
					if (puppy.getY() > motherDoge.getY())
						puppy.playerHit(true, true);
					else
						puppy.playerHit(true, false);
				} else {
					if (puppy.getY() > motherDoge.getY())
						puppy.playerHit(false, true);
					else
						puppy.playerHit(false, false);
				}
			}
		}
	}

	private void spawnPup() {
		float yPos = 0 + (int)(Math.random()*460);
		// Gdx.app.log(DogeDashCore.LOG, "yPos:: " + yPos + "x" + x);
		Puppy puppy = new Puppy(getWidth(), yPos );
		puppies.add(puppy);
		addActor(puppy);
		dogeDelta = TimeUtils.nanoTime();

	}

	float x;
	float yPos;

	private void spawnBee() {
		// float yPos = 0 + (int)(Math.random()*460);
		x += .075f;
		yPos = (float) Math.sin(x);
		// Gdx.app.log(DogeDashCore.LOG, "yPos:: " + yPos + "x" + x);
		EnemyBee enemyBee = new EnemyBee(getWidth(), yPos * 400);
		enemyBees.add(enemyBee);
		addActor(enemyBee);
		dogeDelta = TimeUtils.nanoTime();
	}

	public Array<EnemyBee> getEnemyBees() {
		return enemyBees;
	}

	public void setEnemyBees(Array<EnemyBee> enemyBees) {
		this.enemyBees = enemyBees;
	}

	public MotherDoge getMotherDoge() {
		return motherDoge;
	}

	public void setMotherDoge(MotherDoge motherDoge) {
		this.motherDoge = motherDoge;
	}

}
