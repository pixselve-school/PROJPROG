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
    boundingBox = new Rectangle(new Position(x - width / 2, y - height / 2), new Position(x + width / 2, y + height / 2), new Position(x + width / 2, y - height / 2), new Position(x - width / 2, y + height / 2));
  }

  /**
   * The position on the map
   */
  private Position position;

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

  private Rectangle boundingBox;

  public boolean isColliding(Drawable other) {
    return boundingBox.collision(other.boundingBox);
  }

  /**
   * Draw the thing
   */
  public abstract void draw(Graphics2D g2);

}
