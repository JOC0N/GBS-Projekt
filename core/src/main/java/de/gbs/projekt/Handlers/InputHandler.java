package de.gbs.projekt.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import de.gbs.projekt.managers.GameObjectManager;
import de.gbs.projekt.objects.Player;

import java.util.HashMap;

public class InputHandler {
    private LogicHandler logicHandler;
    private Player player;
    private float speed;
    private HashMap<String,Boolean> keys;
    private boolean[] toggle;

    public void setLogicHandler(LogicHandler logicHandler) {
        speed = 10;
        this.logicHandler = logicHandler;
        keys = new HashMap<>();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void run() {

        keys.put("Debugger", Gdx.input.isKeyPressed(Input.Keys.HOME));
        keys.put("Pause-Menu", Gdx.input.isKeyPressed(Input.Keys.ESCAPE));

        keys.put("Up", Gdx.input.isKeyPressed(Input.Keys.UP));
        keys.put("Down", Gdx.input.isKeyPressed(Input.Keys.DOWN));
        keys.put("Left", Gdx.input.isKeyPressed(Input.Keys.LEFT));
        keys.put("Right", Gdx.input.isKeyPressed(Input.Keys.RIGHT));

        keys.put("Interact", Gdx.input.isKeyPressed(Input.Keys.SPACE));

        if (player == null) return;

        if (keys.get("Debugger")) {
            logicHandler.debugger();

        }

        float velocityX = 0;
        float velocityY = 0;

        if (keys.get("Left")) {
            velocityX = -1;
        }
        if (keys.get("Right")) {
            velocityX = 1;
        }
        if (keys.get("Up")) {
            velocityY = 1;
        }
        if (keys.get("Down")) {
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
