package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_OBJECT;

public class ManaCrystalObjectc extends Entity {

    public ManaCrystalObjectc(GamePanel gamePanel) {
        super(gamePanel);

        name = "Mana Crystal";
        crystal_full = setup(DIRECTORY_BASE_IMAGE_OBJECT, "manacrystal_full", gamePanel.tileSize, gamePanel.tileSize);
        crystal_blank = setup(DIRECTORY_BASE_IMAGE_OBJECT, "manacrystal_blank", gamePanel.tileSize, gamePanel.tileSize);
    }
}
