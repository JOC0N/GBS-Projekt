package de.gbs.projekt.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.gbs.projekt.objects.components.Movable;

public class Player extends GameObject implements Movable {
    private Texture texture;
    private final float speed;

    public Player(float x, float y) {
        super(x, y, 1, 1,2,2, 3, 3);
        texture = new Texture("player.png");
        speed = 5;
    }

    @Override
    public void update(float delta) {
        this.getBounds();
        if (velocityX != 0 && velocityY != 0) {
            velocityX *= 0.7071F; // 1 / sqrt(2)
            velocityY *= 0.7071F;
        }
        this.move(x + velocityX * speed * delta , y + velocityY * speed * delta);

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void move(float x, float y) {
        this.setX(x);
        this.setY(y);
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

    @Override
    public float[] getVelocity() {
        float[] velocity = new float[2];
        velocity[0] = velocityX;
        velocity[1] = velocityY;
        return velocity;
    }
}
