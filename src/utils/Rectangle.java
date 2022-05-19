package utils;

public class Rectangle {
  public Position getA() {
    return a;
  }

  public Position getB() {
    return b;
  }

  public Position getC() {
    return c;
  }

  public Position getD() {
    return d;
  }

  private Position a;
  private Position b;
  private Position c;
  private Position d;

  public Rectangle(Position a, Position b, Position c, Position d) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }

  public boolean collision(Rectangle rectangle) {
    return (this.a.getX() <= rectangle.getA().getX() && this.a.getX() >= rectangle.getB().getX()
        || this.b.getX() <= rectangle.getA().getX() && this.b.getX() >= rectangle.getB().getX())
        && (this.a.getY() <= rectangle.getA().getY() && this.a.getY() >= rectangle.getB().getY()
        || this.b.getY() <= rectangle.getA().getY() && this.b.getY() >= rectangle.getB().getY());
  }


}
