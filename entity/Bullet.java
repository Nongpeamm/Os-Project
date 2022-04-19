package entity;
import javax.imageio.ImageIO;
import java.io.IOException;
import application.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Bullet extends Entity{

    GamePanel gp;
    Thread bulletThread;

    public Bullet(GamePanel gp, int X, int Y, String Direction){
        // super(gp);
        this.gp = gp;
        getBulletImage();
        setDefaultValues();
        worldX = X;
        worldY = Y;
        direction = Direction;
        bulletThread = new Thread();
        bulletThread.start();
    }

    public void setDefaultValues(){
        solidHitBox = new Rectangle(20, 20, 12, 5);
        solidHitBoxDefaultX = solidHitBox.x;
        solidHitBoxDefaultY = solidHitBox.y;
        speed = 5;
    }

    public void getBulletImage(){
        try{
            bullet1 = ImageIO.read(getClass().getResourceAsStream("/res/bullet/ball.png"));
            // down1 = ImageIO.read(getClass().getResourceAsStream("/res/bullet/ball.png"));
            // left1 = ImageIO.read(getClass().getResourceAsStream("/res/bullet/ball.png"));
            // right1 = ImageIO.read(getClass().getResourceAsStream("/res/bullet/ball.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public boolean update(){
        collisionOn = false;
        gp.cChecker.checkTile(this);
        int objIndex = gp.cChecker.checkObject(this, true);
            hitObject(objIndex);
        if(collisionOn)
            return false;
        else
            return true;
    }

    public void hitObject(int i) {
        if(i != 999) {
            if (i >= 2000){
                collisionOn = true;
            }
            else{
                collisionOn = true;
                int x = gp.Bomb[i].worldX / gp.tileSize;
                int y = gp.Bomb[i].worldY / gp.tileSize;
                gp.aSetter.Object_Location[x][y]= 0;
                gp.Bomb[i] = null;
                gp.aSetter.RandomNewbomb();
            }
        }
    }

    public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction){
            case "up" : image = bullet1; worldY-=speed;
                break;
            case "down" : image = bullet1; worldY+=speed;
                break;
            case "left" : image = bullet1; worldX-=speed;
                break;
            case "right" : image = bullet1; worldX+=speed;
                break;
        }

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
    }

}

