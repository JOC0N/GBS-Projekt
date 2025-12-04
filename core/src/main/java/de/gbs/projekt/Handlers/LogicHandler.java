package de.gbs.projekt.Handlers;

import de.gbs.projekt.managers.GameObjectManager;
import de.gbs.projekt.objects.GameObject;

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
        if(objectManager == null) return;
        for(int i = 0; i < objectManager.getObjects().size; i++) {
            GameObject a = objectManager.getObjects().get(i);
            for(int j = i + 1; j < objectManager.getObjects().size; j++) {
                GameObject b = objectManager.getObjects().get(j);
                if(objectManager.checkCollision(a.getBoundsH(), b.getBoundsH())) {
                    System.out.println("crazy");
                }
            }
        }
    }



    public void debugger(boolean state) {
        if(drawHandler == null) return;
        drawHandler.showHitbox = state;
        drawHandler.showDetectionRadius = state;
        drawHandler.showInteractionRadius = state;
        drawHandler.showSpeed = state;
    }
}
