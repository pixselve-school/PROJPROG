package entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import utils.Position;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(100, 100, 20);
		this.setPosition(new Position(0, 0));
		System.out.println(this.getPosition().getX());
		this.gp = gp;
		this.keyH = keyH;
		getPlayerImage();

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
		g2.drawImage(image, getPosition().getX(), getPosition().getY(), gp.tileSize, gp.tileSize, null);
	}

	public void move(Direction direction) {
		switch (direction) {
			case UP -> getPosition().addY( -1 * getSpeed());
			case DOWN -> getPosition().addY( 1 * getSpeed());
			case LEFT -> getPosition().addX( -1 * getSpeed());
			case RIGHT -> getPosition().addX( 1 * getSpeed());
		}
	}



}
