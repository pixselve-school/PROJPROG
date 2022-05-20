package items;

import main.GamePanel;
import utils.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item {
  public abstract BufferedImage getImage();

  public void draw(Graphics2D graphics2D, Position position) {
    graphics2D.drawImage(getImage(), position.getX(), position.getY(), (int) (GamePanel.tileSize * 0.7), (int) (GamePanel.tileSize * 0.7), null);
  };
}
