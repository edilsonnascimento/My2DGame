package br.org.edn.my2dgame.main;

import org.ietf.jgss.GSSManager;

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

        // TITLE STATE
        if(gamePanel.isStateTitle()) {
            if(gamePanel.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gamePanel.ui.commandNumber--;
                    if (gamePanel.ui.commandNumber < 0)
                        gamePanel.ui.commandNumber = 2;
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gamePanel.ui.commandNumber++;
                    if (gamePanel.ui.commandNumber > 2)
                        gamePanel.ui.commandNumber = 0;
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gamePanel.ui.commandNumber == 0) {
                        gamePanel.ui.titleScreenState = 1;
                        gamePanel.playMusic(0);
                    }
                    if (gamePanel.ui.commandNumber == 1) {
                        // add later
                    }
                    if (gamePanel.ui.commandNumber == 2) {
                        System.exit(0);
                    }
                }
            }
            else if(gamePanel.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gamePanel.ui.commandNumber--;
                    if (gamePanel.ui.commandNumber < 0)
                        gamePanel.ui.commandNumber = 3;
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gamePanel.ui.commandNumber++;
                    if (gamePanel.ui.commandNumber > 3)
                        gamePanel.ui.commandNumber = 0;
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gamePanel.ui.commandNumber == 0) {
                        System.out.println("DO SOME FIGHTER SPECIFIC STUFF!");
                        gamePanel.gameState = gamePanel.playState;
                        gamePanel.playMusic(0);
                    }
                    if (gamePanel.ui.commandNumber == 1) {
                        System.out.println("DO SOME THIEF SPECIFIC STUFF!");
                        gamePanel.gameState = gamePanel.playState;
                        gamePanel.playMusic(0);
                    }
                    if (gamePanel.ui.commandNumber == 2) {
                        System.out.println("DO SOME SORCERER SPECIFIC STUFF!");
                        gamePanel.gameState = gamePanel.playState;
                        gamePanel.playMusic(0);
                        System.out.println("DO SOME FIGHTER SPECIFIC STUFF!");

                    }if (gamePanel.ui.commandNumber == 3) {
                        gamePanel.ui.titleScreenState = 0;
                    }
                }
            }

        }

        // PLAYER STATE
        else if(gamePanel.isStateGamePlay()) {
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