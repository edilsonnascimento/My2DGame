package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

public class KeyObject extends Entity {

    public KeyObject(GamePanel gamePanel) {
        super(gamePanel);
        name = "Key";
        down1 = setup("/objects/","key.png", gamePanel.tileSize, gamePanel.tileSize);
    }
}