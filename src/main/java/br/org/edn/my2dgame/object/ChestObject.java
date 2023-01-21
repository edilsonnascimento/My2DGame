package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ChestObject extends SuperObject {
    public ChestObject(GamePanel gamePanel) {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_1.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            System.out.println("Error load object: " + name);
        }
    }
}