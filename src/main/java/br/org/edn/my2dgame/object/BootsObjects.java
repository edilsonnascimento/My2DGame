package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BootsObjects extends SuperObject {

    private GamePanel gamePanel;

    public BootsObjects(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            System.out.println("Error load object: " + name);
        }
    }
}