package utils;

import entity.Direction;
import entity.Entity;
import entity.Player;
import main.GamePanel;
import tile.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
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
                    int num = Integer.parseInt(numbers[col]);
                    int ts = GamePanel.tileSize;
                    if(num < 0){
                        System.out.println(col + " " + row);
                        if(col == 7){
                            tiles.add(new Portal_left(-num,col*ts,row*ts));
                        }
                        else if (col == 8){
                            tiles.add(new Portal_right(-num,col*ts,row*ts));
                        }
                        else if(row == 5){
                            tiles.add(new Portal_sideUp(-num,col*ts,row*ts));
                        }
                        else if (row == 6){
                            tiles.add(new Portal_sideDown(-num,col*ts,row*ts));
                        }
                    }
                    else {
                        switch (num) {
                            case 0:
                                tiles.add(new Ground(col * ts, row * ts));
                                break;
                            case 1:
                                if(col == 0){
                                    tiles.add(new Wall_left(col * ts, row * ts));
                                }
                                else if (col == GamePanel.maxScreenCol-1){
                                    tiles.add(new Wall_right(col * ts, row * ts));
                                }
                                else if(row ==0) {
                                    tiles.add(new Wall_back(col * ts, row * ts));
                                }
                                else if(row ==GamePanel.maxScreenRow-1){
                                    tiles.add(new Wall_front(col * ts, row * ts));
                                }
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
    boolean doesCollide = false;
    for (Tile tile : tiles) {
      if (tile.isSolid()) {
        if (willPlayerCollideWithDrawable(tile)) {
          doesCollide = true;
          break;
        }
      }
    }
    if (!doesCollide) {
      player.move();
    }
  }

  public static boolean isPlayerCollidingWithDrawable(Drawable drawable) {
    return GamePanel.player.isColliding(drawable);
  }

  public static boolean willPlayerCollideWithDrawable(Drawable drawable) {
    return GamePanel.player.collideNextBoundingBox(drawable);
  }

  public void draw(Graphics2D g2) {
    tiles.forEach(tile -> tile.draw(g2));
    entities.forEach(tile -> tile.draw(g2));
  }
}
