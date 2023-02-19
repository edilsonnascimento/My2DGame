package br.org.edn.my2dgame.entity;

import br.org.edn.my2dgame.main.GamePanel;
import br.org.edn.my2dgame.main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static br.org.edn.my2dgame.main.Constants.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

public class Player extends Entity {
    public static final int TIME_CHANG_IMAGE = 12;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    public int hasKey;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 23;
        solidArea.height = 30;

        screenX = gamePanel.screeWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screeHeight/2 - (gamePanel.tileSize/2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getEntityImage("/player/");
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";

        // PLAYER SATUS
        maxLife = 6;
        life = 6;
    }
    @Override
    public void getEntityImage(String directory) {
        up1 = setup(directory,"boy_up_1");
        up2 = setup(directory,"boy_up_2");
        down1 = setup(directory,"boy_down_1");
        down2 = setup(directory,"boy_down_2");
        left1 = setup(directory,"boy_left_1");
        left2 = setup(directory,"boy_left_2");
        rigth1 = setup(directory,"boy_right_1");
        rigth2 = setup(directory,"boy_right_2");
    }

    public void update() {
        if(isKeypressed()) {
            if (keyHandler.upPressed) {
                direction = UP;
            } else if (keyHandler.downPressed) {
                direction = DOWN;
            } else if (keyHandler.leftPressed) {
                direction = LEFT;
            } else if (keyHandler.rightPressed) {
                direction = RIGHT;
            }
            //CHECK TILE COLLISION
            collisionOn = false;
            gamePanel.collisionChecker.checkTitle(this);

            //CHECK OBJECT COLLISION
            int objectIndex = gamePanel.collisionChecker.checkObject(this, TRUE);
            pickUpObject(objectIndex);

            //CHECK NPC COLLISION
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gamePanel.eventHandler.checkEvent();

            // IF NOT COLLISION, PLAYER CON MOVE
            if(!collisionOn && !gamePanel.keyHandler.enterPressed) {
                switch (direction) {
                    case UP :
                        worldY -= speed;
                        break;
                    case DOWN :
                        worldY += speed;
                        break;
                    case LEFT :
                        worldX -= speed;
                        break;
                    case RIGHT :
                        worldX += speed;
                        break;
                }
            }
            gamePanel.keyHandler.enterPressed = FALSE;

            spriteCounter++;
            if (spriteCounter > TIME_CHANG_IMAGE) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            else {
                standCounter++;
                if(standCounter == 20) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
        }
         // This needs to be outSide of key if statement!
        if(invincible) {
            invicibleCounter++;
            if(invicibleCounter > 60) {
                invincible = FALSE;
                invicibleCounter = 0;
            }
        }
    }

    private void contactMonster(int monsterIndex) {
        if(isCollision(monsterIndex) && !invincible) {
            life -=1;
            invincible = TRUE;
        }
    }

    private void interactNPC(int index) {
        if(isCollision(index)) {
            gamePanel.gameState = gamePanel.diologueState;
            gamePanel.npc[index].speak();
        }
    }

    private void pickUpObject(int index) {
        if(isCollision(index)) {
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

    private boolean isCollision(int index) {
        return index != NOT_OBJECTS;
    }


    private boolean isKeypressed() {
        return keyHandler.upPressed || keyHandler.downPressed ||
                keyHandler.leftPressed || keyHandler.rightPressed ||
                keyHandler.enterPressed;
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

        if(invincible) {
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        graphics2D.drawImage(image, screenX, screenY, null);
        // RESET
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
       // DEBUG
//        graphics2D.setFont(new Font("Arial", Font.PLAIN, 26));
//        graphics2D.setColor(Color.white);
//        graphics2D.drawString("Invincible: " + invicibleCounter, 10, 400);
    }
}