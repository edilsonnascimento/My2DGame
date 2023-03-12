package br.org.edn.my2dgame.main;

import br.org.edn.my2dgame.entity.Entity;

import static br.org.edn.my2dgame.main.Constants.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.nonNull;

public class CollisionChecker {
    GamePanel gamePanel;
    int index = NOT_OBJECTS;
    boolean contactPlayer;
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTitle(Entity entity) {

        // Position solidArea actual
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        //
        int entityLeftCol = entityLeftWorldX/gamePanel.tileSize;
        int entityRightCol = entityRightWorldX/gamePanel.tileSize;
        int entityTopRow = entityTopWorldY/gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY/gamePanel.tileSize;

        int tileNum1 = 0, tileNum2 = 0;

        switch (entity.direction) {
            case UP:
                entityTopRow = (entityTopWorldY - entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                break;
            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                break;
            case RIGHT:
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
        index = NOT_OBJECTS;
        for (int i = 0; i < gamePanel.objects.length; i++) {
            if(nonNull(gamePanel.objects[i])) {
                // Get Entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;
                // Get Object's solid area position
                gamePanel.objects[i].solidArea.x += gamePanel.objects[i].worldX;
                gamePanel.objects[i].solidArea.y += gamePanel.objects[i].worldY;
                changeDirection(entity);
                verifyCollision(entity, player, i);
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].solidAreaDefaultX;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].solidAreaDefaultY;

            }
        }
        return index;
    }

    private void changeDirection(Entity entity) {
        switch (entity.direction) {
            case UP:
                entity.solidArea.y -= entity.speed;
                break;
            case DOWN:
                entity.solidArea.y += entity.speed;
                break;
            case LEFT:
                entity.solidArea.x -= entity.speed;
                break;
            case RIGHT:
                entity.solidArea.x += entity.speed;
                break;
        }
    }

    private void verifyCollision(Entity entity, boolean player, int i) {
        if(entity.solidArea.intersects(gamePanel.objects[i].solidArea) && player) {
            entity.collisionOn = gamePanel.objects[i].collision;
            index = i;
        }
    }

    // NPC OR MONSTER
    public int checkEntity(Entity entity, Entity[] entities) {
        index = NOT_OBJECTS;
        for (int i = 0; i < entities.length; i++) {
            if(nonNull(entities[i])) {
                // Get Entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;
                // Get Object's solid area position
                entities[i].solidArea.x += entities[i].worldX;
                entities[i].solidArea.y += entities[i].worldY;
                changeDirection(entity);
                verifyEntityCollision(entities, entity, i);
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                entities[i].solidArea.x = entities[i].solidAreaDefaultX;
                entities[i].solidArea.y = entities[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    private void verifyEntityCollision(Entity[] entities, Entity entity, int i) {
        if(entity.solidArea.intersects(entities[i].solidArea) && entities[i] != entity) {
            entity.collisionOn = TRUE;
            index = i;
        }
    }

    public boolean checkPlayer(Entity entity) {
        contactPlayer = FALSE;

        // Get Entity's solid area position
        entity.solidArea.x += entity.worldX;
        entity.solidArea.y += entity.worldY;
        // Get Object's solid area position
        gamePanel.player.solidArea.x += gamePanel.player.worldX;
        gamePanel.player.solidArea.y += gamePanel.player.worldY;
        changeDirection(entity);
        verifyPlayerCollision(entity);
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;

        return contactPlayer;
    }

    private void verifyPlayerCollision(Entity entity) {
        if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
            entity.collisionOn = TRUE;
            contactPlayer = TRUE;
        }
    }
}