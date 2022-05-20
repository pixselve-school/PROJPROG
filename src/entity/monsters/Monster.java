package entity.monsters;

import entity.Entity;
import utils.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Monster extends Entity {
  public Monster(Position position, int health, int strength, int speed, int width, int height) {
    super(position, health, strength, speed, width, height);
  }

  public abstract BufferedImage getImage();

  @Override
  public void draw(Graphics2D g2) {
    g2.drawImage(getImage(), this.getPosition().getX(), this.getPosition().getY(), getWidth(), getHeight(), null);

  }
}
