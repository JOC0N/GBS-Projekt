package de.gbs.projekt.Handlers;

import de.gbs.projekt.managers.GameObjectManager;

public class LogicHandler {
    private GameObjectManager objectManager;
    private DrawHandler drawHandler;

    public void setObjectManager(GameObjectManager objectManager) {
        this.objectManager = objectManager;
    }
    public void setDrawHandler(DrawHandler drawHandler) {
        this.drawHandler = drawHandler;
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
    public void debugger(boolean state) {
        drawHandler.showHitbox = state;
        drawHandler.showDetectionRadius = state;
        drawHandler.showSpeed = state;
    }
}
