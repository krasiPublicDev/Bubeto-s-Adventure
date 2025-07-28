package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Bubeto's Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);//gives default frame settings;

        window.pack(); //when pack is used the settings are implemented

        window.setLocationRelativeTo(null);
        window.setVisible(true);//the image drawing starts here

        gamePanel.requestFocusInWindow();
        gamePanel.startGameThread();


    }
}