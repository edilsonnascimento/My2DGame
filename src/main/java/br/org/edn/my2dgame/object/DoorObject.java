package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_PROJECTILE;
import static java.lang.Boolean.TRUE;

public class DoorObject extends Entity {
    public DoorObject(GamePanel gamePanel) {
        super(gamePanel);
        name = "Door";
        down1 = setup(DIRECTORY_BASE_IMAGE_PROJECTILE, "door", gamePanel.tileSize, gamePanel.tileSize);
        collision = TRUE;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}