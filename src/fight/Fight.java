package fight;

import entity.Entity;
import entity.Player;
import main.CombatPanel;

import javax.swing.*;

public abstract class Fight {
    /*-------------------- Attributes --------------------*/
    // Opponent
    private Entity m_opponent;
    // Player
    private Player m_player;
    // End marker
    private boolean m_end;

    // Acses to combat panel
    JFrame m_window;
    CombatPanel m_panel;


    /*--------------------- Methods ----------------------*/
    /**
     * Entity Fight constructor
     * @param p Human player
     * @param e Opponent entity
     */
    public Fight(Player p, Entity e, JFrame window) {
        m_opponent = e;
        m_player = p;
        m_end = false;
        m_window = window;

        m_panel = new CombatPanel();
        m_window.add(m_panel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        m_panel.startGameThread();

        System.out.println("combat lancé");
    }

    /**
     * Loop for the fight
     */
    public void run(){
        while(!m_end) {
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
    }

    /**
     *
     */
    private void opponentTurn() {

    }

    private void playerTurn() {
        int action = selectAction();
        if(action==1) {
            attack();
        }
        else if(action==2) {
            escape();
        }
    }

    /**
     * Quit fight
     */
    private void escape(){
        m_end = true;
    }

    private int selectAction(){

        return 1;
    }

    private void attack(){}
}
