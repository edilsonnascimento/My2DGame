package br.org.edn.my2dgame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ChestObject extends SuperObject {
    public ChestObject() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_1.png"));
        } catch (IOException e) {
            System.out.println("Error load object: " + name);
        }
    }
}