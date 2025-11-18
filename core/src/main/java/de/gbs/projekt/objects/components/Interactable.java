package de.gbs.projekt.objects.components;

import com.badlogic.gdx.math.Circle;

public interface Interactable {
    public float getInteractionRadius();
    public void setInteractionRadius(float interactionRadius);
    public void setBoundsI (float centerPointX, float centerPointY, float interactionRadius);
    public Circle getBoundsI();
    public void updateBoundsI();
}
