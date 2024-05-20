package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Map;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Map<String, Game> games;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int GAME_ID_LENGTH = 6;

    public ClientThread(Socket socket, Map<String, Game> games) {
        this.clientSocket = socket;
        this.games = games;
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] command = inputLine.split(" ");
                if (command.length == 0) {
                    out.println("Invalid command format");
                    continue;
                }
                switch (command[0].toLowerCase()) {
                    case "create":
                        if (command.length >= 4 && "game".equalsIgnoreCase(command[1])) {
                            String gameId = generateGameId();
                            Player player1 = new Player(command[2], command[3]);
                            Game game = new Game(gameId, player1);
                            games.put(gameId, game);
                            out.println("Game created with ID: " + gameId);
                        } else {
                            out.println("Invalid command format for create game");
                        }
                        break;
                    case "join":
                        if (command.length >= 5 && "game".equalsIgnoreCase(command[1])) {
                            String gameId = command[2];
                            Player player2 = new Player(command[3], command[4]);
                            Game game = games.get(gameId);
                            if (game != null && game.joinGame(player2)) {
                                out.println("Joined game with ID: " + gameId);
                            } else {
                                out.println("Failed to join game with ID: " + gameId);
                            }
                        } else {
                            out.println("Invalid command format for join game");
                        }
                        break;
                    case "placeship":
                        if (command.length >= 7) {
                            String gameId = command[1];
                            String playerId = command[2];
                            try {
                                int x = Integer.parseInt(command[3]);
                                int y = Integer.parseInt(command[4]);
                                int length = Integer.parseInt(command[5]);
                                boolean horizontal = Boolean.parseBoolean(command[6]);
                                Game game = games.get(gameId);
                                if (game != null) {
                                    String result = game.placeShip(playerId, x, y, length, horizontal);
                                    out.println(result);
                                } else {
                                    out.println("Invalid game ID: " + gameId);
                                }
                            } catch (NumberFormatException e) {
                                out.println("Invalid coordinates or length for place ship");
                            }
                        } else {
                            out.println("Invalid command format for place ship");
                        }
                        break;
                    case "ready":
                        if (command.length >= 3) {
                            String gameId = command[1];
                            String playerId = command[2];
                            Game game = games.get(gameId);
                            if (game != null) {
                                game.setPlayerReady(playerId);
                                out.println("Player " + playerId + " is ready");
                            } else {
                                out.println("Invalid game ID: " + gameId);
                            }
                        } else {
                            out.println("Invalid command format for ready");
                        }
                        break;
                    case "submitmove":
                        if (command.length >= 5) {
                            String gameId = command[1];
                            String playerId = command[2];
                            try {
                                int x = Integer.parseInt(command[3]);
                                int y = Integer.parseInt(command[4]);
                                Game game = games.get(gameId);
                                if (game != null) {
                                    String result = game.submitMove(playerId, x, y);
                                    out.println(result);
                                } else {
                                    out.println("Invalid game ID: " + gameId);
                                }
                            } catch (NumberFormatException e) {
                                out.println("Invalid coordinates for submit move");
                            }
                        } else {
                            out.println("Invalid command format for submit move");
                        }
                        break;
                    case "stop":
                        try {
                            out.println("Server stopping");
                            clientSocket.close();
                            System.exit(0);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        out.println("Invalid command");
                        break;
                }
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateGameId() {
        SecureRandom random = new SecureRandom();
        StringBuilder gameId = new StringBuilder(GAME_ID_LENGTH);
        for (int i = 0; i < GAME_ID_LENGTH; i++) {
            gameId.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return gameId.toString();
    }
}
