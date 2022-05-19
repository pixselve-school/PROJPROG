package tile;

public abstract class Portal extends Tile {
  private final int tp;

  public Portal(int map, int x, int y) {
    super(false, x, y);
    tp = map;
  }

  public int getTp() {
    return tp;
  }
}
