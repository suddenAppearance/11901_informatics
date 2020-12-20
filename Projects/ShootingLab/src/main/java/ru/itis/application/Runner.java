package ru.itis.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.itis.controllers.ConnectionController;
import ru.itis.controllers.GameController;
import ru.itis.controllers.MenuController;
import ru.itis.sockets.ReceiveMessageTask;
import ru.itis.sockets.SocketClient;

import java.io.IOException;

public class Runner extends Application {
    private Scene connection;
    private ConnectionController connectionController;
    private Scene menu;
    private MenuController menuController;

    public Scene getGame() throws IOException {
        if (game == null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/maps/emptymap.fxml"));
            game = loader.load();
            gameController = loader.getController();
            gameController.pane = (AnchorPane) game.getRoot();
            gameController.runner = this;
            game.setOnKeyPressed(gameController.getMoveButtonPressed());
            game.setOnKeyReleased(gameController.getMoveButtonReleased());

        }
        return game;
    }

    private Scene game;
    private GameController gameController;
    private Scene mapPick;
    private Stage stage;
    private Scene current;
    private SocketClient socketClient;
    private ReceiveMessageTask receiveMessageTask;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getCurrent() {
        return current;
    }

    public void setCurrent(Scene current) {
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
        Scene g = getMenu();
        MenuController controller = getMenuController();
        controller.runner = this;
        primaryStage.setScene(g);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void run() {
        launch();
    }

    public Scene getConnection() throws IOException {
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

    public void setConnection(Scene connection) {
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

    public Scene getMenu() throws IOException {
        if (menu == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
            menu = loader.load();
            menuController = loader.getController();
            menuController.runner = this;
        }
        return menu;
    }

    public void setMenu(Scene menu) {
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

    public Scene getEnd() throws IOException {
        return new FXMLLoader(getClass().getResource("/fxml/end.fxml")).load();
    }
}
