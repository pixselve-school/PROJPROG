package tile;

import main.GamePanel;
import utils.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile extends Drawable {
  public Tile(boolean solid, int x, int y) {
    super(solid, x, y, GamePanel.tileSize, GamePanel.tileSize);
  }

  protected abstract BufferedImage getImage();

  @Override
  public void draw(Graphics2D g2) {
    g2.drawImage(getImage(), this.getPosition().getX(), this.getPosition().getY(), GamePanel.tileSize, GamePanel.tileSize, null);
    if (GamePanel.DEBUG) {
      if (isSolid()) {
        drawBoundings(g2, Color.RED);
      } else {
        drawBoundings(g2, Color.GREEN);
      }

    }
  }
}
