package application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gp.gameState == gp.titleState) {
            if(code == KeyEvent.VK_UP) {
                if(gp.ui.commandNum != 0) {
                    gp.ui.commandNum--;
                }   
            }

            if(code == KeyEvent.VK_DOWN) {
                if(gp.ui.commandNum != 1) {
                    gp.ui.commandNum++;
                }
            }

            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 1){
                    System.exit(0);
                }
            }
        }

        if(gp.gameState == gp.playState) {
            if(code == KeyEvent.VK_UP) {
                upPressed = true;
            }

            if(code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }

            if(code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }

            if(code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
        }

        if(gp.gameState == gp.gameOverState) {
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0){
                    System.exit(0);
                }
            }
        }

        if(code == KeyEvent.VK_ESCAPE) {
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP) {
            upPressed = false;
        }

        if(code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }

        if(code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if(code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}