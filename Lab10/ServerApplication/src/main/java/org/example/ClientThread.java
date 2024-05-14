package org.example;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class ClientThread implements Runnable {
    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            Scanner scanner = new Scanner(input);
            PrintWriter writer = new PrintWriter(output, true);

            while (true) {
                if (scanner.hasNextLine()) {
                    String command = scanner.nextLine();
                    if ("stop".equalsIgnoreCase(command)) {
                        writer.println("Server stopped");
                        GameServer.stopServer();
                        break;
                    } else {
                        writer.println("Server received the request: " + command);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error in client communication: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Could not close the socket: " + e.getMessage());
            }
        }
    }
}
