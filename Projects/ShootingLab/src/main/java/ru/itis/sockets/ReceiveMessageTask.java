package ru.itis.sockets;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.shape.Circle;
import ru.itis.controllers.ConnectionController;
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
                    });

                } else if (messageFromServer.equals("map")) {
                    Platform.runLater(() -> {
                        try {
                            controller.runner.getStage().setScene(controller.runner.getMapPick());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                } else if (messageFromServer.equals("mappick")) {
                    Platform.runLater(() -> {
                        controller.getStatus().setText("Соперник выбирает карту. Ожидайте");
                    });

                } else if (messageFromServer.matches("^map is .+ player[1-2]")) {
                    Platform.runLater(() -> {
                        try {
                            controller.runner.getStage().setScene(controller.runner.getGame(messageFromServer.split(" ")[2]));
                            gameController = controller.runner.getGameController();
                            gameController.runner = controller.runner;
                            if (messageFromServer.split(" ")[3].equals("player2")) {
                                Circle temp = gameController.getPlayer1();
                                gameController.setPlayer1(gameController.getPlayer2());
                                gameController.setPlayer2(temp);
                            }
                            gameController.runner.getStage().getScene().setOnKeyPressed(gameController.getMoveButtonPressed());
                            gameController.runner.getStage().getScene().setOnKeyReleased(gameController.getMoveButtonReleased());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } else if (messageFromServer.matches("mov -?[0-9]+ -?[0-9]+")) {
                    String[] command = messageFromServer.split(" ");
                    gameController.movePlayer2(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
