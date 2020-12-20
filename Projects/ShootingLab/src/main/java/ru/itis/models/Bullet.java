package ru.itis.models;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Bullet {
    private Circle circle;
    private int shot_power;
    private int xSpeed;

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public int getShot_power() {
        return shot_power;
    }

    public void setShot_power(int shot_power) {
        this.shot_power = shot_power;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public Bullet(double xPos, double yPos, int shot_power, int xSpeed){
        this.xSpeed = xSpeed;
        this.shot_power = shot_power;
        circle = new Circle(shot_power, Color.RED);
        circle.setLayoutX(xPos);
        circle.setLayoutY(yPos);
    }
    public void travel(){
        circle.setLayoutX(circle.getLayoutX() + xSpeed);
        circle.setLayoutY(circle.getLayoutY() - 9);
    }

    public boolean hit(Player player) {//hitboxes are rectangles i dont care:)))
        return (player.getCircle().getLayoutX() < getCircle().getLayoutX() + getCircle().getRadius() * 2) &&
                player.getCircle().getLayoutX() + player.getCircle().getRadius() * 2 > getCircle().getLayoutX() &&
                player.getCircle().getLayoutY() < getCircle().getLayoutY() + getCircle().getRadius() * 2 &&
                player.getCircle().getLayoutY() + player.getCircle().getRadius() * 2 > getCircle().getLayoutY();
    }
}
