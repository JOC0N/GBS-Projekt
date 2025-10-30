package de.gbs.projekt.Handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import de.gbs.projekt.managers.GameObjectManager;
import de.gbs.projekt.objects.GameObject;
import de.gbs.projekt.objects.Player;

public class DrawHandler {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;

    public DrawHandler() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(80, 45, camera);
        batch = new SpriteBatch();
    }


    public void run(GameObjectManager objectManager) {
        Player player = objectManager.getPlayer();
        if (player != null) {
            camera.position.set(player.getX() + player.getWidth() / 2,
                player.getY() + player.getHeight() / 2,
                0);
            camera.update();
        }
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        Texture block = new Texture("floor.png");
        for (int wx = 0; wx < 1010; wx++) {
            for (int wy = 0; wy < 1010; wy++) {
                batch.draw(block, wx, wy, 1, 1);
            }
        }


        // Zeichne alle GameObjects
        objectManager.render(batch);

        batch.end();
    }

    public FitViewport getViewport() {
        return viewport;
    }

    public void dispose() {
        if (batch != null) batch.dispose();
    }

}
