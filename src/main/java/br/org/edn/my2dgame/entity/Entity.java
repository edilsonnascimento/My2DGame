package br.org.edn.my2dgame.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY ;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, rigth1, rigth2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, getSolidAreaDefaultY;
    public boolean collisionOn = false;


}
