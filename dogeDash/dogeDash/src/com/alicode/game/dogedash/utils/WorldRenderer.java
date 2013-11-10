package com.alicode.game.dogedash.utils;

import java.io.IOException;
import java.util.Iterator;

import com.alicode.game.dogedash.DogeDashCore;
import com.alicode.game.dogedash.models.enemies.EnemyObject;
import com.alicode.game.dogedash.models.misc.Background;
import com.alicode.game.dogedash.models.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;

public class WorldRenderer {

	World world;
	SpriteBatch batch;
	Player player;
	Background background, background2;
	OrthographicCamera cam;
	Texture playerTexture, enemyTexture, gameBackground,gameBackground2;
	float width, height;
	ShapeRenderer sr;
	Array<EnemyObject> enemies;
	Iterator<EnemyObject> eIter;
	EnemyObject e;
	ParticleEmitter exhaust;

	public WorldRenderer(World world) {
		this.world = world;

		world.setRenderer(this);

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		gameBackground = new Texture("core/background_big.png");
		gameBackground.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		playerTexture = new Texture("core/game/player/character.png");
		playerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		enemyTexture = new Texture("core/game/enemies/enemyBee.png");
		enemyTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		sr = new ShapeRenderer();

		exhaust = new ParticleEmitter();

		try {
			exhaust.load(Gdx.files.internal("data/exhaust").reader(2024));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Texture ballTexture = new Texture(Gdx.files.internal("data/particle.png"));
		Sprite ball = new Sprite(ballTexture);
		exhaust.setSprite(ball);
		exhaust.getScale().setHigh(0.3f);
		exhaust.start();
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Get objects from world
		player = world.getPlayer();
		enemies = world.getEnemies();
		background = world.getBackground();
		background2 = world.getBackground2();

		// Deal with exhaust particle


		// Update camera
		cam.position.set(player.getPosition().x + 320, 240, 0);
		cam.update();

		// Set the batch matrix to the camera matrix
		batch.setProjectionMatrix(cam.combined);

		// Commence rendering
		batch.begin();

		batch.draw(gameBackground, background.getPosition().x, background.getPosition().y, background.getWidth(), background.getHeight(), background.getWidth(),
				background.getHeight(), 1, 1, background.getRotation(), 0, 0, gameBackground.getWidth(), gameBackground.getHeight(), false, false);
		
		batch.draw(gameBackground, background2.getPosition().x, background2.getPosition().y, background2.getWidth(), background2.getHeight(), background2.getWidth(),
				background2.getHeight(), 1, 1, background2.getRotation(), 0, 0, gameBackground.getWidth(), gameBackground.getHeight(), false, false);

		// Drawing the exhaust
		exhaust.draw(batch, Gdx.graphics.getDeltaTime());

		// Drawing the player
//		batch.draw(texture, x, y, originX, originY, width, height, scaleX, scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY)
		batch.draw(playerTexture, player.getPosition().x, player.getPosition().y, player.getWidth()/2, player.getHeight()/2, player.getWidth(),
				player.getHeight(), 1, 1, player.getRotation(), 0, 0, playerTexture.getWidth(), playerTexture.getHeight(), false, false);
		

		// Get an iterator and use it to draw the enemies
		eIter = enemies.iterator();
		while (eIter.hasNext()) {
			e = eIter.next();
			batch.draw(enemyTexture, e.getPosition().x, e.getPosition().y, e.getWidth()/2, e.getHeight()/2, e.getWidth(), e.getHeight(), 1, 1,
					e.getRotation(), 0, 0, enemyTexture.getWidth(), enemyTexture.getHeight(), false, false);
		
			
			
			exhaust.setPosition(e.getPosition().x + e.getWidth() / 2, e.getPosition().y + e.getHeight() / 2);
			setExhaustRotation();
		}

		// Done rendering
		batch.end();

		// If we're debugging, draw collision boxes
		if (DogeDashCore.DEV_MODE) {
			sr.setProjectionMatrix(cam.combined);
			sr.begin(ShapeType.Rectangle);
			sr.setColor(Color.CYAN);
			sr.rect(player.getBounds().x, player.getBounds().y, player.getBounds().width, player.getBounds().height);
			sr.setColor(Color.BLUE);
			sr.rect(background.getBounds().x, background.getBounds().y, background.getBounds().width, background.getBounds().height);
			sr.setColor(Color.ORANGE);
			sr.rect(background2.getBounds().x, background2.getBounds().y, background2.getBounds().width, background2.getBounds().height);
			sr.setColor(Color.RED);

			eIter = enemies.iterator();
			while (eIter.hasNext()) {
				e = eIter.next();
				sr.rect(e.getBounds().x, e.getBounds().y, e.getBounds().width, e.getBounds().height);
			}

			sr.end();
		}
	}

	private void setExhaustRotation() {
		float angle = player.getRotation();
		exhaust.getAngle().setLow(angle + 270);
		exhaust.getAngle().setHighMin(angle + 270 - 45);
		exhaust.getAngle().setHighMax(angle + 270 + 45);
	}

	public OrthographicCamera getCamera() {
		return cam;
	}

	public void dispose() {
		batch.dispose();
		playerTexture.dispose();
		enemyTexture.dispose();
		sr.dispose();
	}

}
