package entity.monsters;

import main.GamePanel;
import tile.Wall;
import utils.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Skeleton extends Monster {
  private static final BufferedImage image;

  static {
    try {
      image = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/entity/skeleton.png")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Skeleton(Position position) {
    super(position, 3, 3, 3, (int) (GamePanel.tileSize * 1.5), (int) (GamePanel.tileSize * 1.5));
  }

  public BufferedImage getImage() {
    return image;
  }
}
