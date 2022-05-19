package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;


public class CombatPanel extends JPanel implements Runnable{
    //Paramètres de l'écran
    final int originalTileSize = 16; // une tuile de taille 16x16
    final int scale = 3; // échelle utilisée pour agrandir l'affichage
    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12; // ces valeurs donnent une résolution 4:3
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    // FPS : taux de rafraichissement
    int FPS = 60;
    // Création des différentes instances (Player, KeyHandler, TileManager, GameThread ...)
    KeyHandler keyH = new KeyHandler();
    Thread combatThread;
    //TileManager tileM = new TileManager(this);

    // FightBackground
    private BufferedImage background;
    // Constructeur de la classe
    public CombatPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        try {
            background = ImageIO.read(getClass().getResource("/Backgrounds/Fight_back.png"));
        } catch (IOException ex) {
            System.out.println("Background load failed !");
        }
    }

    public void startGameThread() {
        combatThread = new Thread(this);
        combatThread.start();
    }


    public void run() {

        double drawInterval = 1000000000/FPS; // rafraichissement chaque 0.0166666 secondes
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(combatThread != null) { //Tant que le thread du jeu est actif


            //Calcule le temps de pause du thread
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the background
        g.drawImage(background, 0, 0, this);

        // draw opponent
        g.setColor(Color.YELLOW);
        g.fillRect(430, 200, 70, 100);

        // draw player
        g.setColor(Color.GREEN);
        g.fillRect(75, 400, 140, 200);
    }

}
