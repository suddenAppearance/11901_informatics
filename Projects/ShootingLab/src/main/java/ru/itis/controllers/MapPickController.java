package ru.itis.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MapPickController extends Controller implements Initializable {
    @FXML
    ComboBox<String> mapsList;
    @FXML
    Button chooseButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(new File("src/main/resources/fxml/maps").list());
        mapsList.setItems(observableList);
        chooseButton.setOnAction(event -> {
            runner.getSocketClient().sendMessage("map is " + mapsList.getValue());

        });
    }
}
