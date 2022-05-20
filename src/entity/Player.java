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

  private static final BufferedImage image;

  static {
    try {
      image = ImageIO.read(Objects.requireNonNull(Player.class.getResource("/player/Character.png")));
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
    super(16, 4, 5);
    this.nextPosition = this.getPosition();
    this.directions = new HashSet<>();
    this.inventory = new LinkedList<>();
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
      nextPosition = new Position(nextPosition.getX(), nextPosition.getY() - this.getSpeed());
    }
    if (this.directions.contains(Direction.DOWN)) {
      nextPosition = new Position(nextPosition.getX(), nextPosition.getY() + this.getSpeed());
    }
    if (this.directions.contains(Direction.LEFT)) {
      nextPosition = new Position(nextPosition.getX() - this.getSpeed(), nextPosition.getY());
    }
    if (this.directions.contains(Direction.RIGHT)) {
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
    draw(g2, getPosition(), GamePanel.tileSize, GamePanel.tileSize);
  }

  @Override
  public void draw(Graphics2D graphics2D, Position position, int width, int height) {
    // affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
    graphics2D.drawImage(image, position.getX(), position.getY(), width, height, null);
    if (GamePanel.DEBUG) {
      drawBoundings(graphics2D, Color.BLUE);
    }
  }

  public void move() {
    setPosition(nextPosition);
    this.nextPosition = getPosition();
  }
}
