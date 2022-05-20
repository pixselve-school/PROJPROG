package main;

import HUD.HUD;
import entity.Direction;
import entity.Player;
import entity.TmpMob;
import utils.Environment;
import utils.Game_over;
import utils.Main_Menu;
import utils.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
  public static final int maxScreenCol = 16;
  public static final int maxScreenRow = 12; // ces valeurs donnent une r�solution 4:3
  public static final boolean DEBUG = false;
  //Param�tres de l'�cran
  final static int originalTileSize = 16; // une tuile de taille 16x16
  final static int scale = 3; // �chelle utilis�e pour agrandir l'affichage
  public static final int tileSize = originalTileSize * scale; // 48x48
  public static final int screenWidth = tileSize * maxScreenCol; //768 pixels
  public static final int screenHeight = tileSize * maxScreenRow; //576 pixels
  // Cr�ation des diff�rentes instances (Player, KeyHandler, TileManager, GameThread ...)
  public static KeyHandler keyH = new KeyHandler();
  public static Player player = new Player();
  // FPS : taux de rafraichissement
  static int FPS = 60;
  static Thread gameThread;

  public static ArrayList<Environment> environments;
  public static Scene currentEnvironment;
  private static Scene oldScene;

  // Constructeur de la classe
  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
    environments = new ArrayList<>();
    Main_Menu Launch = new Main_Menu("/Musics/theme.wav");
    environments.add(new Environment("/maps/map1.txt"));
    environments.add(new Environment("/maps/map2.txt"));
    environments.add(new Environment("/maps/map3.txt"));
    environments.add(new Environment("/maps/map4.txt"));
    environments.add(new Environment("/maps/map5.txt"));
    environments.add(new Environment("/maps/map6.txt"));
    currentEnvironment = Launch;
    oldScene = Launch;
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }


  private static void setScene(Scene scene) {
    oldScene.reset();
    oldScene = currentEnvironment;
    currentEnvironment = scene;
  }

  public static void revertScene() {
    currentEnvironment.reset();
    currentEnvironment = oldScene;
  }


  public void run() {

    double drawInterval = 1000000000 / FPS; // rafraichissement chaque 0.0166666 secondes
    double nextDrawTime = System.nanoTime() + drawInterval;

    if(player.getHealth()==0){
      oldScene = currentEnvironment;
      Game_over End = new Game_over("/Musics/theme.wav");
      currentEnvironment = End;
    }

    GamePanel.keyH.onKeyPress = (Integer code) -> {
      if (currentEnvironment instanceof Environment) {
        if (code == KeyEvent.VK_W) {
          player.addDirection(Direction.UP);
        }
        if (code == KeyEvent.VK_S) {
          player.addDirection(Direction.DOWN);
        }
        if (code == KeyEvent.VK_A) {
          player.addDirection(Direction.LEFT);
        }
        if (code == KeyEvent.VK_D) {
          player.addDirection(Direction.RIGHT);
        }
        if (code == KeyEvent.VK_F) {
          setScene(new FightScene(new TmpMob(10, 1, 5)));
        }

      }
      else if (currentEnvironment instanceof FightScene) {
        if (code == KeyEvent.VK_F) {
          revertScene();
        }
      }
      else if (currentEnvironment instanceof Main_Menu){
        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
          currentEnvironment = environments.get(0);
        }

      }
      return null;
    };
    GamePanel.keyH.onKeyReleased = (Integer code) -> {
      if (code == KeyEvent.VK_W) {
        player.removeDirection(Direction.UP);
      }
      if (code == KeyEvent.VK_S) {
        player.removeDirection(Direction.DOWN);
      }
      if (code == KeyEvent.VK_A) {
        player.removeDirection(Direction.LEFT);
      }
      if (code == KeyEvent.VK_D) {
        player.removeDirection(Direction.RIGHT);
      }
      return null;
    };


    while (gameThread != null) { //Tant que le thread du jeu est actif


      //Permet de mettre à jour les différentes variables du jeu
      update();
      //Dessine sur l'�cran le personnage et la map avec les nouvelles informations. la m�thode "paintComponent" doit obligatoirement �tre appel�e avec "repaint()"
      repaint();
      //Calcule le temps de pause du thread


      try {
        double remainingTime = nextDrawTime - System.nanoTime();
        remainingTime = remainingTime / 1000000;

        if (remainingTime < 0) {
          remainingTime = 0;
        }

        Thread.sleep((long) remainingTime);
        nextDrawTime += drawInterval;

      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }


  public void update() {
    player.update();
    currentEnvironment.update(player);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    currentEnvironment.draw(g2);
    if ((currentEnvironment instanceof Environment)) {
//      Do not draw the player when in fight mode
      player.draw(g2);
      HUD.draw(g2);
    }
    g2.dispose();
  }


}
