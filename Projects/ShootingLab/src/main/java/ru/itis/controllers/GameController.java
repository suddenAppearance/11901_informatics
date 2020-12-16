package ru.itis.controllers;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class GameController extends Controller implements Initializable {
    public Boolean p1u = false, p1d = false, p1l = false, p1r = false, p1running = false; //means player 1 up/down/left/right
    public Boolean p2u = false, p2d = false, p2l = false, p2r = false, p2running = false;
    private long shot_power; //time the space was hold
    @FXML
    Circle player2;

    public Circle getPlayer2() {
        return player2;
    }

    public void setPlayer2(Circle player2) {
        this.player2 = player2;
    }

    public Circle getPlayer1() {
        return player1;
    }

    public void setPlayer1(Circle player1) {
        this.player1 = player1;
    }

    @FXML
    Circle player1;

    public EventHandler<KeyEvent> getMoveButtonPressed() {
        return moveButtonPressed;
    }

    public void setMoveButtonPressed(EventHandler<KeyEvent> moveButtonPressed) {
        this.moveButtonPressed = moveButtonPressed;
    }

    public void movePlayer(int dx, int dy) {
        player1.setLayoutX(player1.getLayoutX() + dx);
        player1.setLayoutY(player1.getLayoutY() + dy);
    }

    public void movePlayer2(int dx, int dy) {
        player2.setLayoutX(player2.getLayoutX() + dx);
        player2.setLayoutY(player2.getLayoutY() + dy);
    }

    EventHandler<KeyEvent> moveButtonPressed = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case W:
                    p1u = true;
                    break;
                case S:
                    p1d = true;
                    break;
                case D:
                    p1r = true;
                    break;
                case A:
                    p1l = true;
                    break;
                case SHIFT:
                    p1running = true;
                case SPACE:
                    shot_power = System.currentTimeMillis();
            }
        }
    };

    public EventHandler<KeyEvent> getMoveButtonReleased() {
        return moveButtonReleased;
    }

    public void setMoveButtonReleased(EventHandler<KeyEvent> moveButtonReleased) {
        this.moveButtonReleased = moveButtonReleased;
    }
    private void shoot(long shot_power, double xPos, double yPos){
        runner.getSocketClient().sendMessage("shot " + shot_power/100 + "from" + xPos + " " + yPos);
    }

    EventHandler<KeyEvent> moveButtonReleased = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case W:
                    p1u = false;
                    break;
                case S:
                    p1d = false;
                    break;
                case D:
                    p1r = false;
                    break;
                case A:
                    p1l = false;
                    break;
                case SHIFT:
                    p1running = false;
                case SPACE:
                    shot_power = System.currentTimeMillis() - shot_power;
                    shoot(shot_power, player1.getLayoutX(), player1.getLayoutY());
            }
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                if (p1u) dy -= 1;
                if (p1d) dy += 1;
                if (p1l) dx -= 1;
                if (p1r) dx += 1;
                if (p1running) {
                    dx *= 3;
                    dy *= 3;
                }

                if (dx != 0 || dy != 0){
                    runner.getSocketClient().sendMessage("mov " + dx + " " + dy);
                movePlayer(dx, dy);}
            }
        };
        animationTimer.start();
    }
}
