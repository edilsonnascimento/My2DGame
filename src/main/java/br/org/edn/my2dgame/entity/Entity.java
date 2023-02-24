package br.org.edn.my2dgame.entity;

import br.org.edn.my2dgame.main.GamePanel;
import br.org.edn.my2dgame.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static br.org.edn.my2dgame.entity.Player.TIME_CHANG_IMAGE;
import static br.org.edn.my2dgame.main.Constants.*;
import static java.awt.AlphaComposite.SRC_OVER;
import static java.awt.AlphaComposite.getInstance;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Entity {

    protected GamePanel gamePanel;

    // IMAGES
    public BufferedImage up1, up2, down1, down2, left1, left2, rigth1, rigth2;
    public BufferedImage attackUp1, attackUp2, attackDow1, attackDow2, attackLeft1, attackLeft2,
    attackRight1, attackRight2;
    public BufferedImage heartFull, heartBlank, heartHalf;

    // SOLID AREA
    public Rectangle solidArea = new Rectangle(0, 0, 34, 40);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = FALSE;

    public boolean invincible = FALSE;

    protected String dialogues[] = new String[20];

    // STATE
    public int worldX, worldY ;
    public String direction = DOWN;
    public int spriteNum = 1;
    protected int dialogueIndex = 0;
    public boolean collision = FALSE;
    boolean attacking = FALSE;
    public boolean alive = TRUE;
    public boolean dying = FALSE;

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int standCounter = 0;
    public int invicibleCounter = 0;
    int dyingCounter = 0;

    // CHARACTER STATUS
    public int type;
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    
    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    protected BufferedImage setup(String imagePath, String imageName, int width, int height) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream(imagePath + imageName + ".png"));
            bufferedImage = utilityTool.scaleImage(bufferedImage, width, height);
        } catch (IOException e) {
            System.out.println("ERROR LOAD IMAGE: " + imagePath + imageName);
        }
        return bufferedImage;
    }

    public void setAction() {}
    public void update() {
        setAction();

        collisionOn = FALSE;
        gamePanel.collisionChecker.checkTitle(this);
        gamePanel.collisionChecker.checkObject(this, FALSE);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
        boolean contactPlayer = gamePanel.collisionChecker.checkPlayer(this);

        if(this.type == TYPE_MONSTER && contactPlayer && !gamePanel.player.invincible) {
            gamePanel.player.life -= 1;
            gamePanel.player.invincible = TRUE;
        }

        // IF NOT COLLISION, PLAYER CON MOVE
        if(!collisionOn) {
            switch (direction) {
                case UP -> worldY -= speed;
                case DOWN -> worldY += speed;
                case LEFT -> worldX -= speed;
                case RIGHT -> worldX += speed;
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

        // This needs to be outSide of key if statement!
        if (invincible) {
            invicibleCounter++;
            if (invicibleCounter > 40) {
                invincible = FALSE;
                invicibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
            graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }

        switch (direction) {
            case UP :
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
                break;
            case DOWN :
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
                break;
            case LEFT :
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                break;
            case RIGHT :
                if(spriteNum == 1) image = rigth1;
                if(spriteNum == 2) image = rigth2;
                break;
        }
        if(invincible)
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));

        if(dying)
            dyingAnimation(graphics2D);

        graphics2D.drawImage(image, screenX, screenY, null);

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    private void dyingAnimation(Graphics2D graphics2D) {
        dyingCounter++;
        int i = 5;
        if(dyingCounter < i)
            changeAlpha(graphics2D, 0f);
        if(dyingCounter > i && dyingCounter <= i*2)
            changeAlpha(graphics2D, 1f);
        if(dyingCounter > i*2 && dyingCounter <= i*3)
            changeAlpha(graphics2D, 0f);
        if(dyingCounter > i*3 && dyingCounter <= i*4)
            changeAlpha(graphics2D, 1f);
        if(dyingCounter > i*4 && dyingCounter <= i*5)
            changeAlpha(graphics2D, 0f);
        if(dyingCounter > i*5 && dyingCounter <= i*6)
            changeAlpha(graphics2D, 1f);
        if(dyingCounter > i*6 && dyingCounter <= i*7)
            changeAlpha(graphics2D, 0f);
        if(dyingCounter > i*7 && dyingCounter <= i*8)
            changeAlpha(graphics2D, 1f);
        if(dyingCounter > i*8) {
            dying = FALSE;
            alive = FALSE;
        }
    }

    private void changeAlpha(Graphics2D graphics2D, float alphaValue) {
        graphics2D.setComposite(getInstance(SRC_OVER, alphaValue));
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null)
            dialogueIndex = 0;
        gamePanel.ui.currentDialogues = dialogues[dialogueIndex];
        dialogueIndex++;
        // LOOK IN MY EYES THE PLAYER
        switch (gamePanel.player.direction) {
            case UP:
                direction = DOWN;
                break;
            case DOWN :
                direction = UP;
                break;
            case LEFT :
                direction = RIGHT;
                break;
            case RIGHT:
                direction = LEFT;
                break;
        }
    }
}