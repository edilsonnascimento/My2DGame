package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.main.GamePanel;

public class BootsObjects extends Entity {
    public BootsObjects(GamePanel gamePanel) {
        super(gamePanel);
        name = "Boots";
        down1 = setup("/objects/","boots");
    }
}