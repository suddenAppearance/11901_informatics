package ru.itis.sockets;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.shape.Circle;
import ru.itis.controllers.ConnectionController;
import ru.itis.controllers.GameController;
import ru.itis.models.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
    private GameController gameController;

    public ReceiveMessageTask(BufferedReader fromServer, ConnectionController controller) {
        this.fromServer = fromServer;
        this.controller = controller;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            try {

                if (!fromServer.ready()) continue;
                String messageFromServer = fromServer.readLine();
                if (messageFromServer.equals("connected")) {
                    Platform.runLater(() -> {
                        controller.getStatus().setText("Ожидание подключения второго игрока");
                        controller.getStatus().setStyle("-fx-text-inner-color: blue;");
                    });
                } else if (messageFromServer.matches("start")) {
                    Platform.runLater(() -> {
                        controller.getStatus().setText("Запуск...");
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            controller.runner.getStage().setScene(controller.runner.getGame());
                            gameController = controller.runner.getGameController();
                        } catch (InterruptedException | IOException e) {
                            e.printStackTrace();
                        }
                    });

                } else if (messageFromServer.matches("shot from .+")){
                    String[] tokens = messageFromServer.split(" ");
                    Platform.runLater(()->gameController.shot_fired(Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5])));
                } else if (messageFromServer.equals("end")){
                    Platform.runLater(()-> {
                        try {
                            gameController.runner.getStage().setScene(controller.runner.getEnd());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }

            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
