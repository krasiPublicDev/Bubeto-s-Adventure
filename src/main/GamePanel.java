package main;


import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    final int originalTileSize = 40 ;
    final int scale = 2;

    public int tileSize = originalTileSize * scale;
    public int playerSize = tileSize ;
    final int maxScreenCol = 12;
    final int maxScreenRow = 20;
    final int screenWidth = tileSize * maxScreenRow;
    final int screenHeight = tileSize * maxScreenCol;

    final int fps = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, keyHandler);

    TileManager tileManager = new TileManager(this);

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

            if (timer >= 1000000000) {
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

    @Override
    public void paintComponent(Graphics g) {//called when repaint is invoked

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g);


        g2.dispose();

    }
}
