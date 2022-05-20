package entity.monsters;

import entity.Entity;
import main.GamePanel;
import utils.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Monster extends Entity {


  private Position vector;

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

  public void moveInDirection() {
    this.setPosition(new Position(this.getPosition().getX() + vector.getX() * getSpeed() / 20, this.getPosition().getY() + vector.getY() * getSpeed() / 20));
  }

  public void orientateToPlayer() {
    int vector_x = GamePanel.player.getPosition().getX() - getPosition().getX();
    int vector_y = GamePanel.player.getPosition().getY() - getPosition().getY();
    double norm = Math.sqrt(Math.pow(vector_x, 2) + Math.pow(vector_y, 2));
    vector = new Position ((int) (vector_x / norm * 10), (int) ((vector_y / norm) * 10));
  }


}
