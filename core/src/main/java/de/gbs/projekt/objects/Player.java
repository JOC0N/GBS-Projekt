package de.gbs.projekt.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends GameObject {
    private Texture texture;

    public Player(float x, float y) {
        super(x, y, 1, 1);
        texture = new Texture("player.png");
    }

    @Override
    public void update(float delta) {
        // Spieler-Logik aktualisieren (z. B. Bewegung)
        x = x +1;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    // Getter und Setter für x und y
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
