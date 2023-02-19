package br.org.edn.my2dgame.monster;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

import java.util.Random;

import static br.org.edn.my2dgame.main.Constants.*;

public class GreenSlimeMonster extends Entity {
    public GreenSlimeMonster(GamePanel gamePanel) {
        super(gamePanel);
        type = TYPE_MONSTER;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/", "greenslime_down_1");
        up2 = setup("/monster/", "greenslime_down_2");
        down1 = setup("/monster/", "greenslime_down_1");
        down2 = setup("/monster/", "greenslime_down_2");
        left1 = setup("/monster/", "greenslime_down_1");
        left2 = setup("/monster/", "greenslime_down_2");
        rigth1 = setup("/monster/", "greenslime_down_1");
        rigth2 = setup("/monster/", "greenslime_down_2");
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
    }
}
