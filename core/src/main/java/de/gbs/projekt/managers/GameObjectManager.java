package de.gbs.projekt.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import de.gbs.projekt.objects.GameObject;
import de.gbs.projekt.objects.Player;
import de.gbs.projekt.objects.Obstacle;

public class GameObjectManager {
    private final Array<GameObject> objects;

    public GameObjectManager() {
        objects = new Array<>();
    }

    // Füge ein neues Objekt hinzu
    public void addObject(GameObject object) {
        objects.add(object);
    }

    // Entferne ein Objekt
    public void removeObject(GameObject object) {
        objects.removeValue(object, true);
    }

    // Aktualisiere alle Objekte
    public void update(float delta) {
        for(GameObject obj : objects) {
            obj.update(delta);
        }

        // Kollisionen prüfen und beheben
        Player player = getPlayer();
        if(player != null) {
            for (GameObject obj : objects) {
                if (!(obj instanceof Player) && obj instanceof Obstacle) {
                    if (checkCollision(player.getBoundsH(), obj.getBoundsH())) {
                        player.revertMovement();
                        player.setBoundsH();
                    }
                }
            }
        }
    }

    // Zeichne alle Objekte
    public void render(SpriteBatch batch) {
        for(GameObject obj : objects) {
            obj.render(batch);
        }
    }

    // Gib alle Ressourcen frei
    public void dispose() {
        for(GameObject obj : objects) {
            obj.dispose();
        }
        objects.clear();
    }

    // Prüfe Kollisionen zwischen zwei Objekten
    public boolean checkCollision(Rectangle a, Rectangle b) {
        return a.overlaps(b);
    }

    // Gib alle Objekte zurück
    public Array<GameObject> getObjects() {
        return objects;
    }

    public Player getPlayer() {
        for(GameObject obj : objects) {
            if(obj instanceof Player) {
                return (Player) obj;
            }
        }
        return null;
    }


}
