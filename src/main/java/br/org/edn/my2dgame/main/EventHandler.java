package br.org.edn.my2dgame.main;

import java.awt.*;

import static br.org.edn.my2dgame.main.Constants.*;

public class EventHandler {
     GamePanel gamePanel;
     Rectangle eventRectangle;
     int eventRectangleDefaultX, eventRectangleDefaultY;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventRectangle = new Rectangle();
        eventRectangle.x = 23;
        eventRectangle.y = 23;
        eventRectangle.width = 2;
        eventRectangle.height = 2;
        eventRectangleDefaultX = eventRectangle.x;
        eventRectangleDefaultY = eventRectangle.y;
    }

    public void checkEvent() {
        if(hit(27,16, RIGHT))
            damegePit(gamePanel.diologueState);

        if(hit(27,16, RIGHT))
            teleport(gamePanel.diologueState);

        if(hit(23,7, DOWN))
            healingPool(gamePanel.diologueState);
    }

    private void teleport(int gameState) {
        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogues = "Teleport!";
        gamePanel.player.worldX = gamePanel.tileSize * 37;
        gamePanel.player.worldY = gamePanel.tileSize * 10;

    }

    private void damegePit(int gameState) {
        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogues = "You fall into a pit!";
        gamePanel.player.life -= 1;
    }

    public void healingPool(int gameState) {
        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogues = "You drink the water.\n Your life has been recovered.";
        gamePanel.player.life = gamePanel.player.maxLife;
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = Boolean.FALSE;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;
        eventRectangle.x = eventCol * gamePanel.tileSize + eventRectangle.x;
        eventRectangle.y = eventRow * gamePanel.tileSize + eventRectangle.y;

        // check collision
        if(gamePanel.player.solidArea.intersects(eventRectangle)) {
            if(gamePanel.player.direction.contentEquals(reqDirection)|| reqDirection.contentEquals("any")) {
                hit = Boolean.TRUE;
            }
        }

        // reset area
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRectangle.x = eventRectangleDefaultX;
        eventRectangle.y = eventRectangleDefaultY;

        return hit;
    }
}