package entity;

import main.GamePanel;
import tile.Wall;
import utils.Music;
import utils.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Chest extends Entity {

  private static BufferedImage closedImage;
  private static BufferedImage openedImage;

  private static Music openSound;

  public boolean isOpen() {
    return isOpen;
  }
  public void open() {
    openSound.play();
    openSound.clip.loop(0);
    isOpen = true;
  }
  private boolean isOpen;


  static {
    try {
      closedImage = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/entity/closed_chest.png")));
      openedImage = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/entity/opened_chest.png")));
      openSound = new Music("open_chest");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public Chest(Position position) {
    super(new Position((int) (position.getX() + (0.3 * GamePanel.tileSize / 2)), (int) (position.getY() + (0.3 * GamePanel.tileSize / 2))), 9999, 0, 0, (int) (GamePanel.tileSize * 0.7), (int) (GamePanel.tileSize * 0.7));
    isOpen = false;
  }

  @Override
  public void draw(Graphics2D g2) {
    g2.drawImage(isOpen ? openedImage : closedImage, this.getPosition().getX(), this.getPosition().getY(), (int) (GamePanel.tileSize * 0.7), (int) (GamePanel.tileSize * 0.7), null);
    if (GamePanel.DEBUG) {
      if (isSolid()) {
        drawBoundings(g2, Color.RED);
      } else {
        drawBoundings(g2, Color.GREEN);
      }

    }
  }
}
