package ru.itis.application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import ru.itis.controllers.ConnectionController;
import ru.itis.controllers.GameController;
import ru.itis.controllers.MapPickController;
import ru.itis.controllers.MenuController;
import ru.itis.sockets.ReceiveMessageTask;
import ru.itis.sockets.SocketClient;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Runner extends Application {
    private Group connection;
    private ConnectionController connectionController;
    private Group menu;
    private MenuController menuController;
    private Group game;
    private GameController gameController;
    private Group mapPick;
    private MapPickController mapPickController;
    private Stage stage;
    private Group current;
    private SocketClient socketClient;
    private ReceiveMessageTask receiveMessageTask;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Group getCurrent() {
        return current;
    }

    public void setCurrent(Group current) {
        this.current = current;
    }

    public SocketClient getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public ReceiveMessageTask getReceiveMessageTask() {
        return receiveMessageTask;
    }

    public void setReceiveMessageTask(ReceiveMessageTask receiveMessageTask) {
        this.receiveMessageTask = receiveMessageTask;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Group g = getMenu();
        MenuController controller = getMenuController();
        controller.runner = this;
        primaryStage.setScene(new Scene(g));
        primaryStage.show();
    }

    public void run() {
        launch();
    }

    public Group getConnection() throws IOException {
        if (connection == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/connection.fxml"));
            connection = loader.load();
            connectionController = loader.getController();
            connectionController.runner = this;
            this.socketClient = connectionController.getSocketClient();
            this.receiveMessageTask = connectionController.getReceiveMessageTask();
        }
        return connection;
    }

    public void setConnection(Group connection) {
        this.connection = connection;
    }

    public ConnectionController getConnectionController() {
        if (connection == null) {
            throw new IllegalStateException("Connection Loader not defined");
        }
        return connectionController;
    }

    public void setConnectionController(ConnectionController connectionController) {
        this.connectionController = connectionController;
    }

    public Group getMenu() throws IOException {
        if (menu == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
            menu = loader.load();
            menuController = loader.getController();
            menuController.runner = this;
        }
        return menu;
    }

    public void setMenu(Group menu) {
        this.menu = menu;
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public Group getMapPick() throws IOException {
        if (mapPick == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mapPick.fxml"));
            mapPick = loader.load();
            mapPickController = loader.getController();
            mapPickController.runner = this;
        }
        return mapPick;
    }

    public void setMapPick(Group mapPick) {
        this.mapPick = mapPick;
    }

    public MapPickController getMapPickController() {
        return mapPickController;
    }

    public void setMapPickController(MapPickController mapPickController) {
        this.mapPickController = mapPickController;
    }

}
