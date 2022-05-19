package utils;

public class Rectangle {
  private final Position topLeft;
  private final Position topRight;
  private final Position bottomRight;
  private final Position bottomLeft;

  public Rectangle(Position topLeft, Position topRight, Position bottomRight, Position bottomLeft) {
    this.topLeft = topLeft;
    this.topRight = topRight;
    this.bottomRight = bottomRight;
    this.bottomLeft = bottomLeft;
  }

  public Position getTopLeft() {
    return topLeft;
  }

  public Position getTopRight() {
    return topRight;
  }

  public Position getBottomRight() {
    return bottomRight;
  }

  public Position getBottomLeft() {
    return bottomLeft;
  }

  public int getWidth() {
    return topRight.getX() - topLeft.getX();
  }

  public int getHeight() {
    return bottomLeft.getY() - topLeft.getY();
  }

  public boolean collision(Rectangle rectangle) {
    return rectangle.getTopLeft().getX() <= getTopRight().getX() &&
        rectangle.getTopLeft().getY() <= getBottomLeft().getY() &&
        rectangle.getBottomRight().getX() >= getTopLeft().getX() &&
        rectangle.getBottomRight().getY() >= getTopLeft().getY();
  }


}
