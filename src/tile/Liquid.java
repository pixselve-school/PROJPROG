package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Liquid extends Tile {
  private static final BufferedImage image;

  static {
    try {
      image = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/WATER.png")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Liquid(int x, int y) {
    super(true, x, y);
  }

  @Override
  protected BufferedImage getImage() {
    return image;
  }
}
