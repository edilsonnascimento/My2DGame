package br.org.edn.my2dgame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DoorObject extends SuperObject {
    public DoorObject() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException e) {
            System.out.println("Error load object: " + name);
        }
        collision = true;
    }
}