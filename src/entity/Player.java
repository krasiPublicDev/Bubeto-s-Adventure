package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    private String lastDirection = "down";


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 6;
        direction = "up";
    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(getClass().getResource("/player/Andy-9.png.png"));
            up2 = ImageIO.read(getClass().getResource("/player/Andy-8.png.png"));
            up3 = ImageIO.read(getClass().getResource("/player/Andy-7.png.png"));

            down1 = ImageIO.read(getClass().getResource("/player/Andy-11.png.png"));
            down2 = ImageIO.read(getClass().getResource("/player/Andy-10.png.png"));
            down3 = ImageIO.read(getClass().getResource("/player/Andy-1.png.png"));

            left1 = ImageIO.read(getClass().getResource("/player/Andy-4.png.png"));
            left2 = ImageIO.read(getClass().getResource("/player/Andy-6.png.png"));
            left3 = ImageIO.read(getClass().getResource("/player/Andy-7.static.png"));

            right1 = ImageIO.read(getClass().getResource("/player/Andy-3.png.png"));
            right2 = ImageIO.read(getClass().getResource("/player/Andy-5.png.png"));
            right3 = ImageIO.read(getClass().getResource("/player/Andy-8.static.png"));


        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void update() {
        boolean isMoving = false;

        if (keyHandler.upPressed) {
            y -= speed;
            direction = "up";
            isMoving = true;
        } else if (keyHandler.downPressed) {
            y += speed;
            direction = "down";
            isMoving = true;
        } else if (keyHandler.leftPressed) {
            x -= speed;
            direction = "left";
            isMoving = true;
        } else if (keyHandler.rightPressed) {
            x += speed;
            direction = "right";
            isMoving = true;
        }

        if (isMoving) {
            lastDirection = direction;

            spriteCounter++;
            if (spriteCounter > 20) {
                spriteNumber = (spriteNumber == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        } else {
            // ⬇️ Here’s the switch when not moving
            switch (lastDirection) {
                case "up" -> direction = "upStatic";
                case "down" -> direction = "downStatic";
                case "left" -> direction = "leftStatic";
                case "right" -> direction = "rightStatic";
            }

            spriteNumber = 1; // Optional: keep idle image consistent
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

            case "upStatic" -> image = up3;

            case "down" -> {
                if (spriteNumber == 1) {
                    image = down1;
                } else if (spriteNumber == 2) {
                    image = down2;
                }
            }

            case "downStatic" -> image = down3;

            case "left" -> {
                if (spriteNumber == 1) {
                    image = left1;
                } else if (spriteNumber == 2) {
                    image = left2;
                }
            }

            case "leftStatic" -> image = left3;

            case "right" -> {
                if (spriteNumber == 1) {
                    image = right1;
                } else if (spriteNumber == 2) {
                    image = right2;
                }
            }

            case "rightStatic" -> image = right3;

        }

        g2.drawImage(image, x, y, gamePanel.titleSize * 3, gamePanel.titleSize * 3, null);

    }


}
