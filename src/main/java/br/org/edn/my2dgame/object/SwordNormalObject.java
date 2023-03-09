package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

public class SwordNormalObject extends Entity {

    public SwordNormalObject(GamePanel gamePanel) {
        super(gamePanel);

        name = "Normal Sword";
        down1 = setup("/objects/", "sword_normal", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 1;
    }
}