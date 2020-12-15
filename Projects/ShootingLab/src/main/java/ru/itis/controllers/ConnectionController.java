package ru.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import lombok.SneakyThrows;
import org.w3c.dom.Node;
import ru.itis.sockets.ReceiveMessageTask;
import ru.itis.sockets.SocketClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConnectionController extends Controller implements Initializable {
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

    private SocketClient socketClient;
    private ReceiveMessageTask receiveMessageTask;
    public Text getStatus() {
        return status;
    }

    public void setStatus(Text status) {
        this.status = status;
    }

    @FXML
    Text status;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            socketClient = new SocketClient("localhost", 7777);
            receiveMessageTask = new ReceiveMessageTask(socketClient.getFromServer(), this);
            ExecutorService service = Executors.newFixedThreadPool(1);
            service.execute(receiveMessageTask);
        } catch (IOException e) {
            status.setText("Unable to connect to server");
            status.setStyle("-fx-text-inner-color: red;");
            TimeUnit.SECONDS.sleep(3);
            System.exit(0);
        }

    }
}
