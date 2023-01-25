package br.org.edn.my2dgame.main;

import br.org.edn.my2dgame.entity.NPCOldMan;
import br.org.edn.my2dgame.object.*;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        createBoot();
        createChest();
        createDoor();
        createKey();
        creatNPC();
    }

    private void createDoor() {
        gamePanel.objects[5] = new DoorObject(gamePanel);
        gamePanel.objects[5].worldX = 12 * gamePanel.tileSize;
        gamePanel.objects[5].worldY = 23 * gamePanel.tileSize;

        gamePanel.objects[9] = new DoorObject(gamePanel);
        gamePanel.objects[9].worldX = 14 * gamePanel.tileSize;
        gamePanel.objects[9].worldY = 28 * gamePanel.tileSize;
        gamePanel.objects[3] = new DoorObject(gamePanel);
        gamePanel.objects[3].worldX = 10 * gamePanel.tileSize;
        gamePanel.objects[3].worldY = 11 * gamePanel.tileSize;

        gamePanel.objects[4] = new DoorObject(gamePanel);
        gamePanel.objects[4].worldX =  8 * gamePanel.tileSize;
        gamePanel.objects[4].worldY = 28 * gamePanel.tileSize;
    }

    private void createChest() {
        gamePanel.objects[6] = new ChestObject(gamePanel);
        gamePanel.objects[6].worldX = 10 * gamePanel.tileSize;
        gamePanel.objects[6].worldY =  7 * gamePanel.tileSize;
    }

    private void createBoot() {
        gamePanel.objects[7] = new BootsObjects(gamePanel);
        gamePanel.objects[7].worldX = 37 * gamePanel.tileSize;
        gamePanel.objects[7].worldY = 42 * gamePanel.tileSize;
    }

    private void createKey() {
        gamePanel.objects[0] = new KeyObject(gamePanel);
        gamePanel.objects[0].worldX = 23 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 7 * gamePanel.tileSize;

        gamePanel.objects[1] = new KeyObject(gamePanel);
        gamePanel.objects[1].worldX = 23 * gamePanel.tileSize;
        gamePanel.objects[1].worldY = 40 * gamePanel.tileSize;

        gamePanel.objects[2] = new KeyObject(gamePanel);
        gamePanel.objects[2].worldX = 38 * gamePanel.tileSize;
        gamePanel.objects[2].worldY =  8 * gamePanel.tileSize;

        gamePanel.objects[8] = new KeyObject(gamePanel);
        gamePanel.objects[8].worldX = 35 * gamePanel.tileSize;
        gamePanel.objects[8].worldY = 38 * gamePanel.tileSize;
    }

    private void creatNPC() {
        gamePanel.npc[0] = new NPCOldMan(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 21;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 21;
        System.out.println("CRIOU NPC");
    }
}