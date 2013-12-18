package com.alicode.game.dogedash.worlds;

import java.util.Iterator;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.GamePoints;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.Background;
import com.alicode.game.dogedash.models.Bush;
import com.alicode.game.dogedash.models.DogeBiscuit;
import com.alicode.game.dogedash.models.DogeCoin;
import com.alicode.game.dogedash.models.DogeCostumes;
import com.alicode.game.dogedash.models.DogeLamp;
import com.alicode.game.dogedash.models.DogeOnHitEffect;
import com.alicode.game.dogedash.models.Flower;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.models.Puppy;
import com.alicode.game.dogedash.models.enemies.EnemyMoth;
import com.alicode.game.dogedash.models.enemies.EnemyLog;
import com.alicode.game.dogedash.models.enemies.EnemyMoth;
import com.alicode.game.dogedash.models.enemies.EnemyMoth;
import com.alicode.game.dogedash.models.enemies.EnemyMud;
import com.alicode.game.dogedash.models.enemies.EnemyPuddle;
import com.alicode.game.dogedash.utils.txt.FPSLogger;
import com.alicode.game.dogedash.utils.txt.GameScore;
import com.alicode.game.dogedash.worlds.effects.WorldTwoLight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class WorldTwo extends Table {

	private Background background, background2;
	private MotherDoge motherDoge;
	private DogeCostumes dogeCostumes;
//	private FPSLogger fpsLog;
	private GameScore gamePoints;

	private Group backgroundGroup;
	private Group onGroundGroup;
	private Group inBetweenGroup;
	private Group floatingGroup;
	private WorldTwoLight worldTwoLight;

	private Array<Puppy> puppies;
	private Array<Flower> flowers;
	private Array<Bush> bushes;
	private Array<DogeCoin> dogeCoins;
	private Array<DogeBiscuit> dogeBiscuits;
	private Array<DogeLamp> dogeLamps;
	private Array<DogeOnHitEffect> dogeOnHitEffects;

	private Array<EnemyMoth> enemyMoths;
	private Array<EnemyMud> enemyMuds;
	private Array<EnemyPuddle> enemyPuddles;
	private Array<EnemyLog> enemyLogs;

	private long enemyDelta, flowerDelta, puppyDelta, bushDelta, enemyLogDelta, enemyPuddleDelta, enemyMudDelta, dogeCoinDelta, dogeBiscuitDelta, dogeLampDelta;
	private float puppyRespawnTime, puppyRespawnCooldown;
	private float enemyRespawnTime, enemyRespawnCooldown;
	private float flowerRespawnTime, flowerRespawnCooldown;
	private float bushRespawnTime, bushRespawnCooldown;
	private float enemyMudRespawnTime, enemyMudRespawnCooldown;
	private float enemyPuddleRespawnTime, enemyPuddleRespawnCooldown;
	private float enemyLogRespawnTime, enemyLogRespawnCooldown;
	private float dogeCoinRespawnTime, dogeCoinRespawnCooldown;
	private float dogeBiscuitRespawnTime, dogeBiscuitRespawnCooldown;
	private float dogeLampRespawnTime, dogeLampRespawnCooldown;

	private int levelEnemyLimit;

	public WorldTwo() {
		setBounds(0, 0, 800, 480);
		setClip(true);

		background = new Background(0, 0);
		background2 = new Background(Assets.bg_big_day.getWidth(), 0);
		motherDoge = new MotherDoge();
		dogeCostumes = new DogeCostumes();

		puppies = new Array<Puppy>();
		flowers = new Array<Flower>();
		bushes = new Array<Bush>();

		dogeCoins = new Array<DogeCoin>();
		dogeBiscuits = new Array<DogeBiscuit>();
		dogeLamps = new Array<DogeLamp>();
		dogeOnHitEffects = new Array<DogeOnHitEffect>();

		enemyMoths = new Array<EnemyMoth>();
		enemyLogs = new Array<EnemyLog>();
		enemyPuddles = new Array<EnemyPuddle>();
		enemyMuds = new Array<EnemyMud>();

		backgroundGroup = new Group();
		onGroundGroup = new Group();
		inBetweenGroup = new Group();
		floatingGroup = new Group();

//		fpsLog = new FPSLogger();
		gamePoints = new GameScore();

		worldTwoLight = new WorldTwoLight();

		addActor(backgroundGroup);
		addActor(onGroundGroup);
		addActor(inBetweenGroup);
		addActor(floatingGroup);
//		addActor(fpsLog);
		addActor(gamePoints);
		backgroundGroup.addActor(worldTwoLight);

		backgroundGroup.addActor(background);
		backgroundGroup.addActor(background2);

		floatingGroup.addActor(motherDoge);
		floatingGroup.addActor(dogeCostumes);

		puppyRespawnCooldown = 2000000000f;
		enemyRespawnCooldown = 2000000000f;
		flowerRespawnCooldown = 900000000f;
		bushRespawnCooldown = 9000000000f;
		dogeCoinRespawnCooldown = 10000000000f;
		dogeBiscuitRespawnCooldown = 10000000000f;
		dogeLampRespawnCooldown = 10000000000f;

		enemyLogRespawnCooldown = 4000000000f;
		enemyMudRespawnCooldown = 3000000000f;
		enemyPuddleRespawnCooldown = 10000000000f;

		switch (Statics.gameLevelDifficulty) {
		case 1:
			levelEnemyLimit = Statics.easyMaxEnemies;
			break;
		case 2:
			levelEnemyLimit = Statics.normalMaxEnemies;
			break;
		case 3:
			levelEnemyLimit = Statics.hardMaxEnemies;
			break;
		}

	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (Statics.state == Statics.GameState.Running) {
			updateRespawnTimes();
			GamePoints.currentScore++;
		}
		updateBees();
		updateFlowers();
		updatePups();
		updateBushes();
		updateEnemyLogs();
		updateEnemyMuds();
		updateEnemyPuddles();
		updateDogeCoins();
		updateDogeBiscuits();
		updateDogeLamps();

	}



	private void updateRespawnTimes() {
		if (GamePoints.finalScore() > 1000) {
			enemyRespawnCooldown = 2000000000f / 2;
			GamePoints.currentScore++;
		}
		if (GamePoints.finalScore() > 10000) {
			enemyRespawnCooldown = 2000000000f / 3;
			GamePoints.currentScore++;
		}
		if (GamePoints.finalScore() > 100000) {
			enemyRespawnCooldown = 2000000000f / 4;
			GamePoints.currentScore++;
		}
		if (GamePoints.finalScore() > 1000000) {
			enemyRespawnCooldown = 2000000000f / 5;
			GamePoints.currentScore++;
		}
		if (GamePoints.finalScore() > 10000000) {
			enemyRespawnCooldown = 2000000000f / 6;
			GamePoints.currentScore++;
		}
		if (GamePoints.finalScore() > 100000000) {
			enemyRespawnCooldown = 2000000000f / 7;
			GamePoints.currentScore++;
		}

		enemyRespawnTime = TimeUtils.nanoTime() - enemyDelta;
		puppyRespawnTime = TimeUtils.nanoTime() - puppyDelta;
		flowerRespawnTime = TimeUtils.nanoTime() - flowerDelta;
		bushRespawnTime = TimeUtils.nanoTime() - bushDelta;
		enemyLogRespawnTime = TimeUtils.nanoTime() - enemyLogDelta;
		enemyPuddleRespawnTime = TimeUtils.nanoTime() - enemyPuddleDelta;
		enemyMudRespawnTime = TimeUtils.nanoTime() - enemyMudDelta;
		dogeCoinRespawnTime = TimeUtils.nanoTime() - dogeCoinDelta;
		dogeBiscuitRespawnTime = TimeUtils.nanoTime() - dogeBiscuitDelta;
		dogeLampRespawnTime = TimeUtils.nanoTime() - dogeLampDelta;

		if (enemyRespawnTime > enemyRespawnCooldown) {
			spawnBee();
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

		if (enemyLogRespawnTime > enemyLogRespawnCooldown) {
			spawnLog();
		}

		if (enemyMudRespawnTime > enemyMudRespawnCooldown) {
			spawnMud();
		}

		if (enemyPuddleRespawnTime > enemyPuddleRespawnCooldown) {
			spawnPuddle();
		}

		if (dogeCoinRespawnTime > dogeCoinRespawnCooldown) {
			spawnDogeCoin();
		}
		if (dogeBiscuitRespawnTime > dogeBiscuitRespawnCooldown) {
			spawnDogeBiscuit();
		}
		
		if (dogeLampRespawnTime > dogeLampRespawnCooldown) {
			spawnDogeLamp();
		}


	}



	private void updateDogeLamps() {
		Iterator<DogeLamp> dCoinIter = dogeLamps.iterator();

		while (dCoinIter.hasNext()) {
			DogeLamp dogeLamp = dCoinIter.next();
			if (dogeLamp.getBounds().x + dogeLamp.getWidth() < 0) {
				dogeLamp.disposeTrail();
				dCoinIter.remove();
				removeActor(dogeLamp);

			}
			if (dogeLamp.getBounds().overlaps(motherDoge.getBounds())) {
				dCoinIter.remove();
				dogeLamp.playerHit();

			}
		}

		
	}
	private void updateDogeBiscuits() {
		Iterator<DogeBiscuit> dCoinIter = dogeBiscuits.iterator();

		while (dCoinIter.hasNext()) {
			DogeBiscuit dogeBiscuit = dCoinIter.next();
			if (dogeBiscuit.getBounds().x + dogeBiscuit.getWidth() < 0) {
				dogeBiscuit.disposeTrail();
				dCoinIter.remove();
				removeActor(dogeBiscuit);

			}
			if (dogeBiscuit.getBounds().overlaps(motherDoge.getBounds())) {
				dCoinIter.remove();
				dogeBiscuit.playerHit();

			}
		}

	}

	private void updateDogeCoins() {
		Iterator<DogeCoin> dCoinIter = dogeCoins.iterator();

		while (dCoinIter.hasNext()) {
			DogeCoin dogeCoin = dCoinIter.next();
			if (dogeCoin.getBounds().x + dogeCoin.getWidth() < 0) {
				dogeCoin.disposeTrail();
				dCoinIter.remove();
				removeActor(dogeCoin);

			}
			if (dogeCoin.getBounds().overlaps(motherDoge.getBounds())) {
				dCoinIter.remove();
				dogeCoin.playerHit();

			}
		}
	}

	private void updateEnemyPuddles() {
		Iterator<EnemyPuddle> logIter = enemyPuddles.iterator();

		while (logIter.hasNext()) {
			EnemyPuddle enemyPuddle = logIter.next();
			if (enemyPuddle.getBounds().x + enemyPuddle.getWidth() < 0) {
				logIter.remove();
				removeActor(enemyPuddle);
				GamePoints.puppyMissedNum++;
			}
			if (enemyPuddle.getBounds().overlaps(motherDoge.getBounds())) {
				if (!Statics.playerJump) {
					if (enemyPuddle.getX() > motherDoge.getX()) {
						if (enemyPuddle.getY() > motherDoge.getY())
							enemyPuddle.playerHit(true, true);
						else
							enemyPuddle.playerHit(true, false);
					} else {
						if (enemyPuddle.getY() > motherDoge.getY())
							enemyPuddle.playerHit(false, true);
						else
							enemyPuddle.playerHit(false, false);
					}

				}
				if (Statics.playerJump) {
					spawnDogeSwag();
				}
				if (Statics.isSuperD) {
					logIter.remove();
				}
			}

		}

	}

	private void updateEnemyMuds() {
		Iterator<EnemyMud> logIter = enemyMuds.iterator();

		while (logIter.hasNext()) {
			EnemyMud enemyMud = logIter.next();
			if (enemyMud.getBounds().x + enemyMud.getWidth() < 0) {
				logIter.remove();
				removeActor(enemyMud);
			}
			if (enemyMud.getBounds().overlaps(motherDoge.getBounds())) {
				if (!Statics.playerJump) {
					spawnDogePow();
					if (enemyMud.getX() > motherDoge.getX()) {
						if (enemyMud.getY() > motherDoge.getY())
							enemyMud.playerHit(true, true);
						else
							enemyMud.playerHit(true, false);
					} else {
						if (enemyMud.getY() > motherDoge.getY())
							enemyMud.playerHit(false, true);
						else
							enemyMud.playerHit(false, false);
					}
				}
				if (Statics.playerJump) {
					spawnDogeSwag();
				}
				if (Statics.isSuperD)
					logIter.remove();
			}
		}

	}

	private void updateEnemyLogs() {
		Iterator<EnemyLog> logIter = enemyLogs.iterator();

		while (logIter.hasNext()) {
			EnemyLog enemyLog = logIter.next();
			if (enemyLog.getBounds().x + enemyLog.getWidth() < 0) {
				logIter.remove();
				removeActor(enemyLog);
				// Gdx.app.log(DogeDashCore.LOG, "enemyLogs " + enemyLogs.size);
			}
			if (enemyLog.getBounds().overlaps(motherDoge.getBounds())) {
				if (!Statics.playerJump) {
					spawnDogePow();
					if (enemyLog.getX() > motherDoge.getX()) {
						if (enemyLog.getY() > motherDoge.getY())
							enemyLog.playerHit(true, true);
						else
							enemyLog.playerHit(true, false);
					} else {
						if (enemyLog.getY() > motherDoge.getY())
							enemyLog.playerHit(false, true);
						else
							enemyLog.playerHit(false, false);
					}
				}
				if (Statics.playerJump) {
					spawnDogeSwag();
				}
				if (Statics.isSuperD)
					logIter.remove();
			}
		}

	}

	private void updateBees() {
		Iterator<EnemyMoth> mothIter = enemyMoths.iterator();
		while (mothIter.hasNext()) {
			EnemyMoth enemyMoth = mothIter.next();
			if (enemyMoth.getBounds().x + enemyMoth.getWidth() < 0) {
				mothIter.remove();
				removeActor(enemyMoth);
			}
			if (enemyMoth.getBounds().overlaps(motherDoge.getBounds())) {
				if (!Statics.playerJump && !Statics.playerHitByBee) {
					mothIter.remove();
					spawnDogePow();
					floatingGroup.addActor(enemyMoth);
					inBetweenGroup.removeActor(enemyMoth);
					if (enemyMoth.getX() > motherDoge.getX()) {
						if (enemyMoth.getY() > motherDoge.getY())
							enemyMoth.playerHit(true, true);
						else
							enemyMoth.playerHit(true, false);
					} else {
						if (enemyMoth.getY() > motherDoge.getY())
							enemyMoth.playerHit(false, true);
						else
							enemyMoth.playerHit(false, false);
					}
				}
				if (Statics.playerJump) {
					spawnDogeSwag();
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

			if (puppy.getBounds().x + puppy.getWidth() <= 0) {
				iter.remove();
				removeActor(puppy);
			}
			if (puppy.getBounds().overlaps(motherDoge.getBounds()) && !Statics.playerJump) {
				iter.remove();

				GamePoints.puppyCaughtNum++;
				puppy.playerHit();

			}
		}

	}

	private void updateBushes() {
		Iterator<Bush> iter = bushes.iterator();
		while (iter.hasNext()) {
			Bush bush = iter.next();
			if (bush.getBounds().x + bush.getWidth() <= 0) {
				iter.remove();
				removeActor(bush);
			}
			if (bush.getBounds().overlaps(motherDoge.getBounds())) {
				if (!Statics.playerJump) {
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

	}

	private void spawnDogeSwag() {
		if (!Statics.playerGotSwag) {
			DogeOnHitEffect dogeOnHitEffect = new DogeOnHitEffect(MotherDoge.playerX + Assets.character.getRegionHeight(), MotherDoge.playerY + Assets.character.getRegionWidth());
			dogeOnHitEffects.add(dogeOnHitEffect);
			dogeOnHitEffect.playerGotSwag();
			floatingGroup.addActor(dogeOnHitEffect);
		}
	}

	private void spawnDogePow() {
		if (!Statics.playerGotPow) {
			DogeOnHitEffect dogeOnHitEffect = new DogeOnHitEffect(MotherDoge.playerX + Assets.character.getRegionHeight(), MotherDoge.playerY + Assets.character.getRegionWidth());
			dogeOnHitEffects.add(dogeOnHitEffect);
			dogeOnHitEffect.playerGotHit();
			floatingGroup.addActor(dogeOnHitEffect);
		}
	}
	
	private void spawnDogeLamp() {
		float yPos = 0 + (int) (Math.random() * 460);
		DogeLamp dogeLamp = new DogeLamp(getWidth() + yPos * 2, yPos);
		dogeLamps.add(dogeLamp);
		inBetweenGroup.addActor(dogeLamp);
		dogeLampDelta = TimeUtils.nanoTime();
		
	}

	private void spawnDogeBiscuit() {
		float yPos = 0 + (int) (Math.random() * 460);
		DogeBiscuit dogeBiscuit = new DogeBiscuit(getWidth() + yPos * 2, yPos);
		dogeBiscuits.add(dogeBiscuit);
		inBetweenGroup.addActor(dogeBiscuit);
		dogeBiscuitDelta = TimeUtils.nanoTime();

	}

	private void spawnDogeCoin() {
		float yPos = 0 + (int) (Math.random() * 460);
		DogeCoin dogeCoin = new DogeCoin(getWidth() + yPos * 2, yPos);
		dogeCoins.add(dogeCoin);
		inBetweenGroup.addActor(dogeCoin);
		dogeCoinDelta = TimeUtils.nanoTime();

	}

	private void spawnFlower() {
		float yPos = 0 + (int) (Math.random() * 460);
		Flower flower = new Flower(getWidth() + yPos * 2, yPos);
		flowers.add(flower);
		backgroundGroup.addActor(flower);
		flowerDelta = TimeUtils.nanoTime();

	}

	private void spawnBush() {
		float yPos = 0 + (int) (Math.random() * 460);
		Bush bush = new Bush(getWidth() + yPos * 2, yPos);
		bushes.add(bush);
		floatingGroup.addActor(bush);
		bushDelta = TimeUtils.nanoTime();

	}

	private void spawnPup() {
		float yPos = 10 + (int) (Math.random() * 460);
		Puppy puppy = new Puppy(getWidth() + yPos * 2, yPos);
		puppies.add(puppy);
		inBetweenGroup.addActor(puppy);
		puppyDelta = TimeUtils.nanoTime();

	}

	private void spawnBee() {
		if (enemyMoths.size <= levelEnemyLimit) {
			float yPos = 0 + (int) (Math.random() * 460);

			EnemyMoth enemyMoth = new EnemyMoth(getWidth() + yPos * 2, yPos);
			enemyMoths.add(enemyMoth);
			inBetweenGroup.addActor(enemyMoth);
			enemyDelta = TimeUtils.nanoTime();
		}
	}

	private void spawnPuddle() {
		float yPos = 0 + (int) (Math.random() * 460);
		EnemyPuddle enemyPuddle = new EnemyPuddle(getWidth() + yPos * 2, yPos);
		enemyPuddles.add(enemyPuddle);
		onGroundGroup.addActor(enemyPuddle);
		enemyPuddleDelta = TimeUtils.nanoTime();

	}

	private void spawnMud() {
		float yPos = 0 + (int) (Math.random() * 460);
		EnemyMud enemyMud = new EnemyMud(getWidth() + yPos * 2, yPos);
		enemyMuds.add(enemyMud);
		onGroundGroup.addActor(enemyMud);
		enemyMudDelta = TimeUtils.nanoTime();

	}

	private void spawnLog() {
		float yPos = 0 + (int) (Math.random() * 460);
		EnemyLog enemyLog = new EnemyLog(getWidth() + yPos * 2, yPos);
		enemyLogs.add(enemyLog);
		inBetweenGroup.addActor(enemyLog);
		enemyLogDelta = TimeUtils.nanoTime();

	}

	public Array<EnemyMoth> getEnemyMoths() {
		return enemyMoths;
	}

	public void setEnemyMoths(Array<EnemyMoth> enemyMoths) {
		this.enemyMoths = enemyMoths;
	}

	public MotherDoge getMotherDoge() {
		return motherDoge;
	}

	public void setMotherDoge(MotherDoge motherDoge) {
		this.motherDoge = motherDoge;
	}

	public DogeCostumes getDogeCostumes() {
		return dogeCostumes;
	}

	public void setDogeCostumes(DogeCostumes dogeCostumes) {
		this.dogeCostumes = dogeCostumes;
	}

}
