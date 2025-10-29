package de.gbs.projekt.Handlers;

import de.gbs.projekt.managers.GameObjectManager;

public class LogicHandler {
    private GameObjectManager objectManager;

    public void setObjectManager(GameObjectManager objectManager) {
        this.objectManager = objectManager;
    }

    public void run() {
        // Beispiel: Kollisionen prüfen
        for (int i = 0; i < objectManager.getObjects().size; i++) {
            for (int j = i + 1; j < objectManager.getObjects().size; j++) {
                if (objectManager.checkCollision(
                    objectManager.getObjects().get(i),
                    objectManager.getObjects().get(j))) {
                    // Kollision behandelt
                }
            }
        }
    }
}
