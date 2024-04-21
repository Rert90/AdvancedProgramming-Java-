package org.example;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int n = 5;
        int numPlayers = 4;
        long timeLimitMillis = TimeUnit.MINUTES.toMillis(5);

        TokenBag tokenBag = new TokenBag(n);
        TimeKeeper timekeeper = new TimeKeeper(timeLimitMillis);
        timekeeper.start();

        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player("Player " + (i + 1), tokenBag);
            players[i].start();
        }

        for (Player player : players) {
            try {
                player.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Player winner = players[0];
        for (int i = 1; i < numPlayers; i++) {
            if (players[i].getScore() > winner.getScore()) {
                winner = players[i];
            }
        }

        System.out.println("Winner: " + winner.getName() + " with score: " + winner.getScore());
    }
}