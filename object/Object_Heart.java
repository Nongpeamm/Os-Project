package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import application.GamePanel;

public class Object_Heart extends SuperObject {
    GamePanel gp;

    public Object_Heart(GamePanel gp) {
        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/obj/heart.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
