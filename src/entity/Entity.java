package entity;

import main.GamePanel;
import utils.Drawable;
import utils.Position;

import java.awt.image.BufferedImage;

public abstract class Entity extends Drawable {
  public BufferedImage idleImage;
  private int health;
  private final int strength;
  private final int speed;

  public Entity(int health, int strength, int speed) {
    super(true, 360, 450, GamePanel.tileSize, GamePanel.tileSize);
    this.health = health;
    this.strength = strength;
    this.speed = speed;
  }

  public Entity(Position position, int health, int strength, int speed, int width, int height) {
    super(true, position.getX(), position.getY(), width, height);
    this.health = health;
    this.strength = strength;
    this.speed = speed;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int dammage) {
    this.health = health-dammage;
  }

  public int getStrength() {
    return strength;
  }

  public int getSpeed() {
    return speed;
  }
}
