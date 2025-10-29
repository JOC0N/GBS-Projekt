package de.gbs.projekt.Handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class DrawHandler {

    private final SpriteBatch batch;

    private OrthographicCamera camera;
    private final FitViewport viewport;
    private final float worldWidth = 80f;
    private final float worldHeight = 45f;



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
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);

        //Resources
        logo = new Texture("libgdx.png");
    }

    public void run() {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();



        batch.draw(logo, worldWidth/2, worldHeight/2, 1, 1);
        batch.end();
    }

    public void dispose() {

    }

    public void disposeManager() {
        batch.dispose();
        logo.dispose();
    }
}
