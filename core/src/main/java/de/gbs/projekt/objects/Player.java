package de.gbs.projekt.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import de.gbs.projekt.objects.components.Detectable;
import de.gbs.projekt.objects.components.Interactable;
import de.gbs.projekt.objects.components.Movable;

public class Player extends GameObject implements Movable, Detectable, Interactable {
    private final Texture texture;
    private float speed;
    private float sprintSpeed;
    private boolean sprint;
    private float detectionRadius;
    private float interactionRadius;
    private Circle boundsD;
    private Circle boundsI;

    public Player(float x, float y, float detectionRadius, float interactionRadius) {
        super(x, y, 1, 1,0.5f,1);
        texture = new Texture("textures/player.png");
        speed = 5;
        sprintSpeed = 10;
        sprint = false;
        this.detectionRadius = detectionRadius;
        this.interactionRadius = interactionRadius;
    }

    @Override
    public void update(float delta) {
        move(delta);
        this.setBoundsH(); // after move() for sync hitboxes
    }

    @Override
    public void render(SpriteBatch batch) {
        //draw player
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }


    @Override
    public void move(float delta) {
        float velocityLength = (float) Math.sqrt(getVelocityX() * getVelocityX() + getVelocityY() * getVelocityY());
        if (velocityLength > 0) {
            if(sprint){
                this.setX(getX() + getVelocityX()/velocityLength * getSprintSpeed() * delta);
                this.setY(getY() + getVelocityY()/velocityLength * getSprintSpeed() * delta);
            }else {
                this.setX(getX() + getVelocityX()/velocityLength * getSpeed() * delta);
                this.setY(getY() + getVelocityY()/velocityLength * getSpeed() * delta);
            }

        }
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setSprintSpeed(float sprintSpeed) {
        this.sprintSpeed = sprintSpeed;
    }

    @Override
    public float getSprintSpeed() {
        return sprintSpeed;
    }

    @Override
    public void setSprint(boolean sprint) {
        this.sprint = sprint;
    }

    @Override
    public boolean getSprint() {
        return sprint;
    }

    @Override
    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    @Override
    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    @Override
    public float getVelocityX() {
        return velocityX;
    }

    @Override
    public float getVelocityY() {
        return velocityY;
    }


    @Override
    public float getDetectionRadius() {
        return detectionRadius;
    }

    @Override
    public void setDetectionRadius(float detectionRadius) {
        this.detectionRadius = detectionRadius;
    }

    @Override
    public void setBoundsD(float centerPointX, float centerPointY, float detectionRadius) {
            boundsD.set(getCenterPointX(), getCenterPointY(), getDetectionRadius());
    }

    @Override
    public Circle getBoundsD() {
        return boundsD;
    }

    @Override
    public float getInteractionRadius() {
        return interactionRadius;
    }

    @Override
    public void setInteractionRadius(float interactionRadius) {
        this.interactionRadius = interactionRadius;
    }

    @Override
    public void setBoundsI(float centerPointX, float centerPointY, float interactionRadius) {
        boundsI.set(getCenterPointX(), getCenterPointY(), getInteractionRadius());
    }

    @Override
    public Circle getBoundsI() {
        return boundsI;
    }
}
