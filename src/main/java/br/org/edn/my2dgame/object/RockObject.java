package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Projectile;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_PROJECTILE;
import static java.lang.Boolean.FALSE;

public class RockObject extends Projectile {

    public RockObject(GamePanel gamePanel) {
        super(gamePanel);

        name = "Rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = FALSE;
        getImage(DIRECTORY_BASE_IMAGE_PROJECTILE);
    }

    private void getImage(String path) {
        up1 = setup(path, "rock_down_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup(path, "rock_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup(path, "rock_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup(path, "rock_down_1", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup(path, "rock_down_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup(path, "rock_down_1", gamePanel.tileSize, gamePanel.tileSize);
        rigth1 = setup(path, "rock_down_1", gamePanel.tileSize, gamePanel.tileSize);
        rigth2 = setup(path, "rock_down_1", gamePanel.tileSize, gamePanel.tileSize);
    }
}
