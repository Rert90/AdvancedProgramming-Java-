package org.example;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

public class GameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        System.out.println("Connecting to server...");
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            System.out.println("Connected to the server at " + SERVER_ADDRESS + ":" + SERVER_PORT);

            Scanner keyboardScanner = new Scanner(System.in);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            Scanner serverResponseScanner = new Scanner(input);
            PrintWriter writer = new PrintWriter(output, true);

            while (true) {
                System.out.println("Enter command (type 'exit' to quit):");
                String userInput = keyboardScanner.nextLine();

                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }

                writer.println(userInput);

                if (serverResponseScanner.hasNextLine()) {
                    String response = serverResponseScanner.nextLine();
                    System.out.println("Server response: " + response);
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot connect to server: " + e.getMessage());
        }
    }
}

