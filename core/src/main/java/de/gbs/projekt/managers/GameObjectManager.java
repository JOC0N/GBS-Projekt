package de.gbs.projekt.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import de.gbs.projekt.objects.GameObject;
import de.gbs.projekt.objects.Player;

public class GameObjectManager {
    private Array<GameObject> objects;

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
        for (GameObject obj : objects) {
            obj.update(delta);
        }
    }

    // Zeichne alle Objekte
    public void render(SpriteBatch batch) {
        for (GameObject obj : objects) {
            obj.render(batch);
        }
    }

    // Gib alle Ressourcen frei
    public void dispose() {
        for (GameObject obj : objects) {
            obj.dispose();
        }
        objects.clear();
    }

    // Prüfe Kollisionen zwischen zwei Objekten
    public boolean checkCollision(GameObject a, GameObject b) {
        return a.getBounds().overlaps(b.getBounds());
    }

    // Gib alle Objekte zurück
    public Array<GameObject> getObjects() {
        return objects;
    }

    public Player getPlayer() {
        return objects.first();
    }
}
