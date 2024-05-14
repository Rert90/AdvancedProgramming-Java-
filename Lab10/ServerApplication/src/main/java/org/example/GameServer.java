package org.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class GameServer {
    private static final int PORT = 1234;
    private static ServerSocket serverSocket;
    private static boolean isRunning = true;

    public static void main(String[] args) {
        System.out.println("Server is starting...");
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server is listening on port " + PORT);

            while (isRunning) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New client connected");

                    ClientThread clientThread = new ClientThread(clientSocket);
                    new Thread(clientThread).start();
                } catch (IOException e) {
                    System.out.println("I/O error: " + e.getMessage());
                    if (!isRunning) {
                        System.out.println("Server is stopping...");
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Server could not start: " + ex.getMessage());
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException ex) {
                System.out.println("Failed to close server socket: " + ex.getMessage());
            }
        }
    }

    public static void stopServer() {
        isRunning = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error closing server socket: " + e.getMessage());
        }
    }
}
