package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 5;
    }

    public void update() {
        if (keyHandler.upPressed) {
            y -= speed;
        } else if (keyHandler.downPressed) {
            y += speed;
        } else if (keyHandler.leftPressed) {
            x -= speed;
        } else if (keyHandler.rightPressed) {
            x += speed;
        }
    }

    public void draw(Graphics g2) {

        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gamePanel.titleSize, gamePanel.titleSize);

    }


}
