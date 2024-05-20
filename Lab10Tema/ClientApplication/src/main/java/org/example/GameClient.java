package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) {
        GameClient client = new GameClient();
        try {
            client.startConnection("127.0.0.1", 12345);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String msg;
            while ((msg = reader.readLine()) != null) {
                if ("exit".equalsIgnoreCase(msg)) {
                    break;
                }
                System.out.println(client.sendMessage(msg));
            }
            client.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
