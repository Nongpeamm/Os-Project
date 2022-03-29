package application;

import java.util.Random;

import object.Object_Bomb;
import object.Object_Health;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int col = 1;
        int row = 1;
        int bomb_count = 0;
        int health_count = 0;

        while (col < gp.maxWorldCol - 1 && row < gp.maxWorldRow - 1) {

            Random rand = new Random(); // random bomb
            int random = rand.nextInt(100);

            if (col == 50 && row == 40) {
                col++;
            }

            if (random <= 2) {
                gp.Bomb[bomb_count] = new Object_Bomb();
                gp.Bomb[bomb_count].worldX = col * gp.tileSize;
                gp.Bomb[bomb_count].worldY = row * gp.tileSize;
                bomb_count++;
                if (bomb_count == gp.maxBomb) {
                    break;
                }
            }

            if (random == 3) {
                gp.Health[health_count] = new Object_Health();
                gp.Health[health_count].worldX = col * gp.tileSize;
                gp.Health[health_count].worldY = row * gp.tileSize;
                health_count++;
                if (health_count == gp.maxHealth) {
                    break;
                }
            }

            col++;
            if (col == gp.maxWorldCol - 1) {
                col = 1;
                row++;
            }
        }
    }
}
