package de.gbs.projekt.objects.components;

public interface Movable {
    void move(float x, float y);
    void setVelocityX(float velocityX);
    void setVelocityY(float velocityY);

    float getVelocityX();
    float getVelocityY();

}
