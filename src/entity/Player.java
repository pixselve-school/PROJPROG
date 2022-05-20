package entity;

import items.Item;
import main.GamePanel;
import utils.Drawable;
import utils.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class Player extends Entity {

  private static final BufferedImage [] image = new BufferedImage[4];

  private int state;

  static {
    try {
      image [0] = ImageIO.read(Objects.requireNonNull(Player.class.getResource("/player/Character.png")));
      image [1] = ImageIO.read(Objects.requireNonNull(Player.class.getResource("/player/Character2.png")));
      image [2] = ImageIO.read(Objects.requireNonNull(Player.class.getResource("/player/Character3.png")));
      image [3] = ImageIO.read(Objects.requireNonNull(Player.class.getResource("/player/Character4.png")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Position nextPosition;
  private final HashSet<Direction> directions;

  public LinkedList<Item> getInventory() {
    return inventory;
  }

  public void addItemToInventory(Item item) {
    inventory.add(item);
  }

  private LinkedList<Item> inventory;


  public Player() {
    super(10, 5, 5);

    this.nextPosition = this.getPosition();
    this.directions = new HashSet<>();
    this.inventory = new LinkedList<>();
    this.state = 0;
  }

  public void update() {
    computeNextPosition();
  }

  public void addDirection(Direction direction) {
    this.directions.add(direction);
  }

  public void removeDirection(Direction direction) {
    this.directions.remove(direction);
  }

  public void computeNextPosition() {
    nextPosition = this.getPosition();
    if (this.directions.contains(Direction.UP)) {
      this.state = 2;
      nextPosition = new Position(nextPosition.getX(), nextPosition.getY() - this.getSpeed());
    }
    if (this.directions.contains(Direction.DOWN)) {
      this.state = 3;
      nextPosition = new Position(nextPosition.getX(), nextPosition.getY() + this.getSpeed());
    }
    if (this.directions.contains(Direction.LEFT)) {
      this.state = 1;
      nextPosition = new Position(nextPosition.getX() - this.getSpeed(), nextPosition.getY());
    }
    if (this.directions.contains(Direction.RIGHT)) {
      this.state = 0;
      nextPosition = new Position(nextPosition.getX() + this.getSpeed(), nextPosition.getY());
    }
  }

  public boolean collideNextBoundingBox(Drawable other) {
    int width = getWidth();
    int height = getHeight();
    int otherWidth = other.getWidth();
    int otherHeight = other.getHeight();
    int x = nextPosition.getX();
    int y = nextPosition.getY();
    int otherX = other.getPosition().getX();
    int otherY = other.getPosition().getY();
    return x + width > otherX && x < otherX + otherWidth && y + height > otherY && y < otherY + otherHeight;
  }


  public void draw(Graphics2D g2) {
    // affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
    switch (state){
      case 1:
        g2.drawImage(image[1], getPosition().getX(), getPosition().getY(), GamePanel.tileSize, GamePanel.tileSize, null);
        break;
      case 2:
        g2.drawImage(image[2], getPosition().getX(), getPosition().getY(), GamePanel.tileSize, GamePanel.tileSize, null);
        break;
      case 3:
        g2.drawImage(image[3], getPosition().getX(), getPosition().getY(), GamePanel.tileSize, GamePanel.tileSize, null);
        break;
      default:
        g2.drawImage(image[0], getPosition().getX(), getPosition().getY(), GamePanel.tileSize, GamePanel.tileSize, null);
    }
    if (GamePanel.DEBUG) {
      drawBoundings(g2, Color.BLUE);
    }
  }

  public void move() {
    setPosition(nextPosition);
    this.nextPosition = getPosition();
  }
}
