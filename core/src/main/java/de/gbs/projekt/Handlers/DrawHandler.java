package de.gbs.projekt.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import de.gbs.projekt.managers.GameObjectManager;
import de.gbs.projekt.objects.GameObject;
import de.gbs.projekt.objects.Obstacle;
import de.gbs.projekt.objects.Player;
import de.gbs.projekt.objects.components.Detectable;
import de.gbs.projekt.objects.components.Interactable;

public class DrawHandler {
    public boolean showHitbox;
    public boolean showSpeed;
    public boolean showDetectionRadius;
    public boolean showInteractionRadius;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private GameObjectManager objectManager;
    private Texture floorTexture;
    // Camera used for pixel-precise HUD rendering
    private OrthographicCamera hudCamera;

    public DrawHandler() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.zoom = 1;
        viewport = new FitViewport(16, 9, camera);
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        font.getData().setScale(0.1f);
        floorTexture = new Texture("floor.png");

        // HUD camera initialisation (pixel coordinates)
        hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void run() {
        Player player = objectManager.getPlayer();
        if(player != null) {
            camera.position.set(player.getCenterPointX(), player.getCenterPointY(), 0);

            camera.update();
        }
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // determine visible tile range based on camera and viewport
        float halfWidth = viewport.getWorldWidth() / 2f;
        float halfHeight = viewport.getWorldHeight() / 2f;
        float left = camera.position.x - halfWidth - 1;
        float right = camera.position.x + halfWidth + 1;
        float bottom = camera.position.y - halfHeight - 1;
        float top = camera.position.y + halfHeight + 1;

        int startX = (int)Math.floor(left);
        int endX = (int)Math.ceil(right);
        int startY = (int)Math.floor(bottom);
        int endY = (int)Math.ceil(top);

        for(int wx = startX; wx <= endX; wx++) {
            for(int wy = startY; wy <= endY; wy++) {
                batch.draw(floorTexture, wx, wy, 1, 1);
            }
        }

        objectManager.render(batch);
        debugSprites(player);
        batch.end();

        // Render obstacle collision boxes as filled black rectangles
        renderObstacles(objectManager);

        debugRenderer(objectManager);

        // Render HUD last so it stays on the topmost layer: health (red) and mana (blue) bars in the top-left of the window (pixel-perfect)
        if(player != null) {
            // update hud camera to current window size in case of resize
            hudCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            hudCamera.update();

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

            // switch to HUD (pixel) projection
            shapeRenderer.setProjectionMatrix(hudCamera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            // HUD layout in pixels
            float margin = 10f; // pixels from left/top
            float maxBarWidth = 150f; // pixels for 100 value
            float barHeight = 16f; // pixels
            // top-left in pixel coords (origin bottom-left)
            float hudStartX = margin;
            float hudStartY = Gdx.graphics.getHeight() - margin - barHeight; // top bar (HP)

            // background bars (dark grey)
            shapeRenderer.setColor(new Color(0.15f, 0.15f, 0.15f, 0.9f));
            shapeRenderer.rect(hudStartX, hudStartY, maxBarWidth, barHeight);
            shapeRenderer.rect(hudStartX, hudStartY - (barHeight + 6f), maxBarWidth, barHeight); // MP below HP with 6px gap

            // actual bars based on player values
            float hpFraction = player.getHP() / 100f;
            float mpFraction = player.getMP() / 100f;

            shapeRenderer.setColor(new Color(1f, 0f, 0f, 1f)); // red
            shapeRenderer.rect(hudStartX, hudStartY, maxBarWidth * hpFraction, barHeight);

            shapeRenderer.setColor(new Color(0f, 0f, 1f, 1f)); // blue
            shapeRenderer.rect(hudStartX, hudStartY - (barHeight + 6f), maxBarWidth * mpFraction, barHeight);

            shapeRenderer.end();
        }
    }

    public FitViewport getViewport() {
        return viewport;
    }

    public void dispose() {
        if(batch != null)
            batch.dispose();
        if(floorTexture != null)
            floorTexture.dispose();
        if(shapeRenderer != null)
            shapeRenderer.dispose();
        if(font != null)
            font.dispose();
    }

    public void debugSprites(Player player) {
        if(showSpeed) {
            assert player != null;

            font.draw(batch, " " + Math.round(player.getX()), player.getX(), player.getY());
            font.draw(batch, " " + Math.round(player.getY()), player.getX(), player.getY()-1);
        }
    }

    public void renderObstacles(GameObjectManager objectManager) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 0, 1f)); // Black, fully opaque

        for(GameObject obj : objectManager.getObjects()) {
            if(obj instanceof Obstacle) {
                Rectangle boundsH = obj.getBoundsH();
                shapeRenderer.rect(boundsH.x, boundsH.y, boundsH.getWidth(), boundsH.getHeight());
            }
        }
        shapeRenderer.end();
    }

    public void debugRenderer(GameObjectManager objectManager) {
        if(showHitbox || showDetectionRadius || showInteractionRadius) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(new Color(1, 0, 0, 0.5f));

            for(GameObject obj : objectManager.getObjects()) {

                if(obj instanceof Interactable) {
                    shapeRenderer.setColor(new Color(0, 0, 1, 0.5f));
                    Circle boundsI = ((Interactable) obj).getBoundsI();
                    shapeRenderer.circle(boundsI.x, boundsI.y, boundsI.radius, 32);
                }

                if(obj instanceof Detectable) {
                    shapeRenderer.setColor(new Color(0, 1, 0, 0.5f));
                    Circle boundsD = ((Detectable) obj).getBoundsD();
                    // WTF Warum nicht:
                    shapeRenderer.circle(boundsD.x, boundsD.y, boundsD.radius, 32);
                    // System.out.println("Objekt: " + obj.getClass().getSimpleName() +        ", ist Detectable: " + (obj instanceof Detectable));
                }

                shapeRenderer.setColor(new Color(1, 0, 0, 0.5f));
                Rectangle boundsH = obj.getBoundsH();
                shapeRenderer.rect(boundsH.x, boundsH.y, boundsH.getWidth(), boundsH.getHeight());
            }
            shapeRenderer.end();
        }
    }

    public void setObjectManager(GameObjectManager objectManager) {
        this.objectManager = objectManager;
    }
}
