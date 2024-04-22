package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TokenBag {
    private final List<Token> tokens;
    private final Random random;
    private int currentPlayerIndex;
    private final Object turnLock;
    private final int numPlayers;

    public TokenBag(int n, int numPlayers) {
        tokens = new ArrayList<>();
        random = new Random();
        currentPlayerIndex = 0;
        turnLock = new Object();
        this.numPlayers = numPlayers;

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                tokens.add(new Token(i, j));
            }
        }
    }

    public synchronized Token drawToken() {
        if (tokens.isEmpty()) {
            return null;
        }

        int index = random.nextInt(tokens.size());
        return tokens.remove(index);
    }

    public synchronized void startNextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        synchronized (turnLock) {
            turnLock.notifyAll();
        }
    }

    public synchronized void waitForTurn(int playerIndex) throws InterruptedException {
        while (currentPlayerIndex != playerIndex) {
            synchronized (turnLock) {
                turnLock.wait();
            }
        }
    }

    public synchronized void endTurn() {
        synchronized (turnLock) {
            turnLock.notifyAll();
        }
    }
}
