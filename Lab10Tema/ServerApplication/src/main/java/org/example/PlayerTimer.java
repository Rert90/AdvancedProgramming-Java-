package org.example;

public class PlayerTimer {
    private long startTime;
    private long timeLimit;
    private long timeLeft;
    private boolean isRunning;

    public PlayerTimer(long timeLimit) {
        this.timeLimit = timeLimit;
        this.timeLeft = timeLimit;
        this.isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            timeLeft -= (System.currentTimeMillis() - startTime);
            isRunning = false;
        }
    }

    public long getTimeLeft() {
        if (isRunning) {
            return timeLeft - (System.currentTimeMillis() - startTime);
        }
        return timeLeft;
    }

    public boolean isTimeOut() {
        return getTimeLeft() <= 0;
    }
}
