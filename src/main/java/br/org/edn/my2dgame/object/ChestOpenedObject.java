package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

public class ChestOpenedObject extends Entity {
    public ChestOpenedObject(GamePanel gamePanel) {
        super(gamePanel);
        name = "ChestOpened";
        down1 = setup("/objects/","chest_opened", gamePanel.tileSize, gamePanel.tileSize);
    }
}