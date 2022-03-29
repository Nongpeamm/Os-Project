package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import application.GamePanel;
import application.UtilityTool;
import java.awt.*;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean isStepped = false;
    public int worldX, worldY;
    public Rectangle solidObjHitBox = new Rectangle(0, 0, 32, 32);
    public int solidObjHitBoxDefaultX = 0;
    public int solidObjHitBoxDefaultY = 0;

    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void drawHealthTanknum(Graphics2D g2, GamePanel gp) {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
        g2.setColor(Color.black);
        for (int i = 0; i < gp.Health.length; i++) {
            if (gp.Health[i] != null) {
                if (gp.Health[i].HealthTanknum > 0) {

                    int screenX = gp.Health[i].worldX - gp.player.worldX + gp.player.screenX;
                    int screenY = gp.Health[i].worldY - gp.player.worldY + gp.player.screenY;

                    if (gp.Health[i].worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                            gp.Health[i].worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                            gp.Health[i].worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                            gp.Health[i].worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                        g2.drawString(" H: " + gp.Health[i].HealthTanknum, screenX - 15, screenY);
                    }
                }
            }
        }
    }
}
