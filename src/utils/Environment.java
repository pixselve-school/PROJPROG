package utils;

import entity.Entity;
import entity.Player;
import entity.TmpMob;
import main.GamePanel;
import tile.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Environment extends Scene {
  static int nbEnv = 0;
  private final LinkedList<Tile> tiles;
  private final LinkedList<Entity> entities;
  private final int index;

  private final String mapPath;

  private Environment() {
    tiles = new LinkedList<>();
    entities = new LinkedList<>();
    nbEnv++;
    index = nbEnv;
    mapPath = "";
  }

  public Environment(String pathToMap) {
    tiles = new LinkedList<>();
    entities = new LinkedList<>();
    nbEnv++;
    index = nbEnv;
    mapPath = pathToMap;
    loadMap();
  }

  public static boolean isPlayerCollidingWithDrawable(Drawable drawable) {
    return GamePanel.player.isColliding(drawable);
  }

  public static boolean willPlayerCollideWithDrawable(Drawable drawable) {
    return GamePanel.player.collideNextBoundingBox(drawable);
  }

  // Cette m�thode charge la map
  public void loadMap() {
    //charger le fichier txt de la map
    try {
      InputStream is = getClass().getResourceAsStream(mapPath);
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      int col = 0;
      int row = 0;
      // Parcourir le fichier txt pour r�cup�rer les valeurs
      while (col < GamePanel.maxScreenCol && row < GamePanel.maxScreenRow) {
        String line = br.readLine();
        while (col < GamePanel.maxScreenCol) {
          String[] numbers = line.split(" ");
          int num = Integer.parseInt(numbers[col]);
          int ts = GamePanel.tileSize;
          if (num < 0) {
            if (col == 7) {
              tiles.add(new Portal_left(-num, col * ts, row * ts));
            } else if (col == 8) {
              tiles.add(new Portal_right(-num, col * ts, row * ts));
            } else if (row == 5) {
              tiles.add(new Portal_sideUp(-num, col * ts, row * ts));
            } else if (row == 6) {
              tiles.add(new Portal_sideDown(-num, col * ts, row * ts));
            }
          } else {
            switch (num) {
              case 0:
                tiles.add(new Ground(col * ts, row * ts));
                break;
              case 1:
                if (col == 0) {
                  tiles.add(new Wall_left(col * ts, row * ts));
                } else if (col == GamePanel.maxScreenCol - 1) {
                  tiles.add(new Wall_right(col * ts, row * ts));
                } else if (row == 0) {
                  tiles.add(new Wall_back(col * ts, row * ts));
                } else if (row == GamePanel.maxScreenRow - 1) {
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
          row++;
        }
      }
      br.readLine(); // on saute la ligne vide
      col = 0;
      row = 0;
      // Parcourir le fichier txt pour r�cup�rer les valeurs
      while (col < GamePanel.maxScreenCol && row < GamePanel.maxScreenRow) {
        String line = br.readLine();
        while (col < GamePanel.maxScreenCol) {
          String[] numbers = line.split(" ");
          int num = Integer.parseInt(numbers[col]);
          int ts = GamePanel.tileSize;
          switch (num) {
              case 1:
                entities.add(new TmpMob(1, 1, 1));
                break;
              default:
                //rien
          }
          col++;
        }
        if (col == GamePanel.maxScreenCol) {
          col = 0;
          row++;
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
      } else if (tile instanceof Portal) {
        if (isPlayerCollidingWithDrawable(tile)) {
          //        The player is on the portal

          if(player.getPosition().getX() < 100){
            player.setPosition(new Position(GamePanel.screenWidth - 100, player.getPosition().getY()));
          }
          else if(player.getPosition().getX() > GamePanel.screenWidth - 100){
            player.setPosition(new Position(100, player.getPosition().getY()));
          }
          else if(player.getPosition().getY() < 100){
            player.setPosition(new Position(player.getPosition().getX(), GamePanel.screenHeight - 100));
          }
          else if(player.getPosition().getY() > GamePanel.screenHeight - 100){
            player.setPosition(new Position(player.getPosition().getX(), 100));
          }
          GamePanel.currentEnvironment = GamePanel.environments.get(((Portal) tile).getTp()-1);
          return;
        }

      }
    }
    if (!doesCollide) {
      player.move();
    }
  }

  public void draw(Graphics2D g2) {
    tiles.forEach(tile -> tile.draw(g2));
    entities.forEach(tile -> tile.draw(g2));
  }
}
