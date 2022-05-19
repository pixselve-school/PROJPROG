package utils;

public class Position {
    private int x;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addX(int x) {
        this.x += x;
    }
    public void addY(int y) {
        this.y += y;
    }
}
