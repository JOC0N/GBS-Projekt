package de.gbs.projekt.objects.components;

import com.badlogic.gdx.math.Circle;

public interface Detectable {
    public float getDetectionRadius();
    public void setDetectionRadius(float detectionRadius);
    public void setBoundsD (float centerPointX, float centerPointY, float detectionRadius);
    public Circle getBoundsD();
    public void updateBoundsD();
}
