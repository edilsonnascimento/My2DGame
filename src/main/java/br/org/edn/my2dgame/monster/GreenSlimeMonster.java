package br.org.edn.my2dgame.monster;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;
import br.org.edn.my2dgame.object.RockObject;

import java.util.Random;

import static br.org.edn.my2dgame.main.Constants.*;
import static java.lang.Boolean.TRUE;

public class GreenSlimeMonster extends Entity {
    public GreenSlimeMonster(GamePanel gamePanel) {
        super(gamePanel);

        type = TYPE_MONSTER;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new RockObject(gamePanel);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage(DIRECTORY_BASE_IMAGE_MONSTER);
    }

    public void getImage(String path) {
        up1 = setup(path, "greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup(path, "greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup(path, "greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup(path, "greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup(path, "greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup(path, "greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);
        rigth1 = setup(path, "greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        rigth2 = setup(path, "greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setAction() {
        Random random = new Random();
        actionLockCounter++;

        if(actionLockCounter == TWO_FPS) {
            int i = random.nextInt(100) + 1;
            if (i <= 25)
                direction = "up";
            if (i > 25 && i <= 50)
                direction = "down";
            if (i > 50 && i <= 75)
                direction = "left";
            if (i > 75 && i <= 100)
                direction = "right";
            actionLockCounter = 0;
        }
        int i = new Random().nextInt(100) + 1;
        if(i > 99 && !projectile.alive && shotAvailableCounter == 30) {
            projectile.set(worldX, worldY, direction, TRUE, this);
            gamePanel.projectiles.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    @Override
    public void damageReaction() {
        actionLockCounter = 0;
        direction = gamePanel.player.direction;
    }
}