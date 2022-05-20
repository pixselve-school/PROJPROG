package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Wall_back extends Wall {
  private static final BufferedImage[] images = new BufferedImage[4];
  private boolean Torch;

  static {
    try {
      images[0] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/WALL_back.png")));
      images[1] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/WALL_backWithTorch.png")));
      images[2] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/WALL_backWithTorch2.png")));
      images[3] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/WALL_backWithTorch3.png")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Wall_back(boolean torche,int x, int y) {
    super(x, y);
    this.Torch = torche;
  }

  @Override
  protected BufferedImage getImage() {
    if(Torch){
      return images[0];
    }
    else {
      Random r = new Random();
      return images[r.nextInt(1,4)];
    }
  }
}
