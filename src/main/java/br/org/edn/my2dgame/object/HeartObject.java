package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_OBJECT;
import static br.org.edn.my2dgame.main.Constants.TYPE_PICKUP_ONLY;

public class HeartObject extends Entity {
    public HeartObject(GamePanel gamePanel) {
        super(gamePanel);

        type = TYPE_PICKUP_ONLY;
        name = "Heart";
        value = 2;
        getImage(DIRECTORY_BASE_IMAGE_OBJECT);
    }

    private void getImage(String path) {
        down1 = setup(path,"heart_full", gamePanel.tileSize, gamePanel.tileSize);
        heartFull = setup(path,"heart_full", gamePanel.tileSize, gamePanel.tileSize);
        heartHalf = setup(path,"heart_half", gamePanel.tileSize, gamePanel.tileSize);
        heartBlank = setup(path,"heart_blank", gamePanel.tileSize, gamePanel.tileSize);
    }

    @Override
    public void use(Entity entity) {
        gamePanel.playSE(2);
        gamePanel.ui.addMessage("Live +" + value);
        entity.life += value;
    }
}