package de.gbs.projekt.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
    protected float x, y;
    protected float centerPointX, centerPointY;
    protected float velocityX, velocityY;
    protected float width, height;
    protected float hitboxWidth, hitboxHeight;
    protected float detectionWidth, detectionHeight;

    protected Rectangle bounds;
    protected Rectangle boundsd;

    public GameObject(float x, float y,
                      float width, float height,
                      float hitboxWidth, float hitboxHeight,
                      float detectionWidth, float detectionHeight) {
        this.x = x;
        this.y = y;

        this.velocityX = 0;
        this.velocityY = 0;

        this.width = width;
        this.height = height;

        this.centerPointX =x + this.width/2;
        this.centerPointY =y + this.height/2;

        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;

        this.detectionWidth = detectionWidth;
        this.detectionHeight = detectionHeight;

        this.bounds = new Rectangle(
            centerPointX - this.hitboxWidth / 2,
            centerPointY - this.hitboxHeight / 2,
            this.hitboxWidth, this.hitboxHeight);

    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

    public Rectangle getBounds() {
        bounds.setPosition(centerPointX - hitboxWidth / 2,
            centerPointY - hitboxHeight / 2);
        return bounds;
    }

    public void dispose() {
        // Ressourcen freigeben
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        this.centerPointY = y + this.height / 2;
    }

    public void setX(float x) {
        this.x = x;
        this.centerPointX = x + this.width / 2;
    }

}
