package ru.itis.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 30.11.2020
 * 07. Sockets
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class SocketClient {
    private Socket client;

    private PrintWriter toServer; // на EchoServerSocket fromClient
    private BufferedReader fromServer; // на EchoServerSocket toClient

    public SocketClient(String host, int port) throws IOException {
        client = new Socket(host, port);
        toServer = new PrintWriter(client.getOutputStream(), true);
        fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public void sendMessage(String message) {
        toServer.println(message);
    }

    public BufferedReader getFromServer() {
        return fromServer;
    }
}
