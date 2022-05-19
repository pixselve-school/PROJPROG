package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		// Initialise les valeurs par d�faut
		x = 100;
		y = 100;
		speed = 4;
	}
	
	public void getPlayerImage() {
		try {
			
			idleImage = ImageIO.read(getClass().getResource("/player/superhero.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		
		
	}
	
	public void draw(Graphics2D g2) {
		// r�cup�re l'image du joueur
		BufferedImage image = idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	
	
}
