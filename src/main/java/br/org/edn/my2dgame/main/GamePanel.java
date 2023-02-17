package br.org.edn.my2dgame.main;

import br.org.edn.my2dgame.entity.Entity;
import br.org.edn.my2dgame.entity.Player;
import br.org.edn.my2dgame.object.SuperObject;
import br.org.edn.my2dgame.tile.TileManager;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;

import static java.util.Objects.nonNull;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    static final int ONE_NANO_SECUND = 1000000000;
    final int originalTileSize = 16; //16x16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScrenRow = 12;
    public final int screeWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screeHeight = tileSize * maxScrenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxScreenCol;

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    Sound sound = new Sound();
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler);
    public SuperObject objects[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    // GAME STATE
    public int gameState;
    public int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int diologueState = 3;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screeWidth, screeHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        playMusic(0);
        stopMusic(); // provisório
        gameState = titleState;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void setUI(PanelUI ui) {
        super.setUI(ui);
    }

    public void update() {
        if(isStateGamePlay()) {
            // PLAYER
            player.update();
            // NPC
            for (int i = 0; i < npc.length; i++) {
                if(nonNull(npc[i]))
                    npc[i].update();
            }
        }

        if(isStateGamePause()) {
            //implement
        }
    }

    @Override
    public void run() {
        double drawInterval = ONE_NANO_SECUND/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentime;
        long timer = 0;
        int drawConunt = 0;

        while (gameThread != null) {
            currentime = System.nanoTime();
            delta += (currentime - lastTime) / drawInterval;
            timer += (currentime - lastTime);
            lastTime = currentime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawConunt++;
            }

            if(timer > ONE_NANO_SECUND) {
                drawConunt = 0;
                timer = 0;
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        // DEBUG
        long drawStart = 0;
        if(keyHandler.checkDrawTime)
                drawStart = System.nanoTime();

        // TITLE SCREEN
        if(isStateTitle()) {
            ui.draw(graphics2D);
        } else {
            // TILE
            tileManager.draw(graphics2D);

            // OBJECT
            for (int i = 0; i < objects.length; i++) {
                if (nonNull(objects[i]))
                    objects[i].draw(graphics2D, this);
            }
            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (nonNull(npc[i]))
                    npc[i].draw(graphics2D);
            }
            // PLAYER
            player.draw(graphics2D);

            // UI
            ui.draw(graphics2D);
        }
        // DEBUG
        if(keyHandler.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            graphics2D.setColor(Color.white);
            graphics2D.drawString("Draw Time: " + passed, 10, 400);
        }

        graphics2D.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

    public boolean isStateGamePlay() {
        return this.gameState == this.playState;
    }

    public boolean isStateGamePause() {
        return this.gameState == this.pauseState;
    }

    public boolean isStateDialogue() {
        return this.gameState == this.diologueState;
    }

    public boolean isStateTitle() {
        return this.gameState == this.titleState;
    }
}