package org.example;

public class Ship {
    private int x, y, length;
    private boolean horizontal;

    public Ship(int x, int y, int length, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.horizontal = horizontal;
    }

    public boolean isSunk(char[][] board) {
        if (horizontal) {
            for (int i = 0; i < length; i++) {
                if (board[x + i][y] != 'X') {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (board[x][y + i] != 'X') {
                    return false;
                }
            }
        }
        return true;
    }
}
