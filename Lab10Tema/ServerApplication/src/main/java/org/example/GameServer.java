package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class GameServer {
    private ServerSocket serverSocket;
    private Map<String, Game> games = new HashMap<>();
    private boolean running = true;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                new ClientThread(clientSocket, games).start();
            } catch (IOException e) {
                if (!running) {
                    System.out.println("Server stopped");
                    break;
                }
                throw e;
            }
        }
    }

    public void stop() throws IOException {
        running = false;
        serverSocket.close();
    }

    public static void main(String[] args) {
        GameServer server = new GameServer();
        try {
            server.start(12345);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
