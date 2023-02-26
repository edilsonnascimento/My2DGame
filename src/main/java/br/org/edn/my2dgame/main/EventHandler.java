package br.org.edn.my2dgame.main;

import static br.org.edn.my2dgame.main.Constants.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class EventHandler {
     GamePanel gamePanel;
     EventRect eventRect[][];
     int previusEventX, previusEventY;
     boolean canTouchEvent = TRUE;
    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventRect = new EventRect[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectangleDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectangleDefaultY = eventRect[col][row].y;

            col++;
            if(col == gamePanel.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {
        // CHECK if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gamePanel.player.worldX - previusEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - previusEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gamePanel.tileSize) {
            canTouchEvent = TRUE;
            gamePanel.player.attackCancelad = TRUE;
        }

        if(canTouchEvent) {
            if (hit(27, 16, RIGHT))
                damegePit(27, 16, gamePanel.diologueState);

            if (hit(23, 7, DOWN))
                healingPool(23, 7, gamePanel.diologueState);
        }
    }
    private void damegePit(int col, int row, int gameState) {
        gamePanel.gameState = gameState;
        gamePanel.playSE(6);
        gamePanel.ui.currentDialogues = "You fall into a pit!";
        gamePanel.player.life -= 1;
        canTouchEvent = FALSE;
    }

    public void healingPool(int col, int row, int gameState) {
        gamePanel.gameState = gameState;
        gamePanel.playSE(2);
        gamePanel.ui.currentDialogues = "You drink the water.\n Your life has been recovered.";
        gamePanel.player.life = gamePanel.player.maxLife;
    }

    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = FALSE;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;
        eventRect[col][row].x = col * gamePanel.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gamePanel.tileSize + eventRect[col][row].y;

        // check collision
        if(gamePanel.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if(gamePanel.player.direction.contentEquals(reqDirection)|| reqDirection.contentEquals("any")) {
                hit = TRUE;
                previusEventX = gamePanel.player.worldX;
                previusEventY = gamePanel.player.worldY;
            }
        }

        // reset area
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectangleDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectangleDefaultY;

        return hit;
    }
}