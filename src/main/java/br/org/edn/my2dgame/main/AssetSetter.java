package br.org.edn.my2dgame.main;

import br.org.edn.my2dgame.entity.NPCOldMan;
import br.org.edn.my2dgame.monster.GreenSlimeMonster;
import br.org.edn.my2dgame.object.AxeObject;
import br.org.edn.my2dgame.object.KeyObject;
import br.org.edn.my2dgame.object.ShieldBlueObject;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
       creatNPC();
       creatMonster();
       createObjects();
    }

    public void creatNPC() {
        gamePanel.npc[0] = new NPCOldMan(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 21;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 21;
    }

    public void creatMonster() {
        int i = 0;
        gamePanel.monster[i] = new GreenSlimeMonster(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize * 23;
        gamePanel.monster[i].worldY = gamePanel.tileSize * 36;
        i++;
        gamePanel.monster[i] = new GreenSlimeMonster(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize * 23;
        gamePanel.monster[i].worldY = gamePanel.tileSize * 37;
        i++;
        gamePanel.monster[i] = new GreenSlimeMonster(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize * 24;
        gamePanel.monster[i].worldY = gamePanel.tileSize * 35;
        i++;
        gamePanel.monster[i] = new GreenSlimeMonster(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize * 34;
        gamePanel.monster[i].worldY = gamePanel.tileSize * 40;
        i++;
        gamePanel.monster[i] = new GreenSlimeMonster(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize * 38;
        gamePanel.monster[i].worldY = gamePanel.tileSize * 40;
        i++;
        gamePanel.monster[i] = new GreenSlimeMonster(gamePanel);
        gamePanel.monster[i].worldX = gamePanel.tileSize * 23;
        gamePanel.monster[i].worldY = gamePanel.tileSize * 37;
    }

    private void createObjects() {
        int i = 0;
        gamePanel.objects[i] = new KeyObject(gamePanel);
        gamePanel.objects[i].worldX = gamePanel.tileSize * 25;
        gamePanel.objects[i].worldY = gamePanel.tileSize * 23;
        i++;
        gamePanel.objects[i] = new KeyObject(gamePanel);
        gamePanel.objects[i].worldX = gamePanel.tileSize * 21;
        gamePanel.objects[i].worldY = gamePanel.tileSize * 19;
        i++;
        gamePanel.objects[i] = new KeyObject(gamePanel);
        gamePanel.objects[i].worldX = gamePanel.tileSize * 26;
        gamePanel.objects[i].worldY = gamePanel.tileSize * 21;
        i++;
        gamePanel.objects[i] = new KeyObject(gamePanel);
        gamePanel.objects[i].worldX = gamePanel.tileSize * 16;
        gamePanel.objects[i].worldY = gamePanel.tileSize * 22;
        i++;
        gamePanel.objects[i] = new AxeObject(gamePanel);
        gamePanel.objects[i].worldX = gamePanel.tileSize * 33;
        gamePanel.objects[i].worldY = gamePanel.tileSize * 21;
        i++;
        gamePanel.objects[i] = new ShieldBlueObject(gamePanel);
        gamePanel.objects[i].worldX = gamePanel.tileSize * 35;
        gamePanel.objects[i].worldY = gamePanel.tileSize * 21;
    }
}