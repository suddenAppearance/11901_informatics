package ru.itis.sockets;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import ru.itis.application.Runner;
import ru.itis.controllers.ConnectionController;
import ru.itis.controllers.Controller;
import ru.itis.controllers.GameController;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 30.11.2020
 * 07. Sockets
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
// слушатель сообщений от сервера
public class ReceiveMessageTask extends Task<Void> {
    // читаем сообщения с сервера
    private BufferedReader fromServer; // на EchoServerSocket toClient
    private ConnectionController controller;

    public ReceiveMessageTask(BufferedReader fromServer, ConnectionController controller) {
        this.fromServer = fromServer;
        this.controller = controller;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer.equals("waiting")) {
                    Platform.runLater(() -> {
                        controller.getStatus().setText("Ожидание подключения второго игрока");
                        controller.getStatus().setStyle("-fx-text-inner-color: blue;");
                    });
                } else if (messageFromServer.equals("map")) {
                    controller.getStatus().getScene().setRoot(controller.runner.getMapPick());
                } else if (messageFromServer.equals("mappick")) {
                    controller.getStatus().setText("Соперник выбирает карту. Ожидайте");
                } else if (messageFromServer.matches("^map is .+$")) {

                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
