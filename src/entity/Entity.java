package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    //everything with up 3 is stationary in a certain direction
    public BufferedImage up1, up2, upStatic, down1, down2, downStatic, left1, left2, leftStatic, right1, right2, rightStatic;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;
}
