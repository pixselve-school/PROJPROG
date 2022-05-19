package entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import utils.Position;

public class Player extends Entity {

    private static final BufferedImage image;

    static {
        try {
            image = ImageIO.read(Objects.requireNonNull(Player.class.getResource("/player/superhero.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player() {
        super(10, 5, 11);
        this.setPosition(new Position(0, 0));
    }

    public void update() {
    }

    public void draw(Graphics2D g2) {
        // affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
        g2.drawImage(image, getPosition().getX(), getPosition().getY(), GamePanel.tileSize, GamePanel.tileSize, null);
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP -> getPosition().addY(-1 * getSpeed());
            case DOWN -> getPosition().addY(1 * getSpeed());
            case LEFT -> getPosition().addX(-1 * getSpeed());
            case RIGHT -> getPosition().addX(1 * getSpeed());
        }
    }
}
