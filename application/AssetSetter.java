package application;

import java.util.Random;

import object.Object_Bomb;

public class AssetSetter {
    GamePanel gp;
    

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int col = 0;
        int row = 0;
        int count = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
            if(bombRandom(col, row) == true) {
                gp.obj[count] = new Object_Bomb();
                gp.obj[count].worldX = col * gp.tileSize;
                gp.obj[count].worldY = row * gp.tileSize;
            }

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public boolean bombRandom (int col, int row) {
        Random rand = new Random();
        if(col > 0 && col < gp.maxWorldCol - 1 && row > 0 && row < gp.maxWorldRow - 1){
            int bombDetected = rand.nextInt(100);
            if(bombDetected <= 6) {
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
    }
}
