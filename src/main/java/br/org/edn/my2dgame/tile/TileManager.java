package br.org.edn.my2dgame.tile;

import br.org.edn.my2dgame.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.worldWidth];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles[1].coolision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
            tiles[2].coolision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tiles[4].coolision = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road00.png"));

        } catch (IOException e) {
            System.out.println("ERROR CATCH IMAGE TILE");
        }
    }
    public void loadMap(String filePath){
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int col = 0;
            int row = 0;
            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = bufferedReader.readLine();
                while (col < gamePanel.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxWorldCol) {
                    col =0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("ERROR LOAD MAP");
        }
    }
    public void draw(Graphics2D graphics2D) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
               worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
               worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
               worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                graphics2D.drawImage(tiles[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            worldCol++;
            if(worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}