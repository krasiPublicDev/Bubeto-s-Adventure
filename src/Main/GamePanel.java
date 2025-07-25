package Main;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTitleSize = 32;
    final int scale = 1;

    final int titleSize = originalTitleSize * scale;
    final int maxScreenCol = 30;
    final int maxScreenRow = 46;
    final int screenWidth = titleSize * maxScreenRow;
    final int screenHeight = titleSize * maxScreenCol;

    final int fps = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

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

//    @Override
//    @SuppressWarnings("BusyWait")
//    public void run() {
//
//        double drawInterval = (double) 1000000000 / fps;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        double remainingTime = 0;
//
//        while (gameThread != null) {
//
//            update();
//
//            repaint();// repaint calls paintComponent
//
//
//            try {
//                remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//
//                if (remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    @Override
    @SuppressWarnings("BusyWait")
    public void run() {

        final double drawInterval = (double) 1000000000 / fps;
        double delta = 0;
        double previousTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - previousTime) / drawInterval;
            previousTime = currentTime;

            if (delta >= 1) {

                update();
                repaint();// repaint calls paintComponent
                delta--;

            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {

        if (keyHandler.upPressed) {
            playerY -= playerSpeed;
        } else if (keyHandler.downPressed) {
            playerY += playerSpeed;
        } else if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        } else if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, titleSize, titleSize);

        g2.dispose();

    }
}
