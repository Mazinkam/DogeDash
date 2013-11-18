package com.alicode.game.dogedash.worlds;

import java.util.Iterator;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.Background;
import com.alicode.game.dogedash.models.Bush;
import com.alicode.game.dogedash.models.Flower;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.models.Puppy;
import com.alicode.game.dogedash.models.enemies.EnemyBee;
import com.alicode.game.dogedash.utils.FPSLogger;
import com.alicode.game.dogedash.utils.GamePoints;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class WorldOne extends Table {
	enum GameState {
		Ready, Running, Paused, GameOver
	}
	private Background background, background2;
	private MotherDoge motherDoge;
	private FPSLogger fpsLog;
	private GamePoints gamePoints;

	private Group backgroundGroup;
	private Group onGroundGroup;
	private Group floatingGroup;

	private Array<EnemyBee> enemyBees;
	private Array<Puppy> puppies;
	private Array<Flower> flowers;
	private Array<Bush> bushes;

	private long enemyDelta, flowerDelta, puppyDelta, bushDelta;
	private float puppyRespawnTime, puppyRespawnCooldown;
	private float enemyRespawnTime, enemyRespawnCooldown;
	private float flowerRespawnTime, flowerRespawnCooldown;
	private float bushRespawnTime, bushRespawnCooldown;

	public WorldOne() {
		setBounds(0, 0, 800, 480);
		setClip(true);

		background = new Background(0, 0);
		background2 = new Background(Assets.bg_big_day.getWidth(), 0);
		motherDoge = new MotherDoge();

		enemyBees = new Array<EnemyBee>();
		puppies = new Array<Puppy>();
		flowers = new Array<Flower>();
		bushes = new Array<Bush>();

		backgroundGroup = new Group();
		onGroundGroup = new Group();
		floatingGroup = new Group();

		fpsLog = new FPSLogger();
		gamePoints = new GamePoints();

		addActor(backgroundGroup);
		addActor(onGroundGroup);
		addActor(floatingGroup);
		addActor(fpsLog);
		addActor(gamePoints);

		backgroundGroup.addActor(background);
		backgroundGroup.addActor(background2);

		onGroundGroup.addActor(motherDoge);

		puppyRespawnCooldown = 2000000000f;
		enemyRespawnCooldown = 2000000000f;
		flowerRespawnCooldown = 900000000f;
		bushRespawnCooldown = 900000000f;

	}

	@Override
	public void act(float delta) {
		super.act(delta);
		updateRespawnTimes();
		updateBees();
		updateFlowers();
		updatePups();
		updateBushes();
		Statics.gamePoints++;
	}

	private void updateRespawnTimes() {
		enemyRespawnTime = TimeUtils.nanoTime() - enemyDelta;
		puppyRespawnTime = TimeUtils.nanoTime() - puppyDelta;
		flowerRespawnTime = TimeUtils.nanoTime() - flowerDelta;
		bushRespawnTime = TimeUtils.nanoTime() - bushDelta;

		if (enemyRespawnTime > enemyRespawnCooldown) {
			spawnBee();
			enemyRespawnCooldown -= 1000;
			Gdx.app.log(DogeDashCore.LOG, "enemyRespawnCooldown: " + enemyRespawnCooldown);

		}
		
		if (puppyRespawnTime > puppyRespawnCooldown) {
			spawnPup();
		}
		if (flowerRespawnTime > flowerRespawnCooldown) {
			spawnFlower();
		}

		if (bushRespawnTime > bushRespawnCooldown) {
			spawnBush();
		}

	}

	private void updateBees() {
		Iterator<EnemyBee> beeIter = enemyBees.iterator();
		while (beeIter.hasNext()) {
			EnemyBee enemyBee = beeIter.next();
			enemyBee.setZIndex(4);
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

	}

	private void updateFlowers() {
		Iterator<Flower> flowerIter = flowers.iterator();
		while (flowerIter.hasNext()) {
			Flower flower = flowerIter.next();
			flower.setZIndex(2);
			if (flower.getBounds().x + flower.getWidth() <= 0) {
				flowerIter.remove();
				removeActor(flower);
			}
		}

	}

	private void updatePups() {
		Iterator<Puppy> iter = puppies.iterator();
		while (iter.hasNext()) {
			Puppy puppy = iter.next();
			// puppy.setZIndex(3);
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

	private void updateBushes() {
		Iterator<Bush> iter = bushes.iterator();
		while (iter.hasNext()) {
			Bush bush = iter.next();
			// bush.setZIndex(3);
			if (bush.getBounds().x + bush.getWidth() <= 0) {
				iter.remove();
				removeActor(bush);
			}
			if (bush.getBounds().overlaps(motherDoge.getBounds())) {
				iter.remove();
				if (bush.getX() > motherDoge.getX()) {
					if (bush.getY() > motherDoge.getY())
						bush.playerHit(true, true);
					else
						bush.playerHit(true, false);
				} else {
					if (bush.getY() > motherDoge.getY())
						bush.playerHit(false, true);
					else
						bush.playerHit(false, false);
				}
			}
		}

	}

	private void spawnFlower() {
		float yPos = 0 + (int) (Math.random() * 460);
		// Gdx.app.log(DogeDashCore.LOG, "yPos:: " + yPos + "x" + x);
		Flower flower = new Flower(getWidth(), yPos);
		flowers.add(flower);
		backgroundGroup.addActor(flower);
		flowerDelta = TimeUtils.nanoTime();

	}

	private void spawnBush() {
		float yPos = 0 + (int) (Math.random() * 460);
		// Gdx.app.log(DogeDashCore.LOG, "yPos:: " + yPos + "x" + x);
		Bush bush = new Bush(getWidth(), yPos);
		bushes.add(bush);
		floatingGroup.addActor(bush);
		bushDelta = TimeUtils.nanoTime();

	}

	private void spawnPup() {
		float yPos = 0 + (int) (Math.random() * 460);
		// Gdx.app.log(DogeDashCore.LOG, "yPos:: " + yPos + "x" + x);
		Puppy puppy = new Puppy(getWidth(), yPos);
		puppies.add(puppy);
		onGroundGroup.addActor(puppy);
		puppyDelta = TimeUtils.nanoTime();

	}

	float x;
	float yPos;

	private void spawnBee() {
		// float yPos = 0 + (int)(Math.random()*460);
		x += .075f;
		yPos = (float) Math.sin(x) + 0.5f;
		// Gdx.app.log(DogeDashCore.LOG, "yPos:: " + yPos + "x" + x);
		EnemyBee enemyBee = new EnemyBee(getWidth(), yPos * 360);
		enemyBees.add(enemyBee);
		floatingGroup.addActor(enemyBee);
		enemyDelta = TimeUtils.nanoTime();
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
