package de.gbs.projekt.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import de.gbs.projekt.objects.Player;

import java.util.HashMap;

public class InputHandler {
    private LogicHandler logicHandler;
    private Player player;
    private HashMap<String, Boolean> keys;
    private boolean[] toggle;

    public void setLogicHandler(LogicHandler logicHandler) {
        this.logicHandler = logicHandler;
        keys = new HashMap<>();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void run() {

        if(keys == null) keys = new HashMap<>();

        keys.put("Debugger", Gdx.input.isKeyPressed(Input.Keys.HOME));
        keys.put("Quit", Gdx.input.isKeyPressed(Input.Keys.ESCAPE));

        keys.put("Up", Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP));
        keys.put("Down", Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN));
        keys.put("Left", Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT));
        keys.put("Right", Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT));

        keys.put("Interact", Gdx.input.isKeyPressed(Input.Keys.SPACE));
        keys.put("Sprint", Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT));

        // Numpad inputs for HP/MP control (use just-pressed to avoid rapid changes while holding)
        boolean np9 = Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_9);
        boolean np8 = Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_8);
        boolean np6 = Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_6);
        boolean np5 = Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_5);

        if(player == null)
            return;

        if(keys.get("Quit")) {
            Gdx.app.exit();
        }

        // apply numpad changes
        if(np9) player.decreaseHP(5); // NUMPAD 9 decreases HP by 5
        if(np8) player.increaseHP(5); // NUMPAD 8 increases HP by 5
        if(np6) player.decreaseMP(5); // NUMPAD 6 decreases MP by 5
        if(np5) player.increaseMP(5); // NUMPAD 5 increases MP by 5


        player.setSprint(keys.get("Sprint"));

        logicHandler.debugger(keys.get("Debugger"));


        player.setVelocityX(0);
        player.setVelocityY(0);

        if(keys.get("Left")) {
            player.setVelocityX(-1);
        }
        if(keys.get("Right")) {
            player.setVelocityX(1);
        }
        if(keys.get("Up")) {
            player.setVelocityY(1);
        }
        if(keys.get("Down")) {
            player.setVelocityY(-1);
        }


    }
}
