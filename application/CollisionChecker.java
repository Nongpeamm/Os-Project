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

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                entity.solidHitBox.x = entity.worldX + entity.solidHitBox.x;
                entity.solidHitBox.y = entity.worldY + entity.solidHitBox.y;

                gp.obj[i].solidObjHitBox.x = gp.obj[i].worldX + gp.obj[i].solidObjHitBox.x;
                gp.obj[i].solidObjHitBox.y = gp.obj[i].worldY + gp.obj[i].solidObjHitBox.y;

                switch(entity.direction) {
                case "up":
                    entity.solidHitBox.y -= entity.speed;
                    if(entity.solidHitBox.intersects(gp.obj[i].solidObjHitBox) && gp.obj[i].isStepped == false) {
                        gp.player.life -= 1;
                        gp.obj[i].isStepped = true;
                        gp.obj[i].image = null;
                    }
                    break;
                case "down":
                    entity.solidHitBox.y += entity.speed;
                    if(entity.solidHitBox.intersects(gp.obj[i].solidObjHitBox) && gp.obj[i].isStepped == false) {
                        gp.player.life -= 1;
                        gp.obj[i].isStepped = true;
                        gp.obj[i].image = null;
                    }
                    break;
                case "left":
                    entity.solidHitBox.x -= entity.speed;
                    if(entity.solidHitBox.intersects(gp.obj[i].solidObjHitBox) && gp.obj[i].isStepped == false) {
                        gp.player.life -= 1;
                        gp.obj[i].isStepped = true;
                        gp.obj[i].image = null;
                    }
                    break;
                case "right":
                    entity.solidHitBox.x += entity.speed;
                    if(entity.solidHitBox.intersects(gp.obj[i].solidObjHitBox) && gp.obj[i].isStepped == false) {
                        gp.player.life -= 1;
                        gp.obj[i].isStepped = true;
                        gp.obj[i].image = null;
                    }
                    break;
                }
                if(gp.player.life == 0) {
                    gp.gameState = gp.gameOverState;
                }
                entity.solidHitBox.x = entity.solidHitBoxDefaultX;
                entity.solidHitBox.y = entity.solidHitBoxDefaultY;
                gp.obj[i].solidObjHitBox.x = gp.obj[i].solidObjHitBoxDefaultX;
                gp.obj[i].solidObjHitBox.y = gp.obj[i].solidObjHitBoxDefaultY;
            }
        }

        return index;
    }
}
