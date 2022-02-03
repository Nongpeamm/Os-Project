package entity;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import application.GamePanel;
import application.KeyHandler;
import application.UtilityTool;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public int screenX;
    public int screenY;
    int b2StandCounter;
    String turning_flag = "down";

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;       
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidHitBox = new Rectangle(15, 15, 2, 2);
        solidHitBoxDefaultX = solidHitBox.x;
        solidHitBoxDefaultY = solidHitBox.y;

        setDefault();
        getPlayerImage();
    }

    public void setDefault() {
        worldX = gp.tileSize * 50;
        worldY =  gp.tileSize * 40;

        speed = gp.tileSize;
        direction = "down";

        maxLife = 3;
        life = 3;
    }

    public void getPlayerImage() {
        up1 = setup("player_up1");
        up2 = setup("player_up2");
        up3 = setup("player_up3");
        up4 = setup("player_up4");
        down1 = setup("player_down1");
        down2 = setup("player_down2");
        down3 = setup("player_down3");
        down4 = setup("player_down4");
        left1 = setup("player_left1");
        left2 = setup("player_left2");
        left3 = setup("player_left3");
        left4 = setup("player_left4");
        right1 = setup("player_right1");
        right2 = setup("player_right2");
        right3 = setup("player_right3");
        right4 = setup("player_right4");
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true) {
                direction = "up";
                keyH.upPressed = false;
            }
            else if(keyH.downPressed == true) {
                direction = "down";
                keyH.downPressed = false;
            }
            else if(keyH.leftPressed == true) {
                direction = "left";
                keyH.leftPressed = false;
            }
            else if(keyH.rightPressed == true) {
                direction = "right";
                keyH.rightPressed = false;
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);

            if(collisionOn == false) {
                switch(direction) {
                case "up":
                    if(turning_flag != "up") {
                        turning_flag = "up";
                    } else {
                        worldY -= speed;
                    }
                    break;
    
                case "down":
                    if(turning_flag != "down") {
                        turning_flag = "down";
                    } else {
                        worldY += speed;
                    }
                    break;
        
                case "left":
                    if(turning_flag != "left") {
                        turning_flag = "left";
                    } else {
                        worldX -= speed;
                    }
                    break;
        
                case "right":
                    if(turning_flag != "right") {
                        turning_flag = "right";
                    } else {
                        worldX += speed;
                    }
                    break;
                }
            }
        
            spriteCounter++;
            if(spriteCounter > 0) {
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if(spriteNum == 2) {
                    spriteNum = 3;
                } else if(spriteNum == 3) {
                    spriteNum = 4;
                } else if(spriteNum == 4) {
                    spriteNum = 2;
                }
                spriteCounter = 0;
            }
        } else{
            b2StandCounter++;
            if(b2StandCounter == 2) {
                spriteNum = 1;
                b2StandCounter = 0;
            }
        }
    }
    

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
        case "up":
            if(spriteNum == 1) {image = up1;}
            if(spriteNum == 2) {image = up2;}
            if(spriteNum == 3) {image = up3;}
            if(spriteNum == 4) {image = up4;}
            break;
        case "down":
            if(spriteNum == 1) {image = down1;}
            if(spriteNum == 2) {image = down2;}
            if(spriteNum == 3) {image = down3;}
            if(spriteNum == 4) {image = down4;}
            break;
        case "left":
            if(spriteNum == 1) {image = left1;}
            if(spriteNum == 2) {image = left2;}
            if(spriteNum == 3) {image = left3;}
            if(spriteNum == 4) {image = left4;}
            break;
        case "right":
            if(spriteNum == 1) {image = right1;}
            if(spriteNum == 2) {image = right2;}
            if(spriteNum == 3) {image = right3;}
            if(spriteNum == 4) {image = right4;}
            break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}
