package application;

import java.util.Random;

import object.Object_Bomb;
import object.Object_Health;

public class AssetSetter {
    GamePanel gp;public 
    int count = 0;
    public int Object_Location[][];

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        Object_Location = new int[gp.maxWorldCol][gp.maxWorldRow];
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

    public void RandomNewbomb(){
        Random R = new Random();
        while(true){
            int x =  R.nextInt(gp.maxWorldCol-1) + 1;
            int y =  R.nextInt(gp.maxWorldRow-1) + 1;
            if(Object_Location[x][y] == 0){
                Object_Location[x][y] = 1;
                gp.Bomb[count] = new Object_Bomb();
                gp.Bomb[count].worldX = x * gp.tileSize;
                gp.Bomb[count].worldY = y * gp.tileSize;
                System.out.println("random Bomb at " + x + " " + y);
                count++;
                break;
            }
        }
    }
}
