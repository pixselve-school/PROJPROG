package items;

import tile.Wall;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sword extends Item {

  private static BufferedImage image;

  static {
    try {
      image = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/items/sword.png")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Sword() {
    super(2);
  }


  @Override
  public BufferedImage getImage() {
    return image;
  }
}
