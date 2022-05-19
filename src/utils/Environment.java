package utils;

import entity.Entity;
import entity.Player;
import main.GamePanel;
import tile.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Environment {
    private LinkedList<Tile> tiles;
    private LinkedList<Entity> entities;

    static int nbEnv = 0;

    private int index;

    private Environment() {
        tiles = new LinkedList<>();
        entities = new LinkedList<>();
        nbEnv++;
        index = nbEnv;
    }

    public Environment(String pathToMap) {
        tiles = new LinkedList<>();
        entities = new LinkedList<>();
        nbEnv++;
        index = nbEnv;
        loadMap(pathToMap);
    }

    // Cette m�thode charge la map
    public void loadMap(String pathToMap) {
        //charger le fichier txt de la map
        try {
            InputStream is = getClass().getResourceAsStream(pathToMap);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            // Parcourir le fichier txt pour r�cup�rer les valeurs
            while (col < GamePanel.maxScreenCol && row < GamePanel.maxScreenRow) {
                String line = br.readLine();
                while (col < GamePanel.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    for(String s : numbers){
                        System.out.println(s);
                    }
                    int num = Integer.parseInt(numbers[col]);
                    int ts = GamePanel.tileSize;
                    if(num < 0){
                        tiles.add(new Portal(-num,col*ts,row*ts));
                    }
                    else {
                        switch (num) {
                            case 0:
                                tiles.add(new Ground(col * ts, row * ts));
                                break;
                            case 1:
                                tiles.add(new Wall(col * ts, row * ts));
                                break;
                            case 2:
                                tiles.add(new Liquid(col * ts, row * ts));
                                break;
                            default:
                                tiles.add(new Ground(col * ts, row * ts));
                        }
                    }
                    col++;
                }
                if (col == GamePanel.maxScreenCol) {
                    col = 0;
                    row ++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Player player) {
        for (Tile tile : tiles) {
            if (tile.isSolid()) {
                if (tile.isColliding(player)) {
                    player.setPosition(player.getOldPosition());
                }
            }
        }
        for (Entity entity : entities) {
            if (entity.isSolid()) {
                if (entity.isColliding(player)) {
                    player.setPosition(player.getOldPosition());
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        tiles.forEach(tile -> tile.draw(g2));
        entities.forEach(tile -> tile.draw(g2));
    }
}
