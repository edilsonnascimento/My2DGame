package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_OBJECT;

public class HeartObject extends Entity {
    public HeartObject(GamePanel gamePanel) {
        super(gamePanel);
        name = "Heart";
        getImage(DIRECTORY_BASE_IMAGE_OBJECT);
    }

    private void getImage(String path) {
        heartFull = setup(path,"heart_full", gamePanel.tileSize, gamePanel.tileSize);
        heartBlank = setup(path,"heart_blank", gamePanel.tileSize, gamePanel.tileSize);
        heartHalf = setup(path,"heart_half", gamePanel.tileSize, gamePanel.tileSize);
    }
}