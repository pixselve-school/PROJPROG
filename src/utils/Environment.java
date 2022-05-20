package utils;

import entity.Chest;
import entity.Entity;
import entity.Player;
import entity.TmpMob;
import entity.monsters.Monster;
import entity.monsters.Skeleton;
import fight.Fight;
import items.Sword;

import main.FightScene;
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
      while (col < GamePanel.maxScreenCol+2 && row < GamePanel.maxScreenRow+2) {
        String line = br.readLine();
        while (col < GamePanel.maxScreenCol+2) {
          String[] numbers = line.split(" ");
          int num = Integer.parseInt(numbers[col]);
          int ts = GamePanel.tileSize;
          if (num < 0) {
            if (col == 8) {
              tiles.add(new Portal_left(-num, (col-1) * ts, (row-1) * ts));
            } else if (col == 9) {
              tiles.add(new Portal_right(-num, (col-1) * ts, (row-1) * ts));
            } else if (row == 6) {
              tiles.add(new Portal_sideUp(-num, (col-1) * ts, (row-1) * ts));
            } else if (row == 7) {
              tiles.add(new Portal_sideDown(-num, (col-1) * ts, (row-1) * ts));
            }
          } else {
            switch (num) {
              case 0:
                tiles.add(new Ground((col-1) * ts, (row-1) * ts));
                break;
              case 1:
                if (col == 1) {
                  tiles.add(new Wall_left((col-1) * ts, (row-1) * ts));
                } else if (col == GamePanel.maxScreenCol) {
                  tiles.add(new Wall_right((col-1) * ts, (row-1) * ts));
                } else if (row == 1) {
                  if(col == 3 || col == 14){
                    tiles.add(new Wall_back(false,(col-1) * ts, (row-1) * ts));
                  }
                  else{
                    tiles.add(new Wall_back(true,(col-1) * ts, (row-1) * ts));
                  }
                } else if (row == GamePanel.maxScreenRow) {
                  tiles.add(new Wall_front((col-1) * ts, (row-1) * ts));
                }
                else{ // murs en dehors de la map pour empècher d'en sortir
                    tiles.add(new Wall_front((col-1) * ts, (row-1) * ts));

                }
                break;
              case 2:
                tiles.add(new Liquid((col-1) * ts, (row-1) * ts));
                break;
              default:
                tiles.add(new Ground((col-1) * ts, (row-1) * ts));
            }
          }
          col++;
        }
        if (col == GamePanel.maxScreenCol+2) {
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
                entities.add(new Chest(new Position(col*ts, row*ts)));
                break;
              case 2:
                entities.add(new Skeleton(new Position(col*ts, row*ts)));
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
          if(player.getPosition().getX() < GamePanel.tileSize*1.2){
            player.setPosition(new Position(GamePanel.screenWidth - (int)(GamePanel.tileSize*2.2), player.getPosition().getY()));
          }
          else if(player.getPosition().getX() > GamePanel.screenWidth - (int)(GamePanel.tileSize*2.2)){
            player.setPosition(new Position((int)(GamePanel.tileSize*1.2), player.getPosition().getY()));
          }
          else if(player.getPosition().getY() < GamePanel.tileSize*1.2){
            player.setPosition(new Position(player.getPosition().getX(), GamePanel.screenHeight - (int)(GamePanel.tileSize*2.2)));
          }
          else if(player.getPosition().getY() > GamePanel.screenHeight - (int)(GamePanel.tileSize*2.2)){
            player.setPosition(new Position(player.getPosition().getX(), (int)(GamePanel.tileSize*1.2)));
          }
          GamePanel.currentEnvironment = GamePanel.environments.get(((Portal) tile).getTp() - 1);
          return;
        }
      }
    }

    for (Entity entity : entities) {
      if (entity.isSolid()) {
        if (willPlayerCollideWithDrawable(entity)) {
          doesCollide = true;
          if (entity instanceof Chest chest) {
            if (!chest.isOpen()) {
              chest.open();
              player.addItemToInventory(new Sword());
            }
          } else if (entity instanceof Monster monster) {
            Scene f = new FightScene(monster);
            GamePanel.currentEnvironment=f;
          }
          break;
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
