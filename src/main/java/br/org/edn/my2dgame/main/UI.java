package br.org.edn.my2dgame.main;

import java.awt.*;

public class UI {

    private static final int TWO_FRAMES = 120;
    GamePanel gamePanel;
    Graphics2D graphics2D;
    Font arial40;
    Font arial80Bold;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogues = "";

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.arial40 = new Font("Arial", Font.PLAIN, 40);
        this.arial80Bold = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        // PLAY STATE
        if(gamePanel.isStateGamePlay())
            drawPlayerScreen();

        // PAUSE STATE
        if(gamePanel.isStateGamePause())
            drawPauseScreen();

        // DIALOGUES STATE
        if(gamePanel.isStateDialogueState())
            drawDialoguesScreen();

    }

    private void drawPlayerScreen() {
        if(gameFinished) {
            graphics2D.setFont(arial40);
            graphics2D.setColor(Color.WHITE);

            String text = "You found the treasure!";
            int textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            int x = gamePanel.screeWidth/2 - textLength/2;
            int y = gamePanel.screeHeight/2 - (gamePanel.tileSize * 3);
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
}