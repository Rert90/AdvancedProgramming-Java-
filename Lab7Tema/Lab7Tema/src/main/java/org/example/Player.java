package org.example;

public class Player extends Thread {
    private final String name;
    private final TokenBag tokenBag;
    private int score;

    public Player(String name, TokenBag tokenBag) {
        this.name = name;
        this.tokenBag = tokenBag;
        this.score = 0;
    }

    public void run() {
        while (true) {
            Token token;
            synchronized (tokenBag) {
                token = tokenBag.drawToken();
            }
            if (token == null) {
                break;
            }
            System.out.println(name + ": Got token: " + token);

            score += checkForSequence(token);
        }
        System.out.println(name + ": Finished with score: " + score);
    }

    private int checkForSequence(Token token) {
        int sequenceLength = 1;
        int lastNum = token.getNum2();

        while (true) {
            Token nextToken;
            synchronized (tokenBag) {
                nextToken = tokenBag.drawToken();
            }
            if (nextToken == null) {
                break;
            }
            System.out.println(name + ": Got token: " + nextToken);

            if (nextToken.getNum1() == lastNum) {
                sequenceLength++;
                lastNum = nextToken.getNum2();
            } else {
                return sequenceLength;
            }
        }
        return sequenceLength;
    }

    public int getScore() {
        return score;
    }
}