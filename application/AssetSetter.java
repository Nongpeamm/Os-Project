package application;

import java.util.Random;

import object.Object_Bomb;

public class AssetSetter {
    GamePanel gp;
    

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int col = 1;
        int row = 1;
        int count = 0;

        while(col < gp.maxWorldCol - 1 && row < gp.maxWorldRow - 1) {
            Random rand = new Random();
            int bombDetected = rand.nextInt(100);

            if(bombDetected <= 3) {
                gp.obj[count] = new Object_Bomb();
                gp.obj[count].worldX = col * gp.tileSize;
                gp.obj[count].worldY = row * gp.tileSize;
                count++;
                if(count == 240) {break;}
            }

            col++;
            if(col == gp.maxWorldCol - 1) {
                col = 1;
                row++;
            }
        }
    }
}
