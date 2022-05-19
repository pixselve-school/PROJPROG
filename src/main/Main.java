package main;

import entity.Entity;
import entity.TmpMob;
import fight.EntityFight;
import fight.Fight;

import javax.swing.*;


public class Main {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("My 2D Video Game");

    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);
    window.pack();

    window.setLocationRelativeTo(null);
    window.setVisible(true);
    gamePanel.startGameThread();

  }

}
