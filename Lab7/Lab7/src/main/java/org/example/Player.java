package org.example;

public class Player extends Thread {
    private final String name;
    private final Bag bag;
    private int maxSequenceValue;

    public Player(String name, Bag bag) {
        this.name = name;
        this.bag = bag;
        this.maxSequenceValue = 0;
    }

    @Override
    public void run() {
        while (true) {
            Token token;
            synchronized (bag) {
                token = bag.extractToken();
            }
            if (token == null) {
                break;
            }
            processToken(token);
        }
        System.out.println(name + " finished with max sequence value: " + maxSequenceValue);
    }

    private void processToken(Token token) {
        System.out.println(name + " processed token: (" + token.first() + ", " + token.second() + ")");
        maxSequenceValue++;
    }
}
