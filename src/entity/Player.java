package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    private String lastDirection = "down";

    public final int screenX;
    public final int screenY;

    private final LinkedList<String> directionQueue = new LinkedList<>();


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gamePanel.tileSize * 60;
        worldY = gamePanel.tileSize * 34;
        speed = 10;
        direction = "up";
    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(getClass().getResource("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResource("/player/up2.png"));
            upStatic = ImageIO.read(getClass().getResource("/player/upStatic.png"));

            down1 = ImageIO.read(getClass().getResource("/player/doun1.png"));
            down2 = ImageIO.read(getClass().getResource("/player/doun2.png"));
            downStatic = ImageIO.read(getClass().getResource("/player/dounStatic.png"));

            left1 = ImageIO.read(getClass().getResource("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResource("/player/left2.png"));
            leftStatic = ImageIO.read(getClass().getResource("/player/leftStatic.png"));

            right1 = ImageIO.read(getClass().getResource("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResource("/player/right2.png"));
            rightStatic = ImageIO.read(getClass().getResource("/player/rightStatic.png"));


        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void update() {
        String dir = keyHandler.getCurrentDirection();
        boolean isMoving = false;

        if (dir != null) {
            direction = dir;
            switch (dir) {
                case "up" -> {
                    worldY -= speed;
                    isMoving = true;
                    lastDirection = "up";
                }
                case "down" -> {
                    worldY += speed;
                    isMoving = true;
                }
                case "left" -> {
                    worldX -= speed;
                    isMoving = true;
                }
                case "right" -> {
                    worldX += speed;
                    isMoving = true;
                }
            }

            if (isMoving) {
                lastDirection = direction;

                spriteCounter++;
                if (spriteCounter > 18) {
                    spriteNumber = (spriteNumber == 1) ? 2 : 1;
                    spriteCounter = 0;
                }
            } else {
                spriteNumber = 1; // idle sprite
            }
        } else {
            switch (lastDirection) {
                case "up" -> direction = "upStatic";
                case "down" -> direction = "downStatic";
                case "left" -> direction = "leftStatic";
                case "right" -> direction = "rightStatic";
            }
            spriteNumber = 1;
        }
    }

    public void draw(Graphics g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) {
                    image = up1;
                } else if (spriteNumber == 2) {
                    image = up2;
                }
            }

            case "upStatic" -> image = upStatic;

            case "down" -> {
                if (spriteNumber == 1) {
                    image = down1;
                } else if (spriteNumber == 2) {
                    image = down2;
                }
            }

            case "downStatic" -> image = downStatic;

            case "left" -> {
                if (spriteNumber == 1) {
                    image = left1;
                } else if (spriteNumber == 2) {
                    image = left2;
                }
            }

            case "leftStatic" -> image = leftStatic;

            case "right" -> {
                if (spriteNumber == 1) {
                    image = right1;
                } else if (spriteNumber == 2) {
                    image = right2;
                }
            }

            case "rightStatic" -> image = rightStatic;

        }

        g2.drawImage(image, screenX, screenY, gamePanel.playerSize, gamePanel.playerSize, null);

    }


}
