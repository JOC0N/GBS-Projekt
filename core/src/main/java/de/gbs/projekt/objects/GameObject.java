package de.gbs.projekt.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
    protected float x, y;
    protected float velocityX, velocityY;
    protected float width, height;
    protected Rectangle bounds;

    public GameObject(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

    public Rectangle getBounds() {
        bounds.setPosition(x, y);
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

}
