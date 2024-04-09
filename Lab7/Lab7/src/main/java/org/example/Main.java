package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numberOfPlayers = 5;
        int n = 7;

        Bag bag = new Bag(n);
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player("Player " + (i + 1), bag));
        }

        for (Player player : players) {
            player.start();
        }

        for (Player player : players) {
            try {
                player.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Game finished.");
    }
}
