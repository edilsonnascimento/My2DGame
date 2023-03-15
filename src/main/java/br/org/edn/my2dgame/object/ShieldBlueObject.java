package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.TYPE_SHIELD;

public class ShieldBlueObject extends Entity {

    public ShieldBlueObject(GamePanel gamePanel) {
        super(gamePanel);

        type = TYPE_SHIELD;
        name = "Blue Shield";
        down1 = setup("/objects/", "shield_blue", gamePanel.tileSize, gamePanel.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA shiny shield.";
    }
}
