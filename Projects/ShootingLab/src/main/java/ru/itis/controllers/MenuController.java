package ru.itis.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends Controller implements Initializable{
    @FXML
    Button playButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playButton.setOnAction(event -> {
            try {
                runner.getStage().getScene().setRoot(runner.getConnection());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }
}
