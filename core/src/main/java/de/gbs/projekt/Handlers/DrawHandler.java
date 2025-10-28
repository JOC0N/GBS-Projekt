package de.gbs.projekt.Handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class DrawHandler {

    private final SpriteBatch batch;
    private final FitViewport viewport;
    private final Texture logo;

    public Texture getLogo() {
        return logo;
    }

    public FitViewport getViewport() {
        return viewport;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public DrawHandler() {
        batch = new SpriteBatch();
        viewport = new FitViewport(80, 60);

        //Resources
        logo = new Texture("libgdx.png");
    }

    public void run() {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        batch.draw(logo, 0, 0, worldWidth, worldHeight);
        batch.end();
    }

    public void dispose() {

    }

    public void disposeManager() {
        batch.dispose();
        logo.dispose();
    }
}
