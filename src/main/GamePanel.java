package main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

import entity.Direction;
import entity.Player;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
	//Param�tres de l'�cran
	final int originalTileSize = 16; // une tuile de taille 16x16
	final int scale = 3; // �chelle utilis�e pour agrandir l'affichage
	public final int tileSize = originalTileSize * scale; // 48x48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12; // ces valeurs donnent une r�solution 4:3
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	// FPS : taux de rafraichissement
	int FPS = 60;
	// Cr�ation des diff�rentes instances (Player, KeyHandler, TileManager, GameThread ...)
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	TileManager tileM = new TileManager(this);
		
	// Constructeur de la classe
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	public void run() {
		
		double drawInterval = 1000000000/FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval;

		keyH.onKeyPress = (Integer keyCode) -> {
			System.out.println(keyCode);
			if (keyCode == 90) {
				player.move(Direction.UP);
			}
			if (keyCode == 83) {
				player.move(Direction.DOWN);
			}
			if (keyCode == 81) {
				player.move(Direction.LEFT);
			}
			if (keyCode == 68) {
				player.move(Direction.RIGHT);
			}
			return null;
		};
		
		while(gameThread != null) { //Tant que le thread du jeu est actif
			
			
			//Permet de mettre � jour les diff�rentes variables du jeu
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
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		tileM.draw(g2);
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
