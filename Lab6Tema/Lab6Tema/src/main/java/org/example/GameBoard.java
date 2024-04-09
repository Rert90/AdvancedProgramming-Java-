package org.example;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
    private List<Point> sticks;
    private int gridSize;
    private Color[][] stones;

    public GameBoard(int gridSize) {
        this.gridSize = gridSize;
        sticks = new ArrayList<>();
        stones = new Color[gridSize][gridSize];
        initializeGame();
    }

    private void initializeGame() {
        Random random = new Random();
        int numSticks = gridSize * gridSize / 4;
        for (int i = 0; i < numSticks; i++) {
            int x = random.nextInt(gridSize - 1);
            int y = random.nextInt(gridSize - 1);
            sticks.add(new Point(x, y));
        }
    }

    public boolean isValidMove(Point point) {
        return !sticks.contains(point) && stones[point.x][point.y] == null;
    }

    public void placeStone(Point point, Color color) {
        stones[point.x][point.y] = color;
    }

    public Color getStoneColor(Point point) {
        return stones[point.x][point.y];
    }

    public void draw(Graphics g, int width, int height) {
        int cellWidth = width / gridSize;
        int cellHeight = height / gridSize;

        g.setColor(Color.BLACK);
        for (Point stick : sticks) {
            int x = stick.x * cellWidth;
            int y = stick.y * cellHeight;
            g.fillRect(x, y, cellWidth, cellHeight);
        }


        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (stones[i][j] != null) {
                    g.setColor(stones[i][j]);
                    g.fillOval(i * cellWidth + cellWidth / 4, j * cellHeight + cellHeight / 4, cellWidth / 2, cellHeight / 2);
                }
            }
        }
    }


    public int getGridSize() {
        return gridSize;
    }

}
