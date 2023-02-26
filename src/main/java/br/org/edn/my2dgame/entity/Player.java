package br.org.edn.my2dgame.entity;

import br.org.edn.my2dgame.main.GamePanel;
import br.org.edn.my2dgame.main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static br.org.edn.my2dgame.main.Constants.*;
import static java.awt.AlphaComposite.SRC_OVER;
import static java.awt.AlphaComposite.getInstance;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

public class Player extends Entity {
    public static final int TIME_CHANG_IMAGE = 12;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    private final String directoyBaseImage = "/player/";

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        this.keyHandler = keyHandler;

        screenX = gamePanel.screeWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screeHeight/2 - (gamePanel.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 23;
        solidArea.height = 30;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage(directoyBaseImage + "walking/");
        getPlayerAttackImage(directoyBaseImage + "attaking/");
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

    public void getPlayerImage(String path) {
        up1 = setup(path,"boy_up_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup(path,"boy_up_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup(path,"boy_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup(path,"boy_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup(path,"boy_left_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup(path,"boy_left_2", gamePanel.tileSize, gamePanel.tileSize);
        rigth1 = setup(path,"boy_right_1", gamePanel.tileSize, gamePanel.tileSize);
        rigth2 = setup(path,"boy_right_2", gamePanel.tileSize, gamePanel.tileSize);
    }
    private void getPlayerAttackImage(String path) {
        attackUp1 = setup(path, "boy_attack_up_1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackUp2 = setup(path, "boy_attack_up_2", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackDow1 = setup(path, "boy_attack_down_1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackDow2 = setup(path, "boy_attack_down_2", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackLeft1 = setup(path, "boy_attack_left_1", gamePanel.tileSize * 2, gamePanel.tileSize );
        attackLeft2 = setup(path, "boy_attack_left_2", gamePanel.tileSize * 2 , gamePanel.tileSize);
        attackRight1 = setup(path, "boy_attack_right_1", gamePanel.tileSize *2 , gamePanel.tileSize);
        attackRight2 = setup(path, "boy_attack_right_2", gamePanel.tileSize * 2, gamePanel.tileSize);
    }

    public void update() {

        if(attacking) {
            attacking();
        } else {
            if (isKeypressed()) {
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
                if (!collisionOn && !gamePanel.keyHandler.enterPressed) {
                    switch (direction) {
                        case UP-> worldY -= speed;
                        case DOWN -> worldY += speed;
                        case LEFT -> worldX -= speed;
                        case RIGHT -> worldX += speed;
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
                } else {
                    standCounter++;
                    if (standCounter == 20) {
                        spriteNum = 1;
                        standCounter = 0;
                    }
                }
            }
            // This needs to be outSide of key if statement!
            if (invincible) {
                invicibleCounter++;
                if (invicibleCounter > 60) {
                    invincible = FALSE;
                    invicibleCounter = 0;
                }
            }
        }
    }

    private void attacking() {
        spriteCounter++;

        if( spriteCounter <= FIVE_FRAMES)
            spriteNum = 1;

        if(spriteCounter > FIVE_FRAMES && spriteCounter <= TWENTY_FIVE_FRAMES) {
            spriteNum = 2;

            // SAVE THE CURRENT WORLDX, WORLDY, SOLIDAREA
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeigth = solidArea.height;

            // ADJUST PLAYER'S WORLDX/Y FOR THE ATTACKAREA
            switch (direction) {
                case UP -> worldX -= attackArea.height;
                case DOWN -> worldY += attackArea.height;
                case LEFT -> worldX -= attackArea.width;
                case RIGHT -> worldX += attackArea.width;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
            damegeMonster(monsterIndex);

            // After checking collision, resotre the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeigth;
        }

        if(spriteCounter > TWENTY_FIVE_FRAMES) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = FALSE;
        }
    }

    private void pickUpObject(int index) {
        if(isCollision(index)) {
        }
    }

    private void interactNPC(int index) {
        if(gamePanel.keyHandler.enterPressed) {
            if (isCollision(index)) {
                gamePanel.gameState = gamePanel.diologueState;
                gamePanel.npc[index].speak();
            } else {
                gamePanel.playSE(7);
                attacking = TRUE;
            }
        }
    }

    private void contactMonster(int monsterIndex) {
        if(isCollision(monsterIndex) && !invincible) {
            gamePanel.playSE(6);
            life -=1;
            invincible = TRUE;
        }
    }

    private void damegeMonster(int monsterIndex) {
        if(isCollision(monsterIndex)) {
            Entity monster = gamePanel.monster[monsterIndex];
            if(!monster.invincible) {
                gamePanel.playSE(5);
                monster.life -= 1;
                monster.invincible = TRUE;
                monster.damageReaction();
                if(monster.life <= 0 )
                    monster.dying = TRUE;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage imagePlayer = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case UP :
                if(!attacking) {
                    if (spriteNum == 1) imagePlayer = up1;
                    if (spriteNum == 2) imagePlayer = up2;
                }
                if(attacking) {
                    tempScreenY = screenY - gamePanel.tileSize;
                    if (spriteNum == 1) imagePlayer = attackUp1;
                    if (spriteNum == 2) imagePlayer = attackUp2;
                }
                break;
            case DOWN :
                if(!attacking) {
                    if (spriteNum == 1) imagePlayer = down1;
                    if (spriteNum == 2) imagePlayer = down2;
                }
                if(attacking) {
                    if (spriteNum == 1) imagePlayer = attackDow1;
                    if (spriteNum == 2) imagePlayer = attackDow2;
                }
                break;
            case LEFT :
                if(!attacking) {
                    if (spriteNum == 1) imagePlayer = left1;
                    if (spriteNum == 2) imagePlayer = left2;
                }
                if(attacking) {
                    tempScreenX = screenX - gamePanel.tileSize;
                    if (spriteNum == 1) imagePlayer = attackLeft1;
                    if (spriteNum == 2) imagePlayer = attackLeft2;
                }
                break;
            case RIGHT :
                if(!attacking) {
                    if (spriteNum == 1) imagePlayer = rigth1;
                    if (spriteNum == 2) imagePlayer = rigth2;
                }
                if(attacking) {
                    if (spriteNum == 1) imagePlayer = attackRight1;
                    if (spriteNum == 2) imagePlayer = attackRight2;
                }
                break;
        }

        if(invincible)
            graphics2D.setComposite(getInstance(SRC_OVER, 0.3f));

        graphics2D.drawImage(imagePlayer, tempScreenX, tempScreenY, null);

        // RESET
        graphics2D.setComposite(getInstance(SRC_OVER, 1f));
    }

    private boolean isKeypressed() {
        return keyHandler.upPressed || keyHandler.downPressed ||
                keyHandler.leftPressed || keyHandler.rightPressed ||
                keyHandler.enterPressed;
    }

    private boolean isCollision(int index) {
        return index != NOT_OBJECTS;
    }
}