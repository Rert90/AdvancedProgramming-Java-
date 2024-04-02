package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {
    private JPanel configPanel;
    private JPanel canvasPanel;
    private JPanel controlPanel;

    private JLabel gridSizeLabel;
    private JTextField gridSizeTextField;
    private JButton newGameButton;

    private JButton loadButton;
    private JButton saveButton;
    private JButton exitButton;

    private int gridSize = 0;

    public GameGUI() {
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        configPanel = new JPanel();
        gridSizeLabel = new JLabel("Grid Size:");
        gridSizeTextField = new JTextField(5);
        newGameButton = new JButton("New Game");
        configPanel.add(gridSizeLabel);
        configPanel.add(gridSizeTextField);
        configPanel.add(newGameButton);

        canvasPanel = createCanvasPanel();

        controlPanel = createControlPanel();

        add(configPanel, BorderLayout.NORTH);
        add(canvasPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gridSize = Integer.parseInt(gridSizeTextField.getText());
                    canvasPanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid grid size!");
                }
            }
        });

        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private JPanel createCanvasPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (gridSize > 0) {
                    int cellSize = getWidth() / gridSize;
                    g.setColor(Color.BLACK);
                    for (int i = 0; i <= gridSize; i++) {
                        g.drawLine(i * cellSize, 0, i * cellSize, getHeight());
                        g.drawLine(0, i * cellSize, getWidth(), i * cellSize);
                    }
                }
            }
        };
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        exitButton = new JButton("Exit");
        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(exitButton);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Loading game...");
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Saving game...");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameGUI();
            }
        });
    }
}
