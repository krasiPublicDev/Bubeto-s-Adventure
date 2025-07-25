package main;


import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTitleSize = 32;
    final int scale = 1;

    public int titleSize = originalTitleSize * scale;
    final int maxScreenCol = 30;
    final int maxScreenRow = 46;
    final int screenWidth = titleSize * maxScreenRow;
    final int screenHeight = titleSize * maxScreenCol;

    final int fps = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, keyHandler);

    //default player location
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);//focuses on the current map square
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    @SuppressWarnings("BusyWait")
    public void run() {

        final double drawInterval = (double) 1000000000 / fps;
        double delta = 0;
        double previousTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - previousTime) / drawInterval;
            timer += (currentTime - previousTime);// if timer foes over a second draw count will be shown
            previousTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();// repaint calls paintComponent
                delta--;
                drawCount++;

            }

            if(timer >= 1000000000){
                System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        player.draw(g);

        g2.dispose();

    }
}
