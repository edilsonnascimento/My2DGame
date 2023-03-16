package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_PROJECTILE;

public class BootsObject extends Entity {
    public BootsObject(GamePanel gamePanel) {
        super(gamePanel);
        name = "Boots";
        down1 = setup(DIRECTORY_BASE_IMAGE_PROJECTILE,"boots", gamePanel.tileSize, gamePanel.tileSize);
    }
}