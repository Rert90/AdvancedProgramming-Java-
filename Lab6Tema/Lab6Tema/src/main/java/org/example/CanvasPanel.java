package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CanvasPanel extends JPanel {
    private GameBoard gameBoard;
    private GameLogic gameLogic;

    public CanvasPanel(GameBoard gameBoard, GameLogic gameLogic) {
        this.gameBoard = gameBoard;
        this.gameLogic = gameLogic;

        setPreferredSize(new Dimension(400, 400));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gameLogic.handleMouseClick(e.getPoint(), getWidth(), getHeight());
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameBoard.draw(g, getWidth(), getHeight());
    }

    public void updateGameObjects(GameBoard gameBoard, GameLogic gameLogic) {
        this.gameBoard = gameBoard;
        this.gameLogic = gameLogic;
    }
}
