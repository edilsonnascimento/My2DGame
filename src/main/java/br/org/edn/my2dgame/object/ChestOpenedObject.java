package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ChestOpenedObject extends SuperObject {
    private GamePanel gamePanel;

    public ChestOpenedObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "ChestOpened";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_opened.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            System.out.println("Error load object: " + name);
        }
    }
}