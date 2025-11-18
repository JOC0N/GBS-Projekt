package de.gbs.projekt.objects.components;

import com.badlogic.gdx.math.Circle;

public interface Detectable {
    float getDetectionRadius();

    void setDetectionRadius(float detectionRadius);

    void setBoundsD(float centerPointX, float centerPointY, float detectionRadius);

    Circle getBoundsD();

    void updateBoundsD();
}
