package de.gbs.projekt.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import de.gbs.projekt.objects.Player;
import de.gbs.projekt.stats.EntityStats;

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
        keys.put("Debug1", Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_5));
        keys.put("Debug2", Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_6));
        keys.put("Debug3", Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_8));
        keys.put("Debug4", Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_9));

        keys.put("Quit", Gdx.input.isKeyPressed(Input.Keys.ESCAPE));

        keys.put("Up", Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP));
        keys.put("Down", Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN));
        keys.put("Left", Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT));
        keys.put("Right", Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT));

        keys.put("Interact", Gdx.input.isKeyPressed(Input.Keys.SPACE));
        keys.put("Sprint", Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT));


        if(player == null)
            return;

        if(keys.get("Quit")) {
            Gdx.app.exit();
        }

        // apply numpad changes
        if(keys.get("Debug3")) player.changeStat(EntityStats.HEALTH,-50);
        if(keys.get("Debug4")) player.changeStat(EntityStats.HEALTH, 50);
        if(keys.get("Debug1")) player.changeStat(EntityStats.MANA,-5);
        if(keys.get("Debug2")) player.changeStat(EntityStats.MANA, 5);


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
