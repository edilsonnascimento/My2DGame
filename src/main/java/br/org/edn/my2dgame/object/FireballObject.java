package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Projectile;
import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_IMAGE_PROJECTILE;
import static java.lang.Boolean.FALSE;

public class FireballObject extends Projectile {

    public FireballObject(GamePanel gamePanel) {
        super(gamePanel);

        name = "Fireball";
        speed = 10;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = FALSE;
        getImage(DIRECTORY_BASE_IMAGE_PROJECTILE);
    }

    private void getImage(String path) {
        up1 = setup(path,"fireball_up_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup(path,"fireball_up_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup(path,"fireball_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup(path,"fireball_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup(path,"fireball_left_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup(path,"fireball_left_2", gamePanel.tileSize, gamePanel.tileSize);
        rigth1 = setup(path,"fireball_right_1", gamePanel.tileSize, gamePanel.tileSize);
        rigth2 = setup(path,"fireball_right_2", gamePanel.tileSize, gamePanel.tileSize);
    }
}
