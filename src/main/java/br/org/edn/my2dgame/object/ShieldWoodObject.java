package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.TYPE_SHIELD;

public class ShieldWoodObject extends Entity {

    public ShieldWoodObject(GamePanel gamePanel) {
        super(gamePanel);

        type = TYPE_SHIELD;
        name = "Wood Shield";
        down1 = setup("/objects/", "shield_wood", gamePanel.tileSize, gamePanel.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nMade by wood.";
    }
}