package ru.itis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public void start(int port) {
        ServerSocket server;

        try {
            server = new ServerSocket(port);
            // уводит приложение в ожидание, пока не подключится клиент
            // как только клиент подключился, поток продолжает выполнение и помещает
            // "клиента" в client
            Socket player1 = server.accept();
            // читаем сообщения от клиента
            BufferedReader fromPlayer1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            PrintWriter toPlayer1 = new PrintWriter(player1.getOutputStream(), true);
            toPlayer1.println("waiting");
            Socket player2 = server.accept();
            BufferedReader fromPlayer2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            PrintWriter toPlayer2 = new PrintWriter(player2.getOutputStream(), true);
            toPlayer1.println("map");
            toPlayer2.println("mappick");
            String message = fromPlayer1.readLine();
            while (message == null){
                message = fromPlayer1.readLine();
            }
            toPlayer2.println(message);
            while (true){
                String messageFromPlayer1 = fromPlayer1.readLine();
                String messageFromPlayer2 = fromPlayer2.readLine();
                if (messageFromPlayer1 != null){
                    toPlayer2.println(messageFromPlayer1);
                }
                if (messageFromPlayer2 != null){
                    toPlayer1.println(messageFromPlayer2);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }



}
