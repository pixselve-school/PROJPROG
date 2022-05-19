package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Portal_sideUp extends Portal {
  private static final BufferedImage image;

  static {
    try {
      image = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/Portal_sideUp.png")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Portal_sideUp(int map, int x, int y) {
    super(map, x, y);
  }

  @Override
  protected BufferedImage getImage() {
    return image;
  }

}
