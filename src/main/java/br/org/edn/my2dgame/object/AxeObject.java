package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.TYPE_AXE;

public class AxeObject extends Entity {

    public AxeObject(GamePanel gamePanel) {
        super(gamePanel);

        type = TYPE_AXE;
        name = "Woodcutter's Axe";
        down1 = setup("/objects/","axe", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nAn bit rusty but still\ncan cut some trees.";
    }
}