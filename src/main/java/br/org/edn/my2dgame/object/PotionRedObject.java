package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_OBJECT;
import static br.org.edn.my2dgame.main.Constants.TYPE_CONSUMABLE;

public class PotionRedObject extends Entity {

    public PotionRedObject(GamePanel gamePanel) {
        super(gamePanel);
        type = TYPE_CONSUMABLE;
        name = "Red Potion";
        value = 5;
        down1 = setup(DIRECTORY_BASE_IMAGE_OBJECT,"potion_red", gamePanel.tileSize, gamePanel.tileSize);
        description = "[" + name + "]\nHeals your life by " + value + ".";
    }
    @Override
    public void use(Entity entity) {
        gamePanel.playSE(2);
        gamePanel.gameState = gamePanel.diologueState;
        gamePanel.ui.currentDialogues = "You drink the the " + name + "!\n" +
                                        "You life has been recovered\nby " + value + ".";
        entity.life += value;
    }
}
