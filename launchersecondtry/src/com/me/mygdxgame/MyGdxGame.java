package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class MyGdxGame implements ApplicationListener {
	//Why isnt this working for owen
	OrthographicCamera cam;	
	Texture launcher;
	Texture bullet;
	Rectangle shooter;
	float x, y, angle, velocity, xVel, yVel, deltaTime;
	SpriteBatch batch;
	public void compute(){
		angle = (angle*(float)Math.PI)/180;
		xVel = (float)(Math.cos(angle)*velocity);
		yVel = (float)(Math.sin(angle)*velocity);		
	}
	public float x(float time){
		x = (float) ((xVel * time));
		return x;
	}
	public float y(float time){
		float z = yVel * time;
		float w = time * time;
		y = z - (4.9f * w);
		return y;
	}
	@Override
	public void create() {		
		angle = 70;
		launcher = new Texture(Gdx.files.internal("bucket.png"));
		bullet = new Texture(Gdx.files.internal("droplet.png"));
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 400, 800);
		batch = new SpriteBatch();
		shooter = new Rectangle();
		shooter.x = 0;
		shooter.y = 0;
		velocity = 70;
		compute();
		deltaTime = TimeUtils.nanoTime();
		
	}

	@Override
	public void dispose() {
		bullet.dispose();
		launcher.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(launcher, shooter.x, shooter.y);
        batch.draw(bullet, x((TimeUtils.nanoTime() - deltaTime)/1000000000), y((TimeUtils.nanoTime()-deltaTime)/1000000000));
        batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
