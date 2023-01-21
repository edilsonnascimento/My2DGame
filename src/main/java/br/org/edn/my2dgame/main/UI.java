package br.org.edn.my2dgame.main;

import br.org.edn.my2dgame.object.KeyObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    private static final int TWO_FRAMES = 120;
    GamePanel gamePanel;
    Font arial40;
    Font arial80Bold;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    private double plyaTime;
    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.arial40 = new Font("Arial", Font.PLAIN, 40);
        this.arial80Bold = new Font("Arial", Font.BOLD, 80);
        KeyObject key = new KeyObject(gamePanel);
        this.keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D) {

        if(gameFinished) {
            graphics2D.setFont(arial40);
            graphics2D.setColor(Color.WHITE);

            String text = "You found the treasure!";
            int textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            int x = gamePanel.screeWidth/2 - textLength/2;
            int y = gamePanel.screeHeight/2 - (gamePanel.tileSize * 3);
            graphics2D.drawString(text, x, y);

            text = "You Time is: " + decimalFormat.format(plyaTime) + "!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screeWidth/2 - textLength/2;
            y = gamePanel.screeHeight/2 + (gamePanel.tileSize * 4);
            graphics2D.drawString(text, x, y);

            graphics2D.setFont(arial80Bold);
            graphics2D.setColor(Color.yellow);
            text = "Congratulations";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screeWidth/2 - textLength/2;
            y = gamePanel.screeHeight/2 + (gamePanel.tileSize * 2);
            graphics2D.drawString(text, x, y);

            gamePanel.gameThread = null;
        } else {
            // COUNTER KEY
            int hafTileSize = gamePanel.tileSize / 2;
            graphics2D.setFont(arial40);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawImage(keyImage, hafTileSize, hafTileSize, gamePanel.tileSize, gamePanel.tileSize, null);
            graphics2D.drawString("x " + gamePanel.player.hasKey, 75, 68);

            // TIME
            plyaTime += (double) 1 / gamePanel.FPS;
            graphics2D.drawString("Time: " + decimalFormat.format(plyaTime), gamePanel.tileSize * 11, 65);

            // MESSAGE
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
}