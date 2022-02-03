package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;

import application.GamePanel;
import application.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTilePattern [][];
    Random rand = new Random();

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[21];
        mapTilePattern = new int [gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/res/map/map_pattern.txt");
    }

    public void getTileImage() {
        setup(0, "dirt_tile1", false);
        setup(1, "upper_tile", false);
        setup(2, "bottom_tile", false);
        setup(3, "left_tile", false);
        setup(4, "right_tile", false);
        setup(5, "upper_left_tile", false);
        setup(6, "upper_right_tile", false);
        setup(7, "bottom_left_tile", false);
        setup(8, "bottom_right_tile", false);
        setup(9, "dirt_tile2", false);
        setup(10, "dirt_tile3", false);
        setup(11, "grass_tile", false);
        setup(12, "grass_tile2", false);
        setup(13, "grass_tile3", false);
        setup(14, "upper_fence", true);
        setup(15, "under_fence", true);
        setup(16, "side_fence", true);
        setup(17, "upper_left_fence", true);
        setup(18, "upper_right_fence", true);
        setup(19, "under_left_fence", true);
        setup(20, "under_right_fence", true);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while(col < gp.maxWorldCol) {
                    String numbers [] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTilePattern[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTilePattern[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);  
            }
    
            worldCol++;
            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}