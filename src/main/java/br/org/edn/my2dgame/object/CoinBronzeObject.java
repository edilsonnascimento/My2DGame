package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_OBJECT;
import static br.org.edn.my2dgame.main.Constants.TYPE_PICKUP_ONLY;

public class CoinBronzeObject extends Entity {

    public CoinBronzeObject(GamePanel gamePanel) {
        super(gamePanel);

        type = TYPE_PICKUP_ONLY;
        name = "Bronze Coin";
        value = 1;
        down1 = setup(DIRECTORY_BASE_IMAGE_OBJECT,"coin_bronze", gamePanel.tileSize, gamePanel.tileSize);
    }
    @Override
    public void use(Entity entity) {
        gamePanel.playSE(1);
        gamePanel.ui.addMessage("Coin +" + value);
        gamePanel.player.coin += value;
    }
}
