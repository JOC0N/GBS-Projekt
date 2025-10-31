package de.gbs.projekt.objects.components;

public interface Movable {
    void move(float x, float y);
    float getVelocityX();
    float getVelocityY();
    float[] getVelocity();
}
