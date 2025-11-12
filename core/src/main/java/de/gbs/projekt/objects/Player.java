package de.gbs.projekt.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.gbs.projekt.objects.components.Movable;

public class Player extends GameObject implements Movable {
    private Texture texture;
    private final float speed;

    public Player(float x, float y) {
        super(x, y, 1, 1,1,1);
        texture = new Texture("player.png");
        speed = 5;
    }

    @Override
    public void update(float delta) {
        // 1. Geschwindigkeitsvektor berechnen
        float velocityLength = (float) Math.sqrt(velocityX * velocityX + velocityY * velocityY);

        // 2. Normalisieren (falls der Vektor nicht null ist)
        if (velocityLength > 0) {
            float normalizedVelocityX = (velocityX / velocityLength)*speed;
            float normalizedVelocityY = (velocityY / velocityLength)*speed;

            this.move(x + normalizedVelocityX * delta, y + normalizedVelocityY * delta);
        }
        this.setBoundsH(); // after move()
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

}
