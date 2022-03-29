package application;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidHitBox.x;
        int entityRightWorldX = entity.worldX + entity.solidHitBox.x + entity.solidHitBox.width;
        int entityTopWorldY = entity.worldY + entity.solidHitBox.y;
        int entityBottomWorldY = entity.worldY + entity.solidHitBox.y + entity.solidHitBox.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTilePattern[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTilePattern[entityRightCol][entityTopRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTilePattern[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTilePattern[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTilePattern[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTilePattern[entityLeftCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTilePattern[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTilePattern[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.Bomb.length; i++) {
            if (gp.Bomb[i] != null) {

                entity.solidHitBox.x = entity.worldX + entity.solidHitBox.x;
                entity.solidHitBox.y = entity.worldY + entity.solidHitBox.y;

                gp.Bomb[i].solidObjHitBox.x = gp.Bomb[i].worldX + gp.Bomb[i].solidObjHitBox.x;
                gp.Bomb[i].solidObjHitBox.y = gp.Bomb[i].worldY + gp.Bomb[i].solidObjHitBox.y;

                switch (entity.direction) {
                    case "up":
                        if (gp.player.turning_flag == "up") {
                            entity.solidHitBox.y -= entity.speed;
                            if (entity.solidHitBox.intersects(gp.Bomb[i].solidObjHitBox)
                                    && gp.Bomb[i].isStepped == false) {
                                if (player == true) {
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "down":
                        if (gp.player.turning_flag == "down") {
                            entity.solidHitBox.y += entity.speed;
                            if (entity.solidHitBox.intersects(gp.Bomb[i].solidObjHitBox)
                                    && gp.Bomb[i].isStepped == false) {
                                if (player == true) {
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "left":
                        if (gp.player.turning_flag == "left") {
                            entity.solidHitBox.x -= entity.speed;
                            if (entity.solidHitBox.intersects(gp.Bomb[i].solidObjHitBox)
                                    && gp.Bomb[i].isStepped == false) {
                                if (player == true) {
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "right":
                        if (gp.player.turning_flag == "right") {
                            entity.solidHitBox.x += entity.speed;
                            if (entity.solidHitBox.intersects(gp.Bomb[i].solidObjHitBox)
                                    && gp.Bomb[i].isStepped == false) {
                                if (player == true) {
                                    index = i;
                                }
                            }
                        }
                        break;
                }
                entity.solidHitBox.x = entity.solidHitBoxDefaultX;
                entity.solidHitBox.y = entity.solidHitBoxDefaultY;
                gp.Bomb[i].solidObjHitBox.x = gp.Bomb[i].solidObjHitBoxDefaultX;
                gp.Bomb[i].solidObjHitBox.y = gp.Bomb[i].solidObjHitBoxDefaultY;

            }
            if (i < gp.maxHealth) {
                if (gp.Health[i] != null) {

                    entity.solidHitBox.x = entity.worldX + entity.solidHitBox.x;
                    entity.solidHitBox.y = entity.worldY + entity.solidHitBox.y;

                    gp.Health[i].solidObjHitBox.x = gp.Health[i].worldX + gp.Health[i].solidObjHitBox.x;
                    gp.Health[i].solidObjHitBox.y = gp.Health[i].worldY + gp.Health[i].solidObjHitBox.y;

                    switch (entity.direction) {
                        case "up":
                            if (gp.player.turning_flag == "up") {
                                entity.solidHitBox.y -= entity.speed;
                                if (entity.solidHitBox.intersects(gp.Health[i].solidObjHitBox)
                                        && gp.Health[i].isStepped == false) {
                                    if (player == true) {
                                        index = i + 2000;
                                    }
                                }
                            }
                            break;
                        case "down":
                            if (gp.player.turning_flag == "down") {
                                entity.solidHitBox.y += entity.speed;
                                if (entity.solidHitBox.intersects(gp.Health[i].solidObjHitBox)
                                        && gp.Health[i].isStepped == false) {
                                    if (player == true) {
                                        index = i + 2000;
                                    }
                                }
                            }
                            break;
                        case "left":
                            if (gp.player.turning_flag == "left") {
                                entity.solidHitBox.x -= entity.speed;
                                if (entity.solidHitBox.intersects(gp.Health[i].solidObjHitBox)
                                        && gp.Health[i].isStepped == false) {
                                    if (player == true) {
                                        index = i + 2000;
                                    }
                                }
                            }
                            break;
                        case "right":
                            if (gp.player.turning_flag == "right") {
                                entity.solidHitBox.x += entity.speed;
                                if (entity.solidHitBox.intersects(gp.Health[i].solidObjHitBox)
                                        && gp.Health[i].isStepped == false) {
                                    if (player == true) {
                                        index = i + 2000;
                                    }
                                }
                            }
                            break;
                    }
                    entity.solidHitBox.x = entity.solidHitBoxDefaultX;
                    entity.solidHitBox.y = entity.solidHitBoxDefaultY;

                    gp.Health[i].solidObjHitBox.x = gp.Health[i].solidObjHitBoxDefaultX;
                    gp.Health[i].solidObjHitBox.y = gp.Health[i].solidObjHitBoxDefaultY;
                }
            }

        }
        return index;
    }
}
