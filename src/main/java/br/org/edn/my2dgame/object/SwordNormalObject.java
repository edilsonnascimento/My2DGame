package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.TYPE_SWORD;

public class SwordNormalObject extends Entity {

    public SwordNormalObject(GamePanel gamePanel) {
        super(gamePanel);

        type = TYPE_SWORD;
        name = "Normal Sword";
        down1 = setup("/objects/", "sword_normal", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";
    }
}