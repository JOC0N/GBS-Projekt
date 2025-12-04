package de.gbs.projekt.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Obstacle extends GameObject {

    public Obstacle(float x, float y, float width, float height) {
        super(x, y, width, height, width, height);
        this.boundsH = new Rectangle(x, y, width, height);
    }

    @Override
    public void update(float delta) {
        // Obstacle bewegt sich nicht
    }

    @Override
    public void render(SpriteBatch batch) {
        // Obstacle wird nicht gezeichnet (unsichtbare Kollisionsbox)
    }

    @Override
    public void dispose() {
        // Keine Ressourcen zum Freigeben
    }
}
