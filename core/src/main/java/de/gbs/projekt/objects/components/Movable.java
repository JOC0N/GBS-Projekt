package de.gbs.projekt.objects.components;

public interface Movable {

    void setSpeed(float speed);
    float getSpeed();

    void move(float delta);

    void setVelocityX(float velocityX);
    void setVelocityY(float velocityY);
    float getVelocityX();
    float getVelocityY();




}
