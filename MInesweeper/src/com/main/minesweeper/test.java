package com.main.minesweeper;

import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GridBagLayout Example");
        frame.setLayout(new GridLayout(10,10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints gbc = new GridBagConstraints();


        for(int i = 0; i < 100; i++){
            JButton button = new JButton(""+i);
            button.setEnabled(false);
            frame.add(button);
        }


        frame.setSize(300, 200);
        frame.setVisible(true);

    }

    public void piantFrame(){

    }
}
