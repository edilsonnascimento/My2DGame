package br.org.edn.my2dgame.main;

import br.org.edn.my2dgame.entity.NPCOldMan;
import br.org.edn.my2dgame.object.*;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
           creatNPC();
           createDoor();
    }
    private void creatNPC() {
        gamePanel.npc[0] = new NPCOldMan(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 21;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 21;

        gamePanel.npc[1] = new NPCOldMan(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 11;
        gamePanel.npc[1].worldY = gamePanel.tileSize * 21;

        gamePanel.npc[2] = new NPCOldMan(gamePanel);
        gamePanel.npc[2].worldX = gamePanel.tileSize * 31;
        gamePanel.npc[2].worldY = gamePanel.tileSize * 21;

        gamePanel.npc[3] = new NPCOldMan(gamePanel);
        gamePanel.npc[3].worldX = gamePanel.tileSize * 21;
        gamePanel.npc[3].worldY = gamePanel.tileSize * 11;

        gamePanel.npc[4] = new NPCOldMan(gamePanel);
        gamePanel.npc[4].worldX = gamePanel.tileSize * 21;
        gamePanel.npc[4].worldY = gamePanel.tileSize * 31;
    }

    private void createDoor() {
        gamePanel.objects[0] = new DoorObject(gamePanel);
        gamePanel.objects[0].worldX = gamePanel.tileSize * 21;
        gamePanel.objects[0].worldY = gamePanel.tileSize * 22;

        gamePanel.objects[1] = new DoorObject(gamePanel);
        gamePanel.objects[1].worldX = gamePanel.tileSize * 23;
        gamePanel.objects[1].worldY = gamePanel.tileSize * 25;
    }
}