package de.gbs.projekt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import de.gbs.projekt.Handlers.DrawHandler;
import de.gbs.projekt.Handlers.InputHandler;
import de.gbs.projekt.Handlers.LogicHandler;
import de.gbs.projekt.managers.GameObjectManager;
import de.gbs.projekt.objects.Dummy;
import de.gbs.projekt.objects.Player;


/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    public float delta;
    public InputHandler inputHandler;
    public LogicHandler logicHandler;
    public DrawHandler drawHandler;

    public GameObjectManager objectManager;

    @Override
    public void create() {
        objectManager = new GameObjectManager();

        inputHandler = new InputHandler();
        logicHandler = new LogicHandler();
        drawHandler = new DrawHandler();

        inputHandler.setLogicHandler(logicHandler);
        logicHandler.setDrawHandler(drawHandler);
        logicHandler.setObjectManager(objectManager);
        drawHandler.setObjectManager(objectManager);


        Dummy dummy2 = new Dummy(15,1);
        objectManager.addObject(dummy2);

        Player player = new Player(10, 1, 4, 1.5f);
        objectManager.addObject(player);
        inputHandler.setPlayer(player);

        Player player2 = new Player(1, 1, 4, 1.5f);
        objectManager.addObject(player2);

        Dummy dummy0 = new Dummy(-4, -4);
        objectManager.addObject(dummy0);

    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0,0);
        delta = com.badlogic.gdx.Gdx.graphics.getDeltaTime();

        inputHandler.run();
        objectManager.update(delta);
        logicHandler.run();
        drawHandler.run();
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
