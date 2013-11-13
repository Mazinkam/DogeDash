//package com.alicode.game.dogedash.screens;
//
//import com.alicode.game.dogedash.Assets;
//import com.alicode.game.dogedash.DogeDashCore;
//import com.alicode.game.dogedash.utils.World;
//import com.alicode.game.dogedash.utils.WorldRenderer;
//import com.badlogic.gdx.Screen;
//
//public class GameScreen implements Screen {
//
//	DogeDashCore game;
//	World world;
//	WorldRenderer render;
////
//	public GameScreen(DogeDashCore game) {
//		this.game = game;
//		world = new World(game);
//		render = new WorldRenderer(world);
//	}
//
//	@Override
//	public void render(float delta) {
//		world.update();
//		render.render();
//	}
//
//	@Override
//	public void resize(int width, int height) {
//
//	}
//
//	@Override
//	public void show() {
//
//	}
//
//	@Override
//	public void hide() {
//		dispose();
//	}
//
//	@Override
//	public void pause() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void resume() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void dispose() {
//		world.dispose();
//		render.dispose();
//	}
//
//}
