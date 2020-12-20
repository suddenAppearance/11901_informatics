package ru.itis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Server implements Runnable {
    Socket player1;
    Socket player2;
    BufferedReader fromPlayer1;
    PrintWriter toPlayer1;
    BufferedReader fromPlayer2;
    PrintWriter toPlayer2;

    public void start(int port) throws InterruptedException {
        ServerSocket server;

        try {
            server = new ServerSocket(port);
            // уводит приложение в ожидание, пока не подключится клиент
            // как только клиент подключился, поток продолжает выполнение и помещает
            // "клиента" в client
            waitConnection(server);
            waitPlayerReady();

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void waitConnection(ServerSocket server) throws IOException, InterruptedException {
        Socket player1 = server.accept();
        // читаем сообщения от клиента
        fromPlayer1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
        toPlayer1 = new PrintWriter(player1.getOutputStream(), true);
        toPlayer1.println("connected");
        TimeUnit.SECONDS.sleep(1);
        player2 = server.accept();
        fromPlayer2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
        toPlayer2 = new PrintWriter(player2.getOutputStream(), true);
        toPlayer2.println("connected");
        TimeUnit.SECONDS.sleep(1);
        toPlayer1.println("start");
        toPlayer2.println("start");
        TimeUnit.SECONDS.sleep(1);
    }

    private void waitPlayerReady() throws IOException, InterruptedException {
        while (true) {
            String messageFromPlayer1 = null;
            String messageFromPlayer2 = null;
            if (fromPlayer1.ready()) {
                messageFromPlayer1 = fromPlayer1.readLine();
            }
            if (fromPlayer2.ready()) {
                messageFromPlayer2 = fromPlayer2.readLine();
            }
            if (messageFromPlayer1 != null) {
                
                toPlayer2.println(messageFromPlayer1);
            }
            if (messageFromPlayer2 != null) {
                
                toPlayer1.println(messageFromPlayer2);
            }
        }
    }

    public void run() {
        try {
            start(7777);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
