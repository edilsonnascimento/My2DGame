package br.org.edn.my2dgame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BootsObjects extends SuperObject {

    public BootsObjects() {
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch (IOException e) {
            System.out.println("Error load object: " + name);
        }
    }
}
