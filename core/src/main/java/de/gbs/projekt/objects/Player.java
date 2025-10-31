package de.gbs.projekt.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.gbs.projekt.objects.components.Movable;

public class Player extends GameObject implements Movable {
    private Texture texture;


    public Player(float x, float y) {
        super(x, y, 1, 1);
        texture = new Texture("player.png");

    }

    @Override
    public void update(float delta) {
        this.getBounds();
        // Spieler-Logik aktualisieren (z. B. Bewegung)
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

    @Override
    public void move(float x, float y) {

    }
}
