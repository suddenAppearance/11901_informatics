package ru.itis.controllers;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import lombok.SneakyThrows;
import ru.itis.models.Bullet;
import ru.itis.models.Player;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class GameController extends Controller implements Initializable {
    private long space = 0;
    public AnchorPane pane;
    public Player player = new Player();

    @FXML
    Circle player1; //used only to set player's circle in init method

    public EventHandler<KeyEvent> getMoveButtonPressed() {
        return moveButtonPressed;
    }

    public void setMoveButtonPressed(EventHandler<KeyEvent> moveButtonPressed) {
        this.moveButtonPressed = moveButtonPressed;
    }

    public void movePlayer(int dx, int dy) {
        player.getCircle().setLayoutX(player.getCircle().getLayoutX() + dx);
        player.getCircle().setLayoutY(player.getCircle().getLayoutY() + dy);
    }

    EventHandler<KeyEvent> moveButtonPressed = new EventHandler<KeyEvent>() {
        @SneakyThrows
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case W:
                    player.p1u = true;
                    break;
                case S:
                    player.p1d = true;
                    break;
                case D:
                    player.p1r = true;
                    break;
                case A:
                    player.p1l = true;
                    break;
                case SHIFT:
                    player.p1running = true;
                    break;
                case SPACE:
                    space = space == 0 ? System.currentTimeMillis() : space;
                    break;
            }
        }
    };

    public EventHandler<KeyEvent> getMoveButtonReleased() {
        return moveButtonReleased;
    }

    public void setMoveButtonReleased(EventHandler<KeyEvent> moveButtonReleased) {
        this.moveButtonReleased = moveButtonReleased;
    }

    private String shoot() {
        return player.shoot(pane);
    }

    EventHandler<KeyEvent> moveButtonReleased = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case W:
                    player.p1u = false;
                    break;
                case S:
                    player.p1d = false;
                    break;
                case D:
                    player.p1r = false;
                    break;
                case A:
                    player.p1l = false;
                    break;
                case SHIFT:
                    player.p1running = false;
                    break;
                case SPACE:
                    player.setShot_power((int) (5 + (System.currentTimeMillis() - space) / 50));
                    space = 0;
                    runner.getSocketClient().sendMessage(shoot());
                    player.setShot_power(1);
                    break;
            }
        }
    };

    public void shot_fired(double xPos, double yPos, int xSpeed, int shot_power) {
        if (shot_power > 26) shot_power = 26;
        Bullet bullet = new Bullet(this.runner.getStage().getWidth() - xPos, -yPos, shot_power, -xSpeed);
        pane.getChildren().add(bullet.getCircle());
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.005), animation -> {
            if (bullet.hit(player)) {
                player.setHp(player.getHp() - bullet.getShot_power());
                bullet.getCircle().setOpacity(0);
                bullet.setShot_power(0);
                if(player.getHp() < 1) {
                    try {
                        runner.getStage().setScene(runner.getEnd());
                        runner.getSocketClient().sendMessage("end");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                player.getCircle().setOpacity(player.getHp()/100.0);
            }
            bullet.getCircle().setLayoutY(bullet.getCircle().getLayoutY() + 9);
            bullet.getCircle().setLayoutX(bullet.getCircle().getLayoutX() - xSpeed);
        }));
        timeline.setCycleCount(1000);
        timeline.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player.setCircle(player1);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                player.move();
                for (Bullet bullet : (player.getBullets())
                ) {
                    bullet.travel();
                    if (bullet.getCircle().getLayoutY() < -10) {
                        player.getBullets().remove(bullet);

                    }
                }
            }
        };
        animationTimer.start();
    }
}
