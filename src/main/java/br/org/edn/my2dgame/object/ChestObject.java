package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_PROJECTILE;

public class ChestObject extends Entity {
    public ChestObject(GamePanel gamePanel) {
        super(gamePanel);
        name = "Chest";
        down1 = setup(DIRECTORY_BASE_IMAGE_PROJECTILE,"chest_opened", gamePanel.tileSize, gamePanel.tileSize);
    }
}