package br.org.edn.my2dgame.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
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

        // PLAYER STATE
        if(gamePanel.gameState == gamePanel.playState) {
            setKeyEvent(code, true);
            if (code == KeyEvent.VK_P)
                    gamePanel.gameState = gamePanel.pauseState;

            // DEBUG
            if (code == KeyEvent.VK_T)
                checkDrawTime = !checkDrawTime;
        }
        // PAUSE STATE
        else if(gamePanel.gameState == gamePanel.pauseState) {
            if (code == KeyEvent.VK_P)
                gamePanel.gameState = gamePanel.playState;
        }
        // DIALOGUE STATE
        else if (gamePanel.gameState == gamePanel.diologueState) {
            if(code == KeyEvent.VK_ENTER)
                gamePanel.gameState = gamePanel.playState;
        }

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
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            rightPressed = value;
        if(code == KeyEvent.VK_ENTER)
            enterPressed = value;
    }
}