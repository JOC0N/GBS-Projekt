package de.gbs.projekt.objects.components;

public interface Movable {


    void setSprint(boolean sprint);

    boolean getSprint();

    void move(float delta);

    void setVelocityX(float velocityX);

    void setVelocityY(float velocityY);

    float getVelocityX();

    float getVelocityY();


}
