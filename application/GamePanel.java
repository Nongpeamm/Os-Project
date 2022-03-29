package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tiles.TileManager;
import object.Object_Health;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 2;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 20;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 102;
    public final int maxWorldRow = 82;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxScreenRow;

    public final int maxBomb = 350;
    public final int maxHealth = 160;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);

    public SuperObject Bomb[] = new SuperObject[maxBomb];
    public Object_Health Health[] = new Object_Health[maxHealth];
    public SuperObject drawH = new SuperObject();

    public UI ui = new UI(this);
    // public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;

    Color bgColor = new Color(129, 186, 68);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(bgColor);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        gameState = titleState;
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / 60;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int i;

        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            tileM.draw(g2);

            for (i = 0; i < Bomb.length; i++) { // bomb draw
                if (Bomb[i] != null) {
                    Bomb[i].draw(g2, this);
                }
            }

            for (i = 0; i < Health.length; i++) { // health draw
                if (Health[i] != null) {
                    Health[i].draw(g2, this);
                }
            }
            player.draw(g2);
            ui.draw(g2);
            drawH.drawHealthTanknum(g2, this);
        }

        g2.dispose();
    }
}
