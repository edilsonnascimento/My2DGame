package br.org.edn.my2dgame.main;

import br.org.edn.my2dgame.entity.NPCOldMan;
import br.org.edn.my2dgame.monster.GreenSlimeMonster;
import br.org.edn.my2dgame.object.*;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
           creatNPC();
           creatMonster();
    }

    private void creatNPC() {
        gamePanel.npc[0] = new NPCOldMan(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 21;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 21;
    }

    private void creatMonster() {
        gamePanel.monster[0] = new GreenSlimeMonster(gamePanel);
        gamePanel.monster[0].worldX = gamePanel.tileSize * 23;
        gamePanel.monster[0].worldY = gamePanel.tileSize * 36;

        gamePanel.monster[1] = new GreenSlimeMonster(gamePanel);
        gamePanel.monster[1].worldX = gamePanel.tileSize * 23;
        gamePanel.monster[1].worldY = gamePanel.tileSize * 37;

    }

}