package main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

import entity.Direction;
import entity.Player;
import utils.Environment;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
	//Param�tres de l'�cran
	final static int originalTileSize = 16; // une tuile de taille 16x16
	final static int scale = 3; // �chelle utilis�e pour agrandir l'affichage
	public static final int tileSize = originalTileSize * scale; // 48x48
	public static final int maxScreenCol = 16;
	public static final int maxScreenRow = 12; // ces valeurs donnent une r�solution 4:3
	public static final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public static final int screenHeight = tileSize * maxScreenRow; //576 pixels

	// FPS : taux de rafraichissement
	static int FPS = 60;
	// Cr�ation des diff�rentes instances (Player, KeyHandler, TileManager, GameThread ...)
	static KeyHandler keyH = new KeyHandler();
	static Thread gameThread;
	static Player player = new Player();

	static ArrayList<Environment> environments;
	static Environment currentEnvironment;

	// Constructeur de la classe
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		environments = new ArrayList<>();
		environments.add(new Environment("/maps/map1.txt"));
		environments.add(new Environment("/maps/map2.txt"));
		environments.add(new Environment("/maps/map3.txt"));
		environments.add(new Environment("/maps/map4.txt"));
		environments.add(new Environment("/maps/map5.txt"));
		environments.add(new Environment("/maps/map6.txt"));
		currentEnvironment = environments.get(0);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	private boolean[] movements = new boolean[4];

	public void run() {
		
		double drawInterval = 1000000000/FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval; 

    keyH.onKeyPress = (Integer code) -> {
      if (code == KeyEvent.VK_Z) {
        movements[0] = true;
      }
      if (code == KeyEvent.VK_S) {
        movements[1] = true;
      }
      if (code == KeyEvent.VK_Q) {
        movements[2] = true;
      }
      if (code == KeyEvent.VK_D) {
        movements[3] = true;
      }
	  if (code == KeyEvent.VK_UP) {
		movements[0] = true;
	  }
	  if (code == KeyEvent.VK_DOWN) {
		movements[1] = true;
	  }
	  if (code == KeyEvent.VK_LEFT) {
		movements[2] = true;
	  }
	  if (code == KeyEvent.VK_RIGHT) {
		movements[3] = true;
	  }
      return null;
    };
    keyH.onKeyReleased = (Integer code) -> {
      if (code == KeyEvent.VK_Z) {
        movements[0] = false;
      }
      if (code == KeyEvent.VK_S) {
        movements[1] = false;
      }
      if (code == KeyEvent.VK_Q) {
        movements[2] = false;
      }
      if (code == KeyEvent.VK_D) {
        movements[3] = false;
      }
	  if (code == KeyEvent.VK_UP) {
		movements[0] = false;
	  }
	  if (code == KeyEvent.VK_DOWN) {
		movements[1] = false;
	  }
	  if (code == KeyEvent.VK_LEFT) {
		movements[2] = false;
	  }
	  if (code == KeyEvent.VK_RIGHT) {
		movements[3] = false;
	  }
      return null;
    };


		while(gameThread != null) { //Tant que le thread du jeu est actif
			
			
			//Permet de mettre à jour les différentes variables du jeu
			update();
			//Dessine sur l'�cran le personnage et la map avec les nouvelles informations. la m�thode "paintComponent" doit obligatoirement �tre appel�e avec "repaint()"
			repaint();
			//Calcule le temps de pause du thread
			
			
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
	

  public void update() {
    player.update();
    if (movements[0]) {
      player.move(Direction.UP);
    }
    if (movements[1]) {
      player.move(Direction.DOWN);
    }
    if (movements[2]) {
      player.move(Direction.LEFT);
    }
    if (movements[3]) {
      player.move(Direction.RIGHT);
    }

  }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		currentEnvironment.draw(g2);
		player.draw(g2);
		g2.dispose();
	}


}



//public void run() {
//double drawInterval = 1000000000/FPS;
//double delta = 0;
//long lastTime = System.nanoTime();
//long currentTime;
//long timer = 0;
//long drawCount = 0;
//
//while (gameThread != null) {
//	
//	currentTime = System.nanoTime();
//	
//	delta += (currentTime - lastTime) / drawInterval;
//	timer += (currentTime - lastTime);
//	lastTime = currentTime;
//	
//	if(delta >= 1) {
//		update();
//		repaint();
//		delta--;
//		drawCount++;
//	}
//		
//	if(timer >= 1000000000) {
//		System.out.println("FPS:" + drawCount);
//		drawCount = 0;
//		timer = 0;
//	}
//}
//}
