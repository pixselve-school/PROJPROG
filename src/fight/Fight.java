package fight;

import entity.Entity;
import entity.Player;
import entity.TmpMob;
import main.FightPanel;


import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import utils.Music;

public abstract class Fight {
    /*-------------------- Attributes --------------------*/
    // Opponent
    private Entity m_opponent;
    // Player
    private Player m_player;
    // End marker
    private boolean m_end;

    // Acses to combat panel
    private JFrame m_window;
    private FightPanel m_panel;

    Music m_music;


    /*--------------------- Methods ----------------------*/
    /**
     * Entity Fight constructor
     * @param e Opponent entity
     * @param window JFrame
     */
    public Fight(Entity e, JFrame window) {
        m_opponent = e;
        m_player = new Player();

        m_end = false;
        m_window = window;

        m_music = new Music("fight");
        m_music.play();

        m_panel = new FightPanel(m_opponent);
        m_window.add(m_panel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        m_panel.startGameThread();

        System.out.println("START : Player Health : " + m_player.getHealth() + " | Opponent Health : " + m_opponent.getHealth());
    }

    /**
     * Loop for the fight
     */
    public void run(){
        while(!m_end) {
            System.out.println("Player Health : " + m_player.getHealth() + " | Opponent Health : " + m_opponent.getHealth());
            // Check the fastest to act
            if(m_player.getSpeed()> m_opponent.getSpeed()) {
                // Player turn
                playerTurn();
                // Check if player kills opponent
                if(m_opponent.getHealth()==0) {
                    m_end = true;
                    break;
                }

                // Opponent turn
                opponentTurn();
                // Check if opponent kills player
                if(m_player.getHealth()==0) {
                    m_end = true;
                    break;
                }
            }
            else {
                // Opponent turn
                opponentTurn();
                // Check if opponent kills player
                if(m_player.getHealth()==0) {
                    m_end = true;
                    break;
                }

                // Player turn
                playerTurn();
                // Check if player kills opponent
                if(m_opponent.getHealth()==0) {
                    m_end = true;
                    break;
                }
            }
        }
        System.out.println("END : Player Health : " + m_player.getHealth() + " | Opponent Health : " + m_opponent.getHealth());
    }

    /**
     *
     */
    private void opponentTurn() {
        m_player.setHealth(m_opponent.getStrength());
    }

    private void playerTurn() {
        m_opponent.setHealth(m_player.getStrength());
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
    private void escape(){
        m_end = true;
    }

    private int selectAction(){
        //m_panel

        return 2;
    }

    private void attack(){}

}
