package br.org.edn.my2dgame.entity;

import br.org.edn.my2dgame.main.GamePanel;

import static br.org.edn.my2dgame.main.Constants.*;
import static java.lang.Boolean.FALSE;

public class Projectile extends Entity {

    private Entity user;

    public Projectile(GamePanel gamePanel) {
        super(gamePanel);
    }
    public void set(int worldX,int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update() {

        if(user == gamePanel.player) {
            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
            if(isCollision(monsterIndex)) {
                gamePanel.player.damageMonster(monsterIndex, attack);
                alive = FALSE;
            }
        }
        switch (direction) {
            case UP -> worldY -= speed;
            case DOWN -> worldY += speed;
            case LEFT -> worldX -= speed;
            case RIGHT -> worldX += speed;
        }
        life--;
        if(life <= 0 )
            alive = FALSE;
        spriteCounter++;
        if(spriteCounter > 12 && spriteNum == 1) {
            spriteNum = 2;
        } else if(spriteNum == 2) {
          spriteNum = 1;
        }
        spriteCounter = 0;
    };
}
