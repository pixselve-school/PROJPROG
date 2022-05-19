package utils;

public class Rectangle {
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

  private Position topLeft;
  private Position topRight;
  private Position bottomRight;
  private Position bottomLeft;

  public Rectangle(Position topLeft, Position topRight, Position bottomRight, Position bottomLeft) {
    this.topLeft = topLeft;
    this.topRight = topRight;
    this.bottomRight = bottomRight;
    this.bottomLeft = bottomLeft;
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
