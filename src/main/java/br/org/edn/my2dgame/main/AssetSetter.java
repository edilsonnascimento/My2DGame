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
    }
    private void creatNPC() {
        gamePanel.npc[0] = new NPCOldMan(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 21;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 21;
    }
}