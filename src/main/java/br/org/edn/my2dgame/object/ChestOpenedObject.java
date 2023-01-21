package br.org.edn.my2dgame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ChestOpenedObject extends SuperObject {
    public ChestOpenedObject() {
        name = "ChestOpened";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_opened.png"));
        } catch (IOException e) {
            System.out.println("Error load object: " + name);
        }
    }
}