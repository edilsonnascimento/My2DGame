package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DoorObject extends SuperObject {
    private GamePanel gamePanel;

    public DoorObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            System.out.println("Error load object: " + name);
        }
        collision = true;
    }
}