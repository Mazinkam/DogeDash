package com.alicode.game.dogedash.worlds.effects;

import com.alicode.game.dogedash.Assets;
import com.alicode.game.dogedash.Statics;
import com.alicode.game.dogedash.models.MotherDoge;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class WorldTwoLight extends Actor {

	private TextureRegion gameLight;
	private FrameBuffer frameBuff;
	private ShaderProgram finalShader;

	// values passed to the shader
	public static final float ambientIntensity = 0.5f;
	public static final Vector3 ambientColor = new Vector3(0.4f, 0.4f, 0.5f);

	// used to make the light flicker
	public float zAngle;
	public static final float zSpeed = 15.0f;
	public static final float PI2 = (float) (Math.PI * 2.0f);
	private float x, y;
	private float lightSize;

	final String vertexShader = Gdx.files.internal("shaders/vertexShader.glsl").readString();
	final String finalPixelShader = Gdx.files.internal("shaders/pixelShader.glsl").readString();

	public WorldTwoLight() {
		ShaderProgram.pedantic = false;
		finalShader = new ShaderProgram(vertexShader, finalPixelShader);

		frameBuff = new FrameBuffer(Format.RGBA8888, 800, 480, false);

		finalShader.begin();
		finalShader.setUniformf("resolution", 800, 480);
		finalShader.end();

		finalShader.begin();
		finalShader.setUniformi("u_lightmap", 1);
		finalShader.setUniformf("ambientColor", ambientColor.x, ambientColor.y, ambientColor.z, ambientIntensity);
		finalShader.end();

		gameLight = new TextureRegion(Assets.gameLight);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		updatePositon();
	}

	private void updatePositon() {
		if ((MotherDoge.playerX + Assets.character.getRegionWidth() / 2 )- lightSize /2 > x)
			x++;
		else
			x--;

		if ((MotherDoge.playerY + Assets.character.getRegionHeight() / 2) - lightSize / 2 > y)
			y += 3;
		else
			y -= 3;

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		final float dt = Gdx.graphics.getRawDeltaTime();
		zAngle += dt * zSpeed;
		while (zAngle > PI2)
			zAngle -= PI2;

		// draw the light to the FBO
		frameBuff.begin();
		batch.setShader(finalShader);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.end();
		batch.begin();
		lightSize = Statics.playerVisionRadius + 0.25f * (float) Math.sin(zAngle) + 0.2f * MathUtils.random();

		batch.draw(gameLight, x, y, lightSize, lightSize);

		batch.end();
		frameBuff.end();

		// draw the actual scene
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setShader(finalShader);
		batch.begin();
		frameBuff.getColorBufferTexture().bind(1); // this is important! bind
													// the FBO to the 2nd
													// texture unit
		gameLight.getTexture().bind(0);

	}

}