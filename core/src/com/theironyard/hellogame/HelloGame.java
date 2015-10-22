package com.theironyard.hellogame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	//we use floats not doubles in game development because they take up less space
    float x = 0;
    float y = 0;
    float xVelocity = 0;
    float yVelocity = 0;

    final float MAX_VELOCITY = 100; //final because it's a constant
	
	@Override
	public void create () { //create method only runs once, right when it boots up. this is where you would generate things
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () { //render method is going to be called like 60 times/second. think of it as a flipbook and it's drawing a whole bunch on images really quickly.
		move();

        Gdx.gl.glClearColor(1, 0, 0, 1); //drawing a background color (red, green, blue, alpha(transparency)) -> this is a non transparent red
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //completely clearing whatever was drawn previously. -> turning it black and letting you redraw
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}

    void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) { //it takes an integer as the key which will represent the UP arrow
            yVelocity = MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) { //if we do if, else if, else if...etc, we could not push the keys at the same time. so 4 if statements are needed
            yVelocity = MAX_VELOCITY * -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xVelocity = MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xVelocity = MAX_VELOCITY * -1;
        }

        x += xVelocity * Gdx.graphics.getDeltaTime(); //makes it move but takes into account pixel refresh rate
        y += yVelocity * Gdx.graphics.getDeltaTime();

        xVelocity *= 0.9; //dampening
        yVelocity *= 0.9;
    }
}
