package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

public class ChestObject extends Entity {
    public ChestObject(GamePanel gamePanel) {
        super(gamePanel);
        name = "Chest";
        down1 = setup("/objects/","chest_opened", gamePanel.tileSize, gamePanel.tileSize);
    }
}