package entity.monsters;

import entity.Entity;
import utils.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Monster extends Entity {
  @Override
  public void draw(Graphics2D graphics2D, Position position, int width, int height) {
    graphics2D.drawImage(getImage(), position.getX(), position.getY(), width, height, null);
  }

  public Monster(Position position, int health, int strength, int speed, int width, int height) {
    super(position, health, strength, speed, width, height);
  }

  public abstract BufferedImage getImage();

  @Override
  public void draw(Graphics2D g2) {
    draw(g2, this.getPosition(), getWidth(), getHeight());

  }
}
