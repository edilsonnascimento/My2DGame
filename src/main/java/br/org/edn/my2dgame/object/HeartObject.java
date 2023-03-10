package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

public class HeartObject extends Entity {
    public HeartObject(GamePanel gamePanel) {
        super(gamePanel);
        name = "Heart";
        heartFull = setup("/objects/","heart_full", gamePanel.tileSize, gamePanel.tileSize);
        heartBlank = setup("/objects/","heart_blank", gamePanel.tileSize, gamePanel.tileSize);
        heartHalf = setup("/objects/","heart_half", gamePanel.tileSize, gamePanel.tileSize);
    }
}