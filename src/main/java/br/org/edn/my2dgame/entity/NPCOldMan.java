package br.org.edn.my2dgame.entity;

import br.org.edn.my2dgame.main.GamePanel;

import java.util.Random;

import static br.org.edn.my2dgame.main.Constants.TWO_FPS;

public class NPCOldMan extends Entity {

    public NPCOldMan(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        speed = 1;
        getEntityImage("/npc/");
    }

    @Override
    public void getEntityImage(String directory) {
        up1 = setup(directory,"oldman_up_1");
        up2 = setup(directory,"oldman_up_2");
        down1 = setup(directory,"oldman_down_1");
        down1 = setup(directory,"oldman_down_1");
        down2 = setup(directory,"oldman_down_2");
        left1 = setup(directory,"oldman_left_1");
        left2 = setup(directory,"oldman_left_2");
        rigth1 = setup(directory,"oldman_right_1");
        rigth2 = setup(directory,"oldman_right_2");
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