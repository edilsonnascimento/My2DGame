package br.org.edn.my2dgame.main;

import br.org.edn.my2dgame.entity.Entity;

import static br.org.edn.my2dgame.main.Constants.*;
import static java.util.Objects.nonNull;

public class CollisionChecker {
    GamePanel gamePanel;
    int index = NOT_OBJECTS;
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTitle(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gamePanel.tileSize;
        int entityRightCol = entityRightWorldX/gamePanel.tileSize;
        int entityTopRow = entityTopWorldY/gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY/gamePanel.tileSize;

        int tileNum1 = 0, tileNum2 = 0;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                break;
        }
        setCollision(entity,tileNum1, tileNum2);
    }

    private void setCollision(Entity entity, int tileNum1, int tileNum2) {
        entity.collisionOn = gamePanel.tileManager.tiles[tileNum1].coolision ||
                gamePanel.tileManager.tiles[tileNum2].coolision;
    }

    public int checkObject(Entity entity, boolean player) {
        for (int i = 0; i < gamePanel.objects.length; i++) {
            if(nonNull(gamePanel.objects[i])) {
                // Get Entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;
                // Get Object's solid area position
                gamePanel.objects[i].solidArea.x += gamePanel.objects[i].worldX;
                gamePanel.objects[i].solidArea.y += gamePanel.objects[i].worldY;

                String clicked = "";
                switch (entity.direction) {
                    case UP:
                        entity.solidArea.y -= entity.speed;
                        verifyCollision(entity, player, i);
                        break;
                    case DOWN:
                        entity.solidArea.y += entity.speed;
                        verifyCollision(entity, player, i);
                        break;
                    case LEFT:
                        entity.solidArea.x -= entity.speed;
                        verifyCollision(entity, player, i);
                        break;
                    case RIGHT:
                        entity.solidArea.x += entity.speed;
                        verifyCollision(entity, player, i);
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.getSolidAreaDefaultY;
                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].solidAreaDefaultX;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].solidAreaDefaultY;

            }
        }
        return index;
    }

    private void verifyCollision(Entity entity, boolean player, int i) {
        if(entity.solidArea.intersects(gamePanel.objects[i].solidArea) && player) {
            entity.collisionOn = gamePanel.objects[i].collision;
            index = i;
        }
    }

}