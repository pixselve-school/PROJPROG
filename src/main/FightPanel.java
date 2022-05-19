package main;

import entity.Entity;
import entity.Player;
import utils.Music;
import utils.Scene;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class FightPanel extends Scene {
  // Affichage du menu
  public boolean menu;
  // opponent
  Entity m_opp;
  // FightBackground
  private BufferedImage background;

  private boolean m_end;

  // Constructeur de la classe
  public FightPanel(Entity e) {
    super("fight");
    m_opp = e;

    m_end = false;

    playMusic();

    System.out.println("START : Player Health : " + GamePanel.player.getHealth() + " | Opponent Health : " + m_opp.getHealth());


    try {
      background = ImageIO.read(getClass().getResource("/Backgrounds/Fight_back.png"));
    } catch (IOException ex) {
      System.out.println("Background load failed !");
    }
    menu = false;
  }




  /**
   *
   */
  private void opponentTurn() {
    GamePanel.player.setHealth(m_opp.getStrength());
  }

  private void playerTurn() {
    m_opp.setHealth(GamePanel.player.getStrength());
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
        if (m_opp.getHealth() == 0) {
          m_end = true;
//          break;
        }

        // Opponent turn
        opponentTurn();
        // Check if opponent kills player
        if (player.getHealth() == 0) {
          m_end = true;
//          break;
        }
      } else {
        // Opponent turn
        opponentTurn();
        // Check if opponent kills player
        if (player.getHealth() == 0) {
          m_end = true;
//          break;
        }

        // Player turn
        playerTurn();
        // Check if player kills opponent
        if (m_opp.getHealth() == 0) {
          m_end = true;
//          break;
        }
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


  }
}
