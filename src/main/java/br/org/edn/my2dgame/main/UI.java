package br.org.edn.my2dgame.main;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.object.HeartObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON;

public class UI {

    private static final int TWO_FRAMES = 120;
    GamePanel gamePanel;
    Graphics2D graphics2D;
    public Font purisaBold;
    public Font beefD;
    BufferedImage heartFull, heartHalf, heartBlanck;
    public Font safachrome;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogues = "";
    public int commandNumber = 0;
    public int titleScreenState = 0;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.purisaBold = returnFont("/fonts/Purisa_Bold.ttf");
        this.beefD = returnFont("/fonts/Beef_d.ttf");
        this.safachrome = returnFont("/fonts/sofachrome rg.otf");
        Entity heart = new HeartObject(gamePanel);
        heartFull = heart.heartFull;
        heartHalf = heart.heartHalf;
        heartBlanck = heart.heartBlank;
    }

    public Font returnFont(String path) {
        InputStream inputStream = getClass().getResourceAsStream(path);
        try {
            return Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D) {
        setFont(safachrome, graphics2D);
        // TITLE STATE
        if(gamePanel.isStateTitle()) {
            drawTitleScreen();
        }
        // PLAY STATE
        if(gamePanel.isStateGamePlay())
            drawPlayerLife();

        // PAUSE STATE
        if(gamePanel.isStateGamePause()) {
            drawPlayerLife();
            drawPauseScreen();
        }

        // DIALOGUES STATE
        if(gamePanel.isStateDialogue()) {
            setFont(purisaBold, graphics2D);
            drawPlayerLife();
            drawDialoguesScreen();
            setFont(safachrome, graphics2D);
        }

        // CHARACTER STATE
        if(gamePanel.isCharacterState()) {
           drawCharacterScreen();
        }
    }

    public void setFont(Font font, Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        this.graphics2D.setFont(font);
        this.graphics2D.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
        this.graphics2D.setColor(Color.white);

    }

    private void drawPlayerLife() {
        int x = gamePanel.tileSize/2;
        int y = gamePanel.tileSize/2;
        int i = 0;
        int treeHearts = gamePanel.player.maxLife/2;

        // DRAW MAX LIFE
        while (i < treeHearts) {
            graphics2D.drawImage(heartBlanck, x, y, null);
            i++;
            x += gamePanel.tileSize;
        }

        // RESET
        x = gamePanel.tileSize/2;
        y = gamePanel.tileSize/2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gamePanel.player.life) {
            graphics2D.drawImage(heartHalf, x, y, null);
            i++;
            if(i < gamePanel.player.life) {
                graphics2D.drawImage(heartFull, x, y, null);
            }
            i++;
            x += gamePanel.tileSize;
        }
    }
    private void drawTitleScreen() {
        // TITLE NAME
        if(titleScreenState == 0) {
            graphics2D.setColor(new Color(0, 0, 0));
            graphics2D.fillRect(0, 0, gamePanel.screeWidth, gamePanel.screeHeight);
            graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 35F));
            String text = "Blue Boy Adventure";
            int x = getXForCenteredText(text);
            int y = gamePanel.tileSize * 3;

            // SHADOW
            graphics2D.setColor(Color.gray);
            graphics2D.drawString(text, x + 5, y + 5);

            // MAIN COLOR
            graphics2D.setColor(Color.white);
            graphics2D.drawString(text, x, y);

            // BLUE BOY IMAGE
            x = gamePanel.screeWidth / 2 - (gamePanel.tileSize * 2) / 2;
            y += gamePanel.tileSize * 2;
            graphics2D.drawImage(gamePanel.player.down1, x, y, gamePanel.tileSize * 2, gamePanel.tileSize * 2, null);

            // MENU
            graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 28F));
            text = "NEW GAME";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize * 3.5;
            graphics2D.drawString(text, x, y);
            if (commandNumber == 0)
                selectOption(x, y);

            text = "LOAD GAME";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize;
            graphics2D.drawString(text, x, y);
            if (commandNumber == 1)
                selectOption(x, y);

            text = "QUIT";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize;
            graphics2D.drawString(text, x, y);
            if (commandNumber == 2)
                selectOption(x, y);
        } else if( titleScreenState == 1){
            // CLASSE SELECTION SCREEN
            graphics2D.setColor(Color.white);
            graphics2D.setFont(graphics2D.getFont().deriveFont((30F)));
            String text = "Select your class!";
            int x = getXForCenteredText(text);
            int y = gamePanel.tileSize * 3;
            graphics2D.drawString(text, x, y);

            text = "Figther";
            x = getXForCenteredText((text));
            y += gamePanel.tileSize * 3;
            graphics2D.drawString(text, x, y);
            if (commandNumber == 0) {
                graphics2D.drawString(">", x - gamePanel.tileSize, y);
            }
            text = "Thief";
            x = getXForCenteredText((text));
            y += gamePanel.tileSize;
            graphics2D.drawString(text, x, y);
            if (commandNumber == 1) {
                graphics2D.drawString(">", x - gamePanel.tileSize, y);
            }
            text = "Sorcer";
            x = getXForCenteredText((text));
            y += gamePanel.tileSize;
            graphics2D.drawString(text, x, y);
            if (commandNumber == 2) {
                graphics2D.drawString(">", x - gamePanel.tileSize, y);
            }
            text = "Back";
            x = getXForCenteredText((text));
            y += gamePanel.tileSize * 2;
            graphics2D.drawString(text, x, y);
            if (commandNumber == 3) {
                graphics2D.drawString(">", x - gamePanel.tileSize, y);
            }
        }
    }

    private void drawCharacterScreen() {
        // CREATE A FRAME
        final int frameX = gamePanel.tileSize;
        final int frameY = gamePanel.tileSize;
        final int frameWidth = gamePanel.tileSize * 5;
        final int frameHeight = gamePanel.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        graphics2D.setColor(Color.white);
        graphics2D.setFont(gamePanel.getFont().deriveFont(25F));

        int textX = frameX + 20;
        int textY = frameY + gamePanel.tileSize;
        final int lineHeight = 35;

        // NAMES
        graphics2D.drawString("Level", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Life", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Strength", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Attack", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Defense", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Exp", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Next Level", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Coin", textX, textY);
        textY += lineHeight + 20;
        graphics2D.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        graphics2D.drawString("Shield", textX, textY);

        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        // Reset textY
        textY = frameY + gamePanel.tileSize;
        String value;

        value = String.valueOf(gamePanel.player.level);
        textX = getXForAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        String life = String.valueOf(gamePanel.player.life);
        String maxLife = String.valueOf(gamePanel.player.maxLife);
        value =  life + "/" + maxLife;
        textX = getXForAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        graphics2D.drawImage(gamePanel.player.currentWeapon.down1, tailX - gamePanel.tileSize, textY - 14, null);
        textY += gamePanel.tileSize;
        graphics2D.drawImage(gamePanel.player.currentShield.down1, tailX - gamePanel.tileSize, textY - 14, null);


    }

    private void selectOption(int x, int y) {
        graphics2D.drawString(">", x-gamePanel.tileSize, y);
    }


    private void drawPlayerScreen() {
        if(gameFinished) {
            graphics2D.setFont(purisaBold);
            graphics2D.setColor(Color.WHITE);

            String text = "You found the treasure!";
            int textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            int x = gamePanel.screeWidth/2 - textLength/2;
            int y = gamePanel.screeHeight/2 - (gamePanel.tileSize * 3);
            graphics2D.drawString(text, x, y);

            graphics2D.setFont(purisaBold);
            graphics2D.setColor(Color.yellow);
            text = "Congratulations";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screeWidth/2 - textLength/2;
            y = gamePanel.screeHeight/2 + (gamePanel.tileSize * 2);
            graphics2D.drawString(text, x, y);

            gamePanel.gameThread = null;
        } else {
            // MESSAGE
            int hafTileSize = gamePanel.tileSize / 2;
            if (messageOn) {
                graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
                graphics2D.drawString(message, hafTileSize, gamePanel.tileSize * 5);

                messageCounter++;
                if (messageCounter > TWO_FRAMES) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    private void drawPauseScreen() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 80F));
        graphics2D.setColor(Color.white);
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gamePanel.screeHeight/2;
        graphics2D.drawString(text, x, y);
    }
    private void drawDialoguesScreen() {
        // WINDOW
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screeWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.tileSize * 4;
        drawSubWindow(x, y, width, height);

        // TEXT dialogue
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 28F));
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        for (String line: currentDialogues.split("\n")) {
            graphics2D.drawString(line, x, y);
            y += 40;
        }    
    }
    private void drawSubWindow(int x, int y, int width, int height) {
        // BOX
        Color color = new Color(0,0,0, 210);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(x, y, width, height, 35,35);

        // BORDER
        color = new Color(255,255,255);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x+5, y+5, width-10,height-10, 25,25);
    }

    private int getXForCenteredText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return gamePanel.screeWidth/2 - length/2;
    }

    private int getXForAlignToRightText(String text, int tailX) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return tailX - length;
    }
}