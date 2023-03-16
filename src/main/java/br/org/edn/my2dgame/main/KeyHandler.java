package br.org.edn.my2dgame.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
    public boolean checkDrawTime = Boolean.FALSE;
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
            titleState(code);
        }
        // PLAYER STATE
        else if(gamePanel.isStateGamePlay()) {
            playerState(code);
        }
        // PAUSE STATE
        else if(gamePanel.isStateGamePause()) {
            pauseState(code);
        }
        // DIALOGUE STATE
        else if (gamePanel.isStateDialogue()) {
            dialogueState(code);
        }
        // CHARACTER STATE
        else if(gamePanel.isCharacterState()) {
            characterState(code);
        }

    }

    private void characterState(int code) {
        if(code == VK_C)
            gamePanel.gameState = gamePanel.playState;
        if(upCommand(code) ) {
            if(gamePanel.ui.slotRow != 0) {
                gamePanel.ui.slotRow--;
                gamePanel.playSE(9);
            }
        }
        if(leftCommand(code)) {
            if(gamePanel.ui.slotCol != 0) {
                gamePanel.ui.slotCol--;
                gamePanel.playSE(9);
            }
        }
        if(downCommand(code)) {
            if(gamePanel.ui.slotRow != 3) {
                gamePanel.ui.slotRow++;
                gamePanel.playSE(9);
            }
        }
        if(rightCommand(code)) {
            if(gamePanel.ui.slotCol != 4) {
                gamePanel.ui.slotCol++;
                gamePanel.playSE(9);
            }
        }

        if(code == VK_ENTER)
            gamePanel.player.selectItem();
    }

    private void dialogueState(int code) {
        if(code == VK_ENTER)
            gamePanel.gameState = gamePanel.playState;
    }

    private void pauseState(int code) {
        if (code == VK_P)
            gamePanel.gameState = gamePanel.playState;
    }

    private void playerState(int code) {
        setKeyEvent(code, true);
        if(code == VK_P)
            gamePanel.gameState = gamePanel.pauseState;
        if(code == VK_C)
            gamePanel.gameState = gamePanel.characterState;


        // DEBUG
        if (code == VK_T)
            checkDrawTime = !checkDrawTime;
    }

    private void titleState(int code) {
        if(gamePanel.ui.titleScreenState == 0) {
            if (upCommand(code)) {
                gamePanel.ui.commandNumber--;
                if (gamePanel.ui.commandNumber < 0)
                    gamePanel.ui.commandNumber = 2;
            }
            if (downCommand(code)) {
                gamePanel.ui.commandNumber++;
                if (gamePanel.ui.commandNumber > 2)
                    gamePanel.ui.commandNumber = 0;
            }
            if (code == VK_ENTER) {
                if (gamePanel.ui.commandNumber == 0) {
                    gamePanel.ui.titleScreenState = 1;
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
            if (upCommand(code)) {
                gamePanel.ui.commandNumber--;
                if (gamePanel.ui.commandNumber < 0)
                    gamePanel.ui.commandNumber = 3;
            }
            if (downCommand(code)) {
                gamePanel.ui.commandNumber++;
                if (gamePanel.ui.commandNumber > 3)
                    gamePanel.ui.commandNumber = 0;
            }
            if (code == VK_ENTER) {
                if (gamePanel.ui.commandNumber == 0) {
                    System.out.println("DO SOME FIGHTER SPECIFIC STUFF!");
                    gamePanel.gameState = gamePanel.playState;
                    //gamePanel.playMusic(0);
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

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        setKeyEvent(code, false);
    }

    private void setKeyEvent(int code, boolean value) {
        if(upCommand(code))
            upPressed = value;
        if(downCommand(code))
            downPressed = value;
        if(leftCommand(code))
            leftPressed = value;
        if(rightCommand(code))
            rightPressed = value;
        if(code == VK_ENTER)
            enterPressed = value;
        if(code == VK_F)
            shotKeyPressed = value;
    }

    private boolean rightCommand(int code) {
        return code == VK_D || code == VK_RIGHT;
    }

    private boolean leftCommand(int code) {
        return code == VK_A || code == VK_LEFT;
    }

    private boolean upCommand(int code) {
        return code == VK_W || code == VK_UP;
    }

    private boolean downCommand(int code) {
        return code == VK_S || code == VK_DOWN;
    }
}