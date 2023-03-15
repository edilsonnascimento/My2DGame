package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_OBJECT;

public class KeyObject extends Entity {

    public KeyObject(GamePanel gamePanel) {
        super(gamePanel);
        name = "Key";
        down1 = setup(DIRECTORY_BASE_IMAGE_OBJECT, "key", gamePanel.tileSize, gamePanel.tileSize);
        description = "[" + name + "]\nIt opens a door.";
    }
}