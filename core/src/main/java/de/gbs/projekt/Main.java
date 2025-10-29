package de.gbs.projekt;


import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.utils.ScreenUtils;

import de.gbs.projekt.Handlers.DrawHandler;
import de.gbs.projekt.Handlers.InputHandler;
import de.gbs.projekt.Handlers.LogicHandler;
import de.gbs.projekt.managers.GameObjectManager;
import de.gbs.projekt.objects.Player;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    public InputHandler inputHandler;
    public LogicHandler logicHandler;
    public DrawHandler drawHandler;

    public GameObjectManager objectManager;

    @Override
    public void create() {
        inputHandler = new InputHandler();
        logicHandler = new LogicHandler();
        drawHandler = new DrawHandler();

        drawHandler.create();

        objectManager = new GameObjectManager();

        logicHandler.setObjectManager(objectManager);

        Player player = new Player(10, 10);
        objectManager.addObject(player);

        inputHandler.setPlayer(player);



    }

    @Override
    public void render() {
        ScreenUtils.clear(255, 0, 0, 1);

        // Input verarbeiten
        inputHandler.run();

        // Objekte aktualisieren
        objectManager.update(com.badlogic.gdx.Gdx.graphics.getDeltaTime());

        // Logik aktualisieren
        logicHandler.run();

        // Objekte zeichnen
        drawHandler.run(objectManager);
    }

    @Override
    public void resize(int width, int height) {
        drawHandler.getViewport().update(width, height, true); // true centers the camera
    }

    @Override
    public void dispose() {
        drawHandler.dispose();
        objectManager.dispose();

    }
}
