package ru.itis;

public class MainForServer {
    public static void main(String[] args) {
        Thread thread = new Thread(new Server());
        thread.start();
    }
}
