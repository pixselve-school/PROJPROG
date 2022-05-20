package main;

import entity.Entity;
import entity.Player;
import tile.Wall;
import utils.Position;
import utils.Scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;


public class FightScene extends Scene {
  // Affichage du menu
  public boolean menu;
  // opponent
  Entity m_opp;
  // FightBackground
  private BufferedImage background;
  private BufferedImage attackButton;
  private BufferedImage escapeButton;

  private static BufferedImage left_heart;
  private static BufferedImage right_heart;

  private boolean m_end;

  private boolean m_menu;

  // Constructeur de la classe
  public FightScene(Entity e) {
    super("fight");
    m_opp = e;

    m_end = false;
    m_menu = false;

    playMusic();

    System.out.println("START : Player Health : " + GamePanel.player.getHealth() + " | Opponent Health : " + m_opp.getHealth());


    try {
      background = ImageIO.read(getClass().getResource("/Backgrounds/Fight_back.png"));
    } catch (IOException ex) {
      System.out.println(ex);
    }
    try {
      attackButton = ImageIO.read(getClass().getResource("/Backgrounds/atack.png"));
    }catch (IOException ex) {
      System.out.println(ex);
    }
    try {
      escapeButton = ImageIO.read(getClass().getResource("/Backgrounds/escape.png"));
    }catch (IOException ex) {
      System.out.println(ex);
    }

    try {
      left_heart = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/HUD/left_half_heart.png")));
      right_heart = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/HUD/right_half_heart.png")));
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }

    menu = false;
  }

  /**
   *
   */
  private void opponentTurn() {
    if(m_opp.getHealth()>0) {
      GamePanel.player.setHealth(m_opp.getStrength());
    }
  }

  private void playerTurn() {
    AtomicBoolean played = new AtomicBoolean(false);
    while(!played.get()) {
      GamePanel.keyH.onKeyPress = (Integer code) -> {
        if (code == KeyEvent.VK_O) {
          attack();
          played.set(true);
        }
        if (code == KeyEvent.VK_P) {
          escape();
          played.set(false);
        }
        return null;
      };
      menu = false;
   //
    }
        /*
        int action = selectAction();
        if(action==1) {
            attack();
        }
        else if(action==2) {
            escape();
        }*/
  }

  /**
   * Quit fight
   */
  private void escape() {
    m_end = true;
  }

  private int selectAction() {
    //m_panel

    return 2;
  }

  private void attack() {
    m_opp.setHealth(GamePanel.player.getStrength());
  }

  @Override
  public void initialize() {

  }

  /**
   * Update the scene
   *
   * @param player the player
   */
  @Override
  public void update(Player player) {

    System.out.println("Player Health : " + player.getHealth() + " | Opponent Health : " + m_opp.getHealth());
    // Check the fastest to act
      if (player.getSpeed() > m_opp.getSpeed()) {
        // Player turn
        playerTurn();
        // Check if player kills opponent
        if (m_opp.getHealth() <= 0) {
          m_end = true;
        }

        if(!m_end) {
          // Opponent turn
          opponentTurn();
        }
        // Check if opponent kills player
        if (player.getHealth() <= 0) {
          m_end = true;
        }
      } else {
        // Opponent turn
        opponentTurn();
        // Check if opponent kills player
        if (player.getHealth() <= 0) {
          m_end = true;
        }

        if(!m_end) {
          // Player turn
          playerTurn();
        }
        // Check if player kills opponent
        if (m_opp.getHealth() <= 0) {
          m_end = true;
        }
      }

    if(m_end) {
      GamePanel.revertScene();
    }
    System.out.println("END : Player Health : " + player.getHealth() + " | Opponent Health : " + m_opp.getHealth());
  }

  /**
   * Draw the scene
   * @param g2 the graphics
   */
  @Override
  public void draw(Graphics2D g2) {
    // draw the background
    g2.drawImage(background, 0, 0, null);

    drawHealth(g2);
    drawOpponentHealth(g2);

    // draw opponent
    m_opp.draw(g2, new Position(440, 225), m_opp.getWidth(), m_opp.getHeight());

    // draw player
    GamePanel.player.draw(g2, new Position(75, 400), 3*GamePanel.player.getWidth(), 3*GamePanel.player.getHeight());

    g2.drawImage(attackButton, 525, 450, null);
    g2.drawImage(escapeButton, 625, 450, null);

  }

  private void drawHealth(Graphics2D g2) {
    final int WIDTH = 15;
    final int HEIGHT = (int) (WIDTH * 1.7);

    final int START_X = 75;
    final int START_Y = 350;

    for (int i = 0; i < GamePanel.player.getHealth(); i++) {
      if (i % 2 == 0) {
        g2.drawImage(left_heart, START_X + i * WIDTH, START_Y, WIDTH, HEIGHT, null);
      } else {
        g2.drawImage(right_heart, START_X + i * WIDTH, START_Y, WIDTH, HEIGHT, null);
      }
    }
  }

  private void drawOpponentHealth(Graphics2D g2) {
    final int WIDTH = 15;
    final int HEIGHT = (int) (WIDTH * 1.7);

    final int START_X = 440;
    final int START_Y = 190;

    for (int i = 0; i < m_opp.getHealth(); i++) {
      if (i % 2 == 0) {
        g2.drawImage(left_heart, START_X + i * WIDTH, START_Y, WIDTH, HEIGHT, null);
      } else {
        g2.drawImage(right_heart, START_X + i * WIDTH, START_Y, WIDTH, HEIGHT, null);
      }
    }
  }

  }
