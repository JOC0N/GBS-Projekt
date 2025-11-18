package de.gbs.projekt.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//just an GameObj with nothing more. Just a Hitbox with coords.
public class Dummy extends GameObject{

    public Dummy(float x, float y) {
        super(x, y, 1, 1, 1, 1);

    }

    @Override
    public void update(float delta) {
        //404 Am I insane?
    }

    @Override
    public void render(SpriteBatch batch) {
        //404 WTF WDYM: you wanna render?
    }
}
