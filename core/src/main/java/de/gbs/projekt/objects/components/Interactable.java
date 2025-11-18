package de.gbs.projekt.objects.components;

import com.badlogic.gdx.math.Circle;

public interface Interactable {
    float getInteractionRadius();

    void setInteractionRadius(float interactionRadius);

    void setBoundsI(float centerPointX, float centerPointY, float interactionRadius);

    Circle getBoundsI();

    void updateBoundsI();
}
