package de.gbs.projekt.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.gbs.projekt.objects.components.Movable;

public class Player extends GameObject implements Movable {
    private final Texture texture;
    private float speed;

    public Player(float x, float y) {
        super(x, y, 1, 1,1,1);
        texture = new Texture("player.png");
        speed = 5;
    }

    @Override
    public void update(float delta) {
        move(delta);
        this.setBoundsH(); // after move() for sync hitboxes
    }

    @Override
    public void render(SpriteBatch batch) {
        //draw player
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }


    @Override
    public void move(float delta) {
        float velocityLength = (float) Math.sqrt(getVelocityX() * getVelocityX() + getVelocityY() * getVelocityY());
        if (velocityLength > 0) {
            this.setX(getX() + getVelocityX()/velocityLength * getSpeed() * delta);
            this.setY(getY() + getVelocityY()/velocityLength * getSpeed() * delta);
        }
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    @Override
    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    @Override
    public float getVelocityX() {
        return velocityX;
    }

    @Override
    public float getVelocityY() {
        return velocityY;
    }


}
