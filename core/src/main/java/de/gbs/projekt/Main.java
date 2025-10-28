package de.gbs.projekt;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import de.gbs.projekt.Handlers.DrawHandler;
import de.gbs.projekt.Handlers.InputHandler;
import de.gbs.projekt.Handlers.LogicHandler;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
    public class Main extends ApplicationAdapter {


        public final InputHandler inputHandler = new InputHandler();
        public final LogicHandler logicHandler = new LogicHandler();
        public final DrawHandler drawHandler = new DrawHandler();

        @Override
        public void resize(int width, int height) {
            drawHandler.getViewport().update(width, height, true); // true centers the camera
        }


        @Override
        public void create() {
            //Vorbereitungen in den Konstruktoren der Handler
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
    public void dispose() {
            drawHandler.disposeAll();

    }
}
