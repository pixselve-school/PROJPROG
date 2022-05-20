package tile;

import main.GamePanel;
import utils.Drawable;
import utils.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile extends Drawable {
  public Tile(boolean solid, int x, int y) {
    super(solid, x, y, GamePanel.tileSize, GamePanel.tileSize);
  }

  protected abstract BufferedImage getImage();

  @Override
  public void draw(Graphics2D g2) {
    draw(g2, this.getPosition(), GamePanel.tileSize, GamePanel.tileSize);
  }

  @Override
  public void draw(Graphics2D graphics2D, Position position, int width, int height) {
    graphics2D.drawImage(getImage(), position.getX(), position.getY(), width, height, null);
    if (GamePanel.DEBUG) {
      if (isSolid()) {
        drawBoundings(graphics2D, Color.RED);
      } else {
        drawBoundings(graphics2D, Color.GREEN);
      }

    }
  }
}
