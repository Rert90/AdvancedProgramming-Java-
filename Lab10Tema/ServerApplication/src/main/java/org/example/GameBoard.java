package org.example;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private char[][] board;
    private List<Ship> ships;

    public GameBoard() {
        this.board = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '-'; // Initializează tabla cu celule goale
            }
        }
        this.ships = new ArrayList<>();
    }

    public boolean placeShip(int x, int y, int length, boolean horizontal) {
        if (horizontal) {
            if (x + length > 10) return false;
            for (int i = 0; i < length; i++) {
                if (board[x + i][y] != '-') return false;
            }
            for (int i = 0; i < length; i++) {
                board[x + i][y] = 'S';
            }
        } else {
            if (y + length > 10) return false;
            for (int i = 0; i < length; i++) {
                if (board[x][y + i] != '-') return false;
            }
            for (int i = 0; i < length; i++) {
                board[x][y + i] = 'S';
            }
        }
        ships.add(new Ship(x, y, length, horizontal));
        return true;
    }

    public boolean attack(int x, int y) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            throw new ArrayIndexOutOfBoundsException("Coordinates out of bounds");
        }
        if (board[x][y] == 'S') {
            board[x][y] = 'X'; // Lovitură
            return true;
        } else if (board[x][y] == '-') {
            board[x][y] = 'O'; // Rată
            return false;
        }
        return false;
    }

    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk(board)) {
                return false;
            }
        }
        return true;
    }

    public char[][] getBoard() {
        return board;
    }
}
