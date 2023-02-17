package br.org.edn.my2dgame.entity;

import br.org.edn.my2dgame.main.GamePanel;
import br.org.edn.my2dgame.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static br.org.edn.my2dgame.entity.Player.TIME_CHANG_IMAGE;
import static br.org.edn.my2dgame.main.Constants.*;
import static java.lang.Boolean.FALSE;

public class Entity {

    public int worldX, worldY ;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, rigth1, rigth2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 34, 34);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    protected GamePanel gamePanel;
    public int actionLockCounter = 0;
    protected String dialogues[] = new String[20];
    protected int dialogueIndex = 0;
    // CHARACTER STATUS
    public int maxLife;
    public int life;
    
    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    protected  void getEntityImage(String directory) {

    };
    protected BufferedImage setup(String directory, String imageName) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream(directory + imageName + ".png"));
            bufferedImage = utilityTool.scaleImage(bufferedImage, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            System.out.println("ERROR LOAD IMAGE: " + directory + imageName);
        }
        return bufferedImage;
    }

    public void setAction() {}
    public void update() {
        setAction();
        collisionOn = false;
        gamePanel.collisionChecker.checkTitle(this);
        gamePanel.collisionChecker.checkObject(this, FALSE);
        gamePanel.collisionChecker.checkPlayer(this);
        // IF NOT COLLISION, PLAYER CON MOVE
        if(!collisionOn) {
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
                if(spriteNum == 1)
                    image = up1;
                if(spriteNum == 2)
                    image = up2;
                break;
            case DOWN :
                if(spriteNum == 1)
                    image = down1;
                if(spriteNum == 2)
                    image = down2;
                break;
            case LEFT :
                if(spriteNum == 1)
                    image = left1;
                if(spriteNum == 2)
                    image = left2;
                break;
            case RIGHT :
                if(spriteNum == 1)
                    image = rigth1;
                if(spriteNum == 2)
                    image = rigth2;
                break;
        }
        graphics2D.drawImage(image, screenX, screenY, null);
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