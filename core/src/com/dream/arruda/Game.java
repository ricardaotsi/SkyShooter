package com.dream.arruda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Game extends com.badlogic.gdx.Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public int width;
	public int height;
	public float density;
	public OrthographicCamera camera;
	public boolean gameover;
	public boolean backpressed;


	@Override
	public void create () {
		batch=new SpriteBatch();
		width=640;
		height=360;
		density=Gdx.graphics.getWidth()/width;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("sans.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = width/13;
		font = generator.generateFont(parameter);
		camera = new OrthographicCamera();
		gameover=false;
		backpressed=false;
		Gdx.input.setCatchBackKey(true);
		backpressed=false;
		this.setScreen(new MenuView(this));
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
		this.getScreen().resume();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		this.getScreen().dispose();
	}

}
