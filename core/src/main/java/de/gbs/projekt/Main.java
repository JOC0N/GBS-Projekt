package de.gbs.projekt;


import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.utils.ScreenUtils;

import de.gbs.projekt.Handlers.DrawHandler;
import de.gbs.projekt.Handlers.InputHandler;
import de.gbs.projekt.Handlers.LogicHandler;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {


    public InputHandler inputHandler;
    public LogicHandler logicHandler;
    public DrawHandler drawHandler;



    @Override
    public void create() {
        inputHandler = new InputHandler();
        logicHandler = new LogicHandler();
        drawHandler = new DrawHandler();
    }

    @Override
    public void render() {
        //Every Frame refresh screen
        ScreenUtils.clear(0, 0, 0, 1);
        inputHandler.run();
        logicHandler.run();
        drawHandler.run();
    }

    @Override
    public void resize(int width, int height) {
        drawHandler.getViewport().update(width, height, true); // true centers the camera
    }

    @Override
    public void dispose() {
        drawHandler.disposeManager();

    }
}
