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

    public DrawHandler() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(16, 9, camera);
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        font.getData().setScale(0.1f);
    }

    public void run(GameObjectManager objectManager) {
        Player player = objectManager.getPlayer();
        if(player != null) {
            camera.position.set(player.getCenterPointX(), player.getCenterPointY(), 0);
            camera.update();
        }
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        Texture block = new Texture("floor.png");
        for(int wx = 0; wx < 1010; wx++) {
            for(int wy = 0; wy < 1010; wy++) {
                batch.draw(block, wx, wy, 1, 1);
            }
        }

        objectManager.render(batch);
        debugSprites(player);
        batch.end();
        debugRenderer(objectManager);
    }

    public FitViewport getViewport() {
        return viewport;
    }

    public void dispose() {
        if(batch != null)
            batch.dispose();
    }

    public void debugSprites(Player player) {
        if(showSpeed) {
            assert player != null;
            font.draw(batch, "Vel: " + Float.toString(player.getVelocityX()) + " " + Float.toString(player.getVelocityY()), player.getX(), player.getY());
        }
    }

    public void debugRenderer(GameObjectManager objectManager) {
        if(showHitbox && showDetectionRadius && showInteractionRadius) {
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
}
