package br.org.edn.my2dgame.entity;

import br.org.edn.my2dgame.main.Constants;
import br.org.edn.my2dgame.main.GamePanel;
import br.org.edn.my2dgame.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

public class Player extends Entity {

    private final int TIME_CHANG_IMAGE = 12;
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    public int hasKey;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        screenX = gamePanel.screeWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screeHeight/2 - (gamePanel.tileSize/2);
        solidArea = new Rectangle(8, 16, 24, 24);
        solidAreaDefaultX = solidArea.x;
        getSolidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            rigth1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            rigth2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
            System.out.println("ERROR CAPTURAR IMAGEM");
        }
    }

    public void update() {
        if(isKeypressed()) {
            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            }
            //CHECK TILE COLLISION
            collisionOn = false;
            gamePanel.collisionChecker.checkTitle(this);

            //CHECK OBJECT COLLISION
            int objectIndex = gamePanel.collisionChecker.checkObject(this, TRUE);
            pickUpObject(objectIndex);

            // IF NOT COLLISION, PLAYER CON MOVE
            if(!collisionOn) {
                switch (direction) {
                    case "up" :
                        worldY -= speed;
                        break;
                    case "down" :
                        worldY += speed;
                        break;
                    case "left" :
                        worldX -= speed;
                        break;
                    case "right" :
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if (spriteCounter > TIME_CHANG_IMAGE) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    private void pickUpObject(int index) {
        if(index != Constants.NOT_OBJECTS) {
            String objectName = isNull(gamePanel.objects[index]) ? "" : gamePanel.objects[index].name;
            switch (objectName) {
                case "Key" :
                    hasKey++;
                    gamePanel.playSE(1);
                    gamePanel.objects[index] = null;
                    gamePanel.ui.showMessage("You got a key!");
                    break;
                case "Door" :
                    if(hasKey > 0) {
                        gamePanel.playSE(3);
                        gamePanel.objects[index] = null;
                        hasKey--;
                        gamePanel.ui.showMessage("You opened the door");
                    } else {
                        gamePanel.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots" :
                    gamePanel.playSE(2);
                    speed += 2;
                    gamePanel.objects[index] = null;
                    gamePanel.ui.showMessage("Speed up!");
                    break;
                case "Chest" :
                    gamePanel.ui.gameFinished = true;
                    gamePanel.stopMusic();
                    gamePanel.playSE(4);
                    break;
            }

        }
    }
    private boolean isKeypressed() {
        return keyHandler.upPressed || keyHandler.downPressed ||
                keyHandler.leftPressed || keyHandler.rightPressed;
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up" :
                if(spriteNum == 1)
                    image = up1;
                if(spriteNum == 2)
                    image = up2;
                break;
            case "down" :
                if(spriteNum == 1)
                    image = down1;
                if(spriteNum == 2)
                    image = down2;
                break;
            case "left" :
                if(spriteNum == 1)
                    image = left1;
                if(spriteNum == 2)
                    image = left2;
                break;
            case "right" :
                if(spriteNum == 1)
                    image = rigth1;
                if(spriteNum == 2)
                    image = rigth2;
                break;
        }
        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}