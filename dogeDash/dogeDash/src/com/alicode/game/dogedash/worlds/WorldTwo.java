package com.alicode.game.dogedash.worlds;

import java.util.Iterator;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.models.Background;
import com.alicode.game.dogedash.models.MotherDoge;
import com.alicode.game.dogedash.models.Puppy;
import com.alicode.game.dogedash.models.enemies.EnemyMoth;
import com.alicode.game.dogedash.models.enemies.EnemyMoth;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class WorldTwo extends Table {

	private Background background, background2;
	private Array<EnemyMoth> enemyMoths;
	private Array<Puppy> puppies;
	private long dogeDelta = 0;
	public MotherDoge motherDoge;

	public WorldTwo() {
		setBounds(0, 0, 800, 480);
		setClip(true);
		background = new Background(0, 0);
		addActor(background);
		background2 = new Background(Assets.bg_big_night.getWidth(), 0);
		addActor(background2);
		motherDoge = new MotherDoge();
		addActor(motherDoge);
		enemyMoths = new Array<EnemyMoth>();
		puppies = new Array<Puppy>();
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (TimeUtils.nanoTime() - dogeDelta > 100000000f){
			spawnBee();
			spawnPup();
		}

		Iterator<EnemyMoth> mothIter = enemyMoths.iterator();
		while (mothIter.hasNext()) {
			EnemyMoth enemyMoth = mothIter.next();
			if (enemyMoth.getBounds().x + enemyMoth.getWidth() <= 0) {
				mothIter.remove();
				removeActor(enemyMoth);
			}
			if (enemyMoth.getBounds().overlaps(motherDoge.getBounds())) {
				mothIter.remove();
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
		}

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
		yPos = (float) Math.sin(x)/2;
		// Gdx.app.log(DogeDashCore.LOG, "yPos:: " + yPos + "x" + x);
		EnemyMoth enemyMoth = new EnemyMoth(getWidth(), yPos * 800);
		enemyMoths.add(enemyMoth);
		addActor(enemyMoth);
		dogeDelta = TimeUtils.nanoTime();
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

}
