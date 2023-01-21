package br.org.edn.my2dgame.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean checkDrawTime;
    private final GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        setKeyEvent(code, true);
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_P) {
            if(gamePanel.isStateGamePlay()) {
                gamePanel.gameState = gamePanel.pauseState;
            }else if(gamePanel.isStateGamePause()) {
                gamePanel.gameState = gamePanel.playState;
            }
        }

        // DEBUG
        if (code == KeyEvent.VK_T)
            checkDrawTime = !checkDrawTime;
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        setKeyEvent(code, false);
    }

    private void setKeyEvent(int code, boolean value) {
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            upPressed = value;
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            downPressed = value;
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            leftPressed = value;
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            rightPressed = value;
    }
}