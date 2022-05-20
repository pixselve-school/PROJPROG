package entity.monsters;

import main.GamePanel;
import tile.Wall;
import utils.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Vampire extends Monster {

  private static final BufferedImage image;

  static {
    try {
      image = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/entity/vampire.png")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Vampire(Position position) {
    super(position, 5, 4, 4, (int) (GamePanel.tileSize * 1.5), (int) (GamePanel.tileSize * 1.5));
  }


  @Override
  public BufferedImage getImage() {
    return image;
  }
}
