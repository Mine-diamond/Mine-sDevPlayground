package com.main.minesweeper;

import javax.swing.*;

public class GameLogic {
    static int row = 10;
    static int col = 10;

    public static void initial(){
        SwingUtilities.invokeLater(() -> new GameUI());
    }
}
