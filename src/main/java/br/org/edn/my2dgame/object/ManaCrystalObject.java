package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_OBJECT;
import static br.org.edn.my2dgame.main.Constants.TYPE_PICKUP_ONLY;

public class ManaCrystalObject extends Entity {

    public ManaCrystalObject(GamePanel gamePanel) {
        super(gamePanel);

        type = TYPE_PICKUP_ONLY;
        name = "Mana Crystal";
        value = 1;
        down1 = setup(DIRECTORY_BASE_IMAGE_OBJECT, "manacrystal_full", gamePanel.tileSize, gamePanel.tileSize);
        crystal_full = setup(DIRECTORY_BASE_IMAGE_OBJECT, "manacrystal_full", gamePanel.tileSize, gamePanel.tileSize);
        crystal_blank = setup(DIRECTORY_BASE_IMAGE_OBJECT, "manacrystal_blank", gamePanel.tileSize, gamePanel.tileSize);
    }

    @Override
    public void use(Entity entity) {
        gamePanel.playSE(2);
        gamePanel.ui.addMessage("Mana +" + value);
        entity.mana += value;
    }
}
