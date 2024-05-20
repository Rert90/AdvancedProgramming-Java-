package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String id;
    private Player player1;
    private Player player2;
    private GameBoard board1;
    private GameBoard board2;
    private boolean isPlayer1Turn;
    private boolean isPlayer1Ready;
    private boolean isPlayer2Ready;
    private PlayerTimer timer1;
    private PlayerTimer timer2;
    private static final long TIME_LIMIT = 300000; // 5 minutes in milliseconds

    public Game(String id, Player player1) {
        this.id = id;
        this.player1 = player1;
        this.board1 = new GameBoard();
        this.isPlayer1Turn = true;
        this.isPlayer1Ready = false;
        this.isPlayer2Ready = false;
        this.timer1 = new PlayerTimer(TIME_LIMIT);
        this.timer2 = new PlayerTimer(TIME_LIMIT);
    }

    public boolean joinGame(Player player2) {
        if (this.player2 == null) {
            this.player2 = player2;
            this.board2 = new GameBoard();
            return true;
        }
        return false;
    }

    public String placeShip(String playerId, int x, int y, int length, boolean horizontal) {
        if (player1.getId().equals(playerId)) {
            if (board1.placeShip(x, y, length, horizontal)) {
                return "Ship placed successfully";
            } else {
                return "Invalid ship placement";
            }
        } else if (player2.getId().equals(playerId)) {
            if (board2.placeShip(x, y, length, horizontal)) {
                return "Ship placed successfully";
            } else {
                return "Invalid ship placement";
            }
        }
        return "Invalid player ID";
    }

    public void setPlayerReady(String playerId) {
        if (player1.getId().equals(playerId)) {
            isPlayer1Ready = true;
        } else if (player2.getId().equals(playerId)) {
            isPlayer2Ready = true;
        }
    }

    public boolean areBothPlayersReady() {
        return isPlayer1Ready && isPlayer2Ready;
    }

    public String submitMove(String playerId, int x, int y) {
        if (!areBothPlayersReady()) {
            return "Both players must be ready before starting the game";
        }

        if ((isPlayer1Turn && player1.getId().equals(playerId)) ||
                (!isPlayer1Turn && player2.getId().equals(playerId))) {

            boolean hit;
            try {
                if (isPlayer1Turn) {
                    timer1.stop();
                    if (timer1.isTimeOut()) {
                        return "Time out! " + player2.getName() + " wins!";
                    }
                    hit = board2.attack(x, y);
                } else {
                    timer2.stop();
                    if (timer2.isTimeOut()) {
                        return "Time out! " + player1.getName() + " wins!";
                    }
                    hit = board1.attack(x, y);
                }
                isPlayer1Turn = !isPlayer1Turn;
                if (isPlayer1Turn) {
                    timer1.start();
                } else {
                    timer2.start();
                }

                if (isGameOver()) {
                    return "Game over! " + (isPlayer1Turn ? player2.getName() : player1.getName()) + " wins!";
                }

                return hit ? "Hit!" : "Miss!";
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Invalid coordinates!";
            }
        }
        return "Not your turn!";
    }

    private boolean isGameOver() {
        return board1.allShipsSunk() || board2.allShipsSunk();
    }

    public String getId() {
        return id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public GameBoard getBoard1() {
        return board1;
    }

    public GameBoard getBoard2() {
        return board2;
    }

    public boolean isPlayer1Turn() {
        return isPlayer1Turn;
    }

    public void setPlayer1Turn(boolean isPlayer1Turn) {
        this.isPlayer1Turn = isPlayer1Turn;
    }
}
