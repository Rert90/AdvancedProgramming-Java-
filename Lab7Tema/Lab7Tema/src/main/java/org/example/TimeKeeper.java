package org.example;

import java.util.concurrent.TimeUnit;

public class TimeKeeper extends Thread {
    private final long timeLimitMillis;
    private final long startTime;

    public TimeKeeper(long timeLimitMillis) {
        this.timeLimitMillis = timeLimitMillis;
        this.startTime = System.currentTimeMillis();
        setDaemon(true);
    }

    public void run() {
        while (true) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            long remainingTime = timeLimitMillis - elapsedTime;
            if (remainingTime <= 0) {
                System.out.println("Time limit exceeded. Game over.");
                System.exit(0);
            }

            long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTime);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingTime) - TimeUnit.MINUTES.toSeconds(minutes);
            System.out.println("Time remaining: " + minutes + " minutes, " + seconds + " seconds");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}