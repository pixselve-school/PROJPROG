package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Portal_sideDown extends Portal {
  private static final BufferedImage image;

  static {
    try {
      image = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/Portal_sideDown.png")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public Portal_sideDown(int map, int x, int y) {
    super(map, x, y);
  }

  @Override
  protected BufferedImage getImage() {
    return image;
  }

}
