package br.org.edn.my2dgame.entity;

import br.org.edn.my2dgame.main.GamePanel;

import java.util.Random;

import static br.org.edn.my2dgame.main.Constants.*;

public class NPCOldMan extends Entity {

    public NPCOldMan(GamePanel gamePanel) {
        super(gamePanel);
        type = TYPE_NPC;
        direction = DOWN;
        speed = 1;
        getNpcImage("/npc/");
        setDialogue();
    }

    public void getNpcImage(String directory) {
        up1 = setup(directory,"oldman_up_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup(directory,"oldman_up_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup(directory,"oldman_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup(directory,"oldman_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup(directory,"oldman_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup(directory,"oldman_left_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup(directory,"oldman_left_2", gamePanel.tileSize, gamePanel.tileSize);
        rigth1 = setup(directory,"oldman_right_1", gamePanel.tileSize, gamePanel.tileSize);
        rigth2 = setup(directory,"oldman_right_2", gamePanel.tileSize, gamePanel.tileSize);
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

    public void setDialogue() {
        dialogues[0] = "Helo, lad.";
        dialogues[1] = "So you've come to this island to \nfind the treasure?";
        dialogues[2] = "I used to be a great wizard but now... \nI'm a bit too old for talking an adventure.";
        dialogues[3] = "Well, good luck on you.";
    }

    @Override
    public void speak() {
        super.speak();
    }
}