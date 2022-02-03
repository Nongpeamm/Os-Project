package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import application.GamePanel;

public class Object_Bomb extends SuperObject{
    GamePanel gp;

    public Object_Bomb() {
        name = "Bomb";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/obj/bomb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
