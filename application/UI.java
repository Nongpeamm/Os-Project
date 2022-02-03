package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import object.Object_Heart;
import object.SuperObject;

public class UI {
    GamePanel gp;
    Font arial_bold, alagard, retro, opt_prin;
    Color gOverColor = new Color(96, 41, 35); 

    BufferedImage health;
    public int commandNum = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_bold = new Font("Arial", Font.BOLD, 20);
        
        try {
            InputStream is = getClass().getResourceAsStream("/res/font/Alagard.ttf");
            alagard = Font.createFont(Font.TRUETYPE_FONT, is);

            is = getClass().getResourceAsStream("/res/font/Retro_Gaming.ttf");
            retro = Font.createFont(Font.TRUETYPE_FONT, is);

            is = getClass().getResourceAsStream("/res/font/OptimusPrinceps.ttf");
            opt_prin = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SuperObject heart = new Object_Heart(gp);
        health = heart.image;
    }

    public void draw(Graphics2D g2) {
        if(gp.gameState == gp.titleState) {
            drawTitle(g2);
        }
        if(gp.gameState == gp.playState) {
            drawPlayerHP(g2);

            g2.setFont(arial_bold);
            g2.setColor(Color.black);

            g2.drawString("X: " + gp.player.worldX / 32, 5, 20);
            g2.drawString("Y: " + gp.player.worldY / 32, 70, 20);

            playTime += (double)1/10;
            g2.drawString("Time: " + dFormat.format(playTime), 690, 20);
        }
        if(gp.gameState == gp.pauseState) {
            g2.setFont(alagard);
            g2.setColor(Color.red);
            drawPauseScreen(g2);
        }
        if(gp.gameState == gp.gameOverState) {
            g2.setFont(opt_prin);
            drawGameOver(g2);
        }
    }

    public void drawPlayerHP(Graphics2D g2) {
        int x = gp.tileSize / 2;
        int y = gp.tileSize * 18 + 20;
        int i = 0;

        while(i < gp.player.life) {
            g2.drawImage(health, x, y, null);
            i++;
            x += gp.tileSize + 2;
        }
    }

    public void drawTitle(Graphics2D g2) {
        g2.setFont(alagard);
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String text = "THERE'S NOTHING HERE";
        int x = getCenterX(text, g2);
        int y = gp.tileSize * 3;

        // Title shadow
        g2.setColor(Color.orange);
        g2.drawString(text, x, y+5);

        // Title main color
        g2.setColor(Color.yellow);
        g2.drawString(text, x, y);

        // Subtext
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        String underText = "but a landmine";
        x += 550;
        y += gp.tileSize;

        // Subtext shadow
        g2.setColor(Color.orange);
        g2.drawString(underText, x, y+2);

        // Subtext main color
        g2.setColor(Color.yellow);
        g2.drawString(underText, x, y);

        // Character
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 4;
        g2.drawImage(gp.player.right2, x, y, gp.tileSize * 3, gp.tileSize * 3, null);

        // Menu
        g2.setFont(retro);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));

        text = "START";
        x = getCenterX(text, g2);
        y += gp.tileSize * 8;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getCenterX(text, g2);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPauseScreen(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getCenterX(text, g2);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawGameOver(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0, 240, gp.screenWidth, 100);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "YOU DIED";
        int x = getCenterX(text, g2);
        int y = gp.screenHeight / 2;
        g2.setColor(gOverColor);
        g2.drawString(text, x, y);

        // MENU
        g2.setFont(retro);
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));

        text = "TRY AGAIN";
        x = getCenterX(text, g2);
        y += gp.tileSize * 5;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getCenterX(text, g2);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public int getCenterX(String text, Graphics2D g2) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
