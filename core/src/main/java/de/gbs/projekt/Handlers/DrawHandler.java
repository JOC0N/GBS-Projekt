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

public class DrawHandler {
    public boolean showHitbox;
    public boolean showSpeed;
    public boolean showDetectionRadius;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    public DrawHandler() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(16, 9, camera);

        //Alpha Renderer für debugging hitboxes
        shapeRenderer = new ShapeRenderer();
        //Font for debugging
        font = new BitmapFont();
        font.getData().setScale(0.1f);

    }


    public void run(GameObjectManager objectManager) {
        Player player = objectManager.getPlayer();
        if (player != null) {
            camera.position.set(player.getCenterPointX(), player.getCenterPointY(),0);
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

        // Draw all GameObjects
        objectManager.render(batch);

        //debugger velocity
        if(showSpeed){
            assert player != null;
            font.draw(batch,
                "Vel: "
                + Float.toString(player.getVelocityX()) +" "+ Float.toString(player.getVelocityY()),
                player.getX(), player.getY());

        }
        batch.end();
        //hitbox renderer for debugging
        if (showHitbox && showDetectionRadius){
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(new Color(1,0,0,0.5f));
            for (GameObject obj : objectManager.getObjects()) {
                if (obj instanceof Detectable) {
                    Circle boundsD =((Detectable) obj).getBoundsD();
                     shapeRenderer.circle(obj.getCenterPointX(), obj.getCenterPointY(), ((Detectable) obj).getDetectionRadius());
                    // System.out.println("Objekt: " + obj.getClass().getSimpleName() +        ", ist Detectable: " + (obj instanceof Detectable));
                }
                Rectangle boundsH = obj.getBoundsH();
                shapeRenderer.rect(boundsH.x, boundsH.y, boundsH.getWidth(), boundsH.getHeight());
            }
            shapeRenderer.end();
        }


    }

    public FitViewport getViewport() {
        return viewport;
    }

    public void dispose() {
        if (batch != null) batch.dispose();
    }

}
