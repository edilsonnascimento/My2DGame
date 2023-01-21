package br.org.edn.my2dgame.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean checkDrawTime;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setkeyEvents(e, Boolean.TRUE);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setkeyEvents(e, Boolean.FALSE);
    }
    private void setkeyEvents(KeyEvent e, boolean valor){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            upPressed = valor;
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            downPressed = valor;
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            leftPressed = valor;
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            rightPressed = valor;

        // DEBUG
        if (code == KeyEvent.VK_T) {
            if(checkDrawTime == false) {
                checkDrawTime = true;
            } else if(checkDrawTime) {
                checkDrawTime = false;
            }
        }
    }
}