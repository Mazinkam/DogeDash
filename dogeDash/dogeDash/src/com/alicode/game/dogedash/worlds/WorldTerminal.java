package com.alicode.game.dogedash.worlds;

import java.util.Iterator;

import com.alicode.game.dogedash.models.Background;
import com.alicode.game.dogedash.models.EnemyBee;
import com.alicode.game.dogedash.models.MotherDoge;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class WorldTerminal extends Table {

	private Background background;
	private Array<EnemyBee> enemyBees;
	private long dogeDelta = 0;
	public final float lane2 = 390;
	public final float lane1 = 240;
	public final float lane0 = 90;
	public MotherDoge motherDoge;

	public WorldTerminal() {
		setBounds(0, 0, 800, 480);
		setClip(true);
		background = new Background(2397, getHeight());
		addActor(background);
		motherDoge = new MotherDoge(this);
		addActor(motherDoge);
		enemyBees = new Array<EnemyBee>();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (TimeUtils.nanoTime() - dogeDelta > 3000000000f) spawnBee();
		
		Iterator<EnemyBee> iter = enemyBees.iterator();
		while (iter.hasNext()) {
			EnemyBee enemyBee = iter.next();
			if (enemyBee.getBounds().x + enemyBee.getWidth() <= 0) {
				iter.remove();
				removeActor(enemyBee);
			}
			if (enemyBee.getBounds().overlaps(motherDoge.getBounds())) {
                iter.remove();
                if (enemyBee.getX() > motherDoge.getX()) {
                    if (enemyBee.getY() > motherDoge.getY()) enemyBee.playerHit(true, true);
                    else enemyBee.playerHit(true, false);
                }
                else {
                    if (enemyBee.getY() > motherDoge.getY()) enemyBee.playerHit(false, true);
                    else enemyBee.playerHit(false, false);
                }
			}
		}
	}
	
	private void spawnBee() {
		int lane = MathUtils.random(0, 2);
		float yPos = 0;
		if (lane == 0) yPos = lane0;
		if (lane == 1) yPos = lane1;
		if (lane == 2) yPos = lane2;
		EnemyBee enemyCar = new EnemyBee(getWidth(), yPos);
		enemyBees.add(enemyCar);
		addActor(enemyCar);
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
