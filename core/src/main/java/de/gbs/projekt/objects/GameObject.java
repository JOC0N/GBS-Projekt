package de.gbs.projekt.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
    protected float x, y;
    protected float velocityX, velocityY;
    protected float width, height;
    protected float hitboxWidth, hitboxHeight;
    protected Rectangle bounds;

    public float getHitboxWidth() {
        return hitboxWidth;
    }

    public float getHitboxHeight() {
        return hitboxHeight;
    }

    public GameObject(float x, float y, float width, float height, float hitboxWidth, float hitboxHeight) {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;

        this.width = width;
        this.height = height;

        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;

        this.bounds = new Rectangle(
            (this.x + this.width / 2) - this.hitboxWidth / 2,
            (this.y + this.height / 2) - this.hitboxHeight / 2,
            this.hitboxWidth, this.hitboxHeight);
    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

    public Rectangle getBounds() {
        bounds.setPosition((x + width / 2) - hitboxWidth / 2,
            (y + height / 2) - hitboxHeight / 2);
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
    }

    public void setX(float x) {
        this.x = x;
    }

}
