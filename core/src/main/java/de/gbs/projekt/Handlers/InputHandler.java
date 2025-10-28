package de.gbs.projekt.Handlers;

import com.badlogic.gdx.Gdx;

public class InputHandler {
    private float delta;

    public InputHandler() {
        delta = Gdx.graphics.getDeltaTime();
    }

    public void run(){

    }

    public float getDelta() {
        return delta;
    }

}
