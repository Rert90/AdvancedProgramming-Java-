package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PositionalGame extends JFrame {
    private CanvasPanel canvasPanel;
    private GameBoard gameBoard;
    private GameLogic gameLogic;

    public PositionalGame() {
        setTitle("Positional Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gameBoard = new GameBoard(8);
        gameLogic = new GameLogic(gameBoard, this);
        canvasPanel = new CanvasPanel(gameBoard, gameLogic);
        add(canvasPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Game");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage image = new BufferedImage(canvasPanel.getWidth(), canvasPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = image.createGraphics();
                canvasPanel.paint(g2d);
                g2d.dispose();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Game as PNG");
                fileChooser.setSelectedFile(new File("game_board.png"));
                int userSelection = fileChooser.showSaveDialog(PositionalGame.this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try {
                        ImageIO.write(image, "PNG", fileToSave);
                        JOptionPane.showMessageDialog(PositionalGame.this, "Game saved as PNG successfully!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(PositionalGame.this, "Failed to save game as PNG.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        add(saveButton, BorderLayout.WEST);

        JButton loadButton = new JButton("Load Game");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Load Game State");
                int userSelection = fileChooser.showOpenDialog(PositionalGame.this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    gameLogic = GameLogic.loadState(filePath, gameBoard, PositionalGame.this);
                    if (gameLogic != null) {
                        canvasPanel.updateGameObjects(gameBoard, gameLogic);
                        repaint();
                        JOptionPane.showMessageDialog(PositionalGame.this, "Game state loaded successfully!");
                    } else {
                        JOptionPane.showMessageDialog(PositionalGame.this, "Failed to load game state.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        add(loadButton, BorderLayout.EAST);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void displayWinner(Color winner) {
        JOptionPane.showMessageDialog(this, winner + " wins the game!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PositionalGame::new);
    }
}
