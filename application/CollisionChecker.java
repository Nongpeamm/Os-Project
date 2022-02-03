package application;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidHitBox.x;
        int entityRightWorldX = entity.worldX + entity.solidHitBox.x + entity.solidHitBox.width;
        int entityTopWorldY = entity.worldY + entity.solidHitBox.y;
        int entityBottomWorldY = entity.worldY + entity.solidHitBox.y + entity.solidHitBox.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
        case "up":
            entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTilePattern[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTilePattern[entityRightCol][entityTopRow];

            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            break;
        case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTilePattern[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTilePattern[entityRightCol][entityBottomRow];

            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            break;
        case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTilePattern[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTilePattern[entityLeftCol][entityBottomRow];

            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            break;
        case "right":
            entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTilePattern[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTilePattern[entityRightCol][entityBottomRow];

            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
            break;
        }
    }
}
