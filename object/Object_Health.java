package object;

import application.GamePanel;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Object_Health extends SuperObject {
    GamePanel gp;

    public int[] rand_HealthTank = { 50, 55, 60, 65, 70, 75, 80, 85, 90, 95 };
    public Random r = new Random();
    public int randInt = r.nextInt((9 - 0) + 1);
    public int HealthTanknum = rand_HealthTank[randInt];

    public Object_Health() {
        name = "Health";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/obj/medkit.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}