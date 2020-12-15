package ru.itis;

public class MainForServer {
    public static void main(String[] args) {
        Server server = new Server();
        server.start(7777);
    }
}
