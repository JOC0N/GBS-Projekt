package de.gbs.projekt.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import de.gbs.projekt.objects.Player;

public class InputHandler {
    private Player player;
    private float speed = 200; // Bewegungsgeschwindigkeit in Pixeln pro Sekunde

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void run() {
        if (player == null) return;


        float velocityX = 0;
        float velocityY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocityX = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocityX = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            velocityY = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            velocityY = -1;
        }

        // Normalisierung für diagonale Bewegung (optional)
        if (velocityX != 0 && velocityY != 0) {
            velocityX *= 0.7071F; // 1 / sqrt(2)
            velocityY *= 0.7071F;
        }

        player.setX(player.getX() + velocityX * speed * Gdx.graphics.getDeltaTime());
        player.setY(player.getY() + velocityY * speed * Gdx.graphics.getDeltaTime());
    }
}
