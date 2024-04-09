package org.example;

import java.awt.*;
import java.io.*;

public class GameLogic implements Serializable {
    private GameBoard gameBoard;
    private PositionalGame positionalGame;
    private Color[] players = {Color.RED, Color.BLUE};
    private Color currentPlayer;

    public GameLogic(GameBoard gameBoard, PositionalGame positionalGame) {
        this.gameBoard = gameBoard;
        this.positionalGame = positionalGame;
        currentPlayer = players[0]; // Red player starts
    }

    public void handleMouseClick(Point clickedPoint, int panelWidth, int panelHeight) {
        int cellWidth = panelWidth / gameBoard.getGridSize();
        int cellHeight = panelHeight / gameBoard.getGridSize();
        int x = clickedPoint.x / cellWidth;
        int y = clickedPoint.y / cellHeight;

        Point convertedPoint = new Point(x, y);

        if (gameBoard.isValidMove(convertedPoint)) {
            Color stoneColor = currentPlayer;
            gameBoard.placeStone(convertedPoint, stoneColor);

            if (isWinningMove(convertedPoint)) {
                positionalGame.displayWinner(currentPlayer);
            }

            currentPlayer = (currentPlayer == Color.RED) ? Color.BLUE : Color.RED;
        }
    }

    private boolean isWinningMove(Point lastMove) {
        int x = lastMove.x;
        int y = lastMove.y;
        Color stoneColor = currentPlayer;

        int count = 1;
        for (int i = 1; i < 4 && x + i < gameBoard.getGridSize() && gameBoard.getStoneColor(new Point(x + i, y)) == stoneColor; i++) {
            count++;
        }
        for (int i = 1; i < 4 && x - i >= 0 && gameBoard.getStoneColor(new Point(x - i, y)) == stoneColor; i++) {
            count++;
        }
        if (count >= 4) return true;

        count = 1;
        for (int i = 1; i < 4 && y + i < gameBoard.getGridSize() && gameBoard.getStoneColor(new Point(x, y + i)) == stoneColor; i++) {
            count++;
        }
        for (int i = 1; i < 4 && y - i >= 0 && gameBoard.getStoneColor(new Point(x, y - i)) == stoneColor; i++) {
            count++;
        }
        if (count >= 4) return true;

        count = 1;
        for (int i = 1; x - i >= 0 && y - i >= 0 && gameBoard.getStoneColor(new Point(x - i, y - i)) == stoneColor; i++) {
            count++;
        }
        for (int i = 1; x + i < gameBoard.getGridSize() && y + i < gameBoard.getGridSize() && gameBoard.getStoneColor(new Point(x + i, y + i)) == stoneColor; i++) {
            count++;
        }
        if (count >= 4) return true;

        count = 1;
        for (int i = 1; x - i >= 0 && y + i < gameBoard.getGridSize() && gameBoard.getStoneColor(new Point(x - i, y + i)) == stoneColor; i++) {
            count++;
        }
        for (int i = 1; x + i < gameBoard.getGridSize() && y - i >= 0 && gameBoard.getStoneColor(new Point(x + i, y - i)) == stoneColor; i++) {
            count++;
        }
        return count >= 4;
    }


    public void saveState(String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(this);
            System.out.println("Game logic state saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameLogic loadState(String filePath, GameBoard gameBoard, PositionalGame positionalGame) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            GameLogic gameLogic = (GameLogic) inputStream.readObject();
            gameLogic.gameBoard = gameBoard;
            gameLogic.positionalGame = positionalGame;
            System.out.println("Game logic state loaded from: " + filePath);
            return gameLogic;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
