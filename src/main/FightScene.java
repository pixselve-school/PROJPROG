package main;

import entity.Entity;
import entity.Player;
import utils.Scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

  private boolean m_end;

  private boolean m_menu;

  // Constructeur de la classe
  public FightScene(Entity e) {
    super("fight");
    m_opp = e;

    m_end = false;
    m_menu = false;

    //playMusic();

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

    menu = false;
  }

  /**
   *
   */
  private void opponentTurn() {
    System.out.println("enemi attack");
    GamePanel.player.setHealth(m_opp.getStrength());
  }

  private void playerTurn() {
    m_opp.setHealth(GamePanel.player.getStrength());
    /*
    m_menu = true;
    AtomicBoolean played = new AtomicBoolean(false);
    while(!played.get()) {
      GamePanel.keyH.onKeyPress = (Integer code) -> {
        if (code == KeyEvent.VK_A) {
          attack();
          played.set(true);
        }
        if (code == KeyEvent.VK_Z) {
          escape();
          played.set(true);
        }
        return null;
      };
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
  }

  /**
   * Update the scene
   * @param player the player
   */
  @Override
  public void update(Player player) {

//      System.out.println("Player Health : " + player.getHealth() + " | Opponent Health : " + m_opp.getHealth());
      // Check the fastest to act
      if (player.getSpeed() > m_opp.getSpeed()) {
        // Player turn
        playerTurn();
        // Check if player kills opponent
        if (m_opp.getHealth() < 0) {
          m_end = true;
        }

        // Opponent turn
        opponentTurn();
        // Check if opponent kills player
        if (player.getHealth() < 0) {
          m_end = true;
        }
      } else {
        // Opponent turn
        opponentTurn();
        // Check if opponent kills player
        if (player.getHealth() < 0) {
          m_end = true;
        }

        // Player turn
        playerTurn();
        // Check if player kills opponent
        if (m_opp.getHealth() < 0) {
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

    // draw opponent
    m_opp.draw(g2);

    // draw player
    g2.setColor(Color.GREEN);
    g2.fillRect(75, 400, 140, 200);

    if(menu) {
      g2.drawImage(attackButton, 400, 200, null);
      g2.drawImage(escapeButton, 400, 200, null);
    }

  }
}
