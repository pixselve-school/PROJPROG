package utils;

import main.GamePanel;

import java.awt.*;

/**
 * Class that represents a drawable thing
 */
public abstract class Drawable {
  /**
   * @param solid
   * @param x      the x position (center of the object)
   * @param y      the y position (center of the object)
   * @param width  the width of the object
   * @param height the height of the object
   */
  public Drawable(boolean solid, int x, int y, int width, int height) {
    isSolid = solid;
    position = new Position(x, y);
    this.width = width;
    this.height = height;
  }

  /**
   * The position on the map
   */
  private Position position;

  private final int width;

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  private final int height;

  public Position getOldPosition() {
    return oldPosition;
  }

  private Position oldPosition;


  public boolean isSolid() {
    return isSolid;
  }

  private final boolean isSolid;

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.oldPosition = this.position;
    this.position = position;
  }

  public void drawBoundings(Graphics g){
    drawBoundings(g, Color.RED);
  }

  public void drawBoundings(Graphics g, Color color){
    g.setColor(color);
    g.drawRect(getPosition().getX(), getPosition().getY(), width, height);
  }


  private boolean isFill = false;


  public boolean isColliding(Drawable other) {
    return this.getPosition().getX() < other.getPosition().getX() + other.getWidth() &&
        this.getPosition().getX() + this.getWidth() > other.getPosition().getX() &&
        this.getPosition().getY() < other.getPosition().getY() + other.getHeight() &&
        this.getPosition().getY() + this.getHeight() > other.getPosition().getY();
  }

  /**
   * Draw the thing
   */
  public abstract void draw(Graphics2D g2);

}
