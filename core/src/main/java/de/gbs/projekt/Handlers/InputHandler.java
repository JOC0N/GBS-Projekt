package de.gbs.projekt.Handlers;

import com.badlogic.gdx.Gdx;

public class InputHandler {
    private final float delta;

    public InputHandler() {
        delta = Gdx.graphics.getDeltaTime();
    }

    public void run(){
        //no input yet
    }

    public float getDelta() {
        return delta;
    }

}
