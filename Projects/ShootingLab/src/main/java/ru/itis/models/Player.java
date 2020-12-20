package ru.itis.models;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player {
    public Boolean p1u = false, p1d = false, p1l = false, p1r = false, p1running = false; //means player 1 up/down/left/right
    private Circle circle;
    private int shot_power;
    private int hp;


    public CopyOnWriteArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(CopyOnWriteArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    private CopyOnWriteArrayList<Bullet> bullets;

    public Player() {
        circle = new Circle();
        shot_power = 1;
        hp = 100;
        bullets = new CopyOnWriteArrayList<>();
    }

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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String shoot(AnchorPane pane) {
        if (shot_power > 26) shot_power = 26;
        int xSpeed = ((p1l ? -1 : 0) + (p1r ? 1 : 0)) * (p1running ? 3 : 1);
        Bullet bullet = new Bullet(circle.getLayoutX(), circle.getLayoutY(), shot_power, xSpeed);
        pane.getChildren().add(bullet.getCircle());
        bullets.add(bullet);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.005), animation -> {
            bullet.getCircle().setLayoutY(bullet.getCircle().getLayoutY() - 9);
            bullet.getCircle().setLayoutX(bullet.getCircle().getLayoutX() + xSpeed);
        }));
        timeline.setCycleCount(500);
        timeline.play();
        return "shot from " + circle.getLayoutX() + " " + circle.getLayoutY() + " " + xSpeed + " " + shot_power;
    }

    public void move() {
        int dx = 0, dy = 0;
        if (p1u) dy -= 1;
        if (p1d) dy += 1;
        if (p1l) dx -= 1;
        if (p1r) dx += 1;
        if (p1running) {
            dx *= 3;
            dy *= 3;
        }
        circle.setLayoutX(circle.getLayoutX() + dx);
        circle.setLayoutY(circle.getLayoutY() + dy);
    }
}
