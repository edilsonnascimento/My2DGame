package br.org.edn.my2dgame.object;

import br.org.edn.my2dgame.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class HeartObject extends SuperObject {
    public HeartObject(GamePanel gamePanel) {
        name = "Heart";
        try {
            heartFull = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            heartFull = utilityTool.scaleImage(heartFull, gamePanel.tileSize, gamePanel.tileSize);
            heartBlank = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            heartBlank = utilityTool.scaleImage(heartBlank, gamePanel.tileSize, gamePanel.tileSize);
            heartHalf = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            heartHalf = utilityTool.scaleImage(heartHalf, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            System.out.println("Error load object: " + name);
        }
    }
}