package HUD;

import items.Item;
import main.GamePanel;
import tile.Wall;
import utils.Drawable;
import utils.Music;
import utils.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class HUD {

  private static BufferedImage left_heart;
  private static BufferedImage right_heart;

  private static BufferedImage inventory;

  static {
    try {
      left_heart = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/HUD/left_half_heart.png")));
      right_heart = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/HUD/right_half_heart.png")));
      inventory = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/HUD/inventory.png")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void draw(Graphics2D g2) {
    drawHealth(g2);
    drawInventory(g2);
  }

  private static void drawHealth(Graphics2D g2) {
    final int WIDTH = 15;
    final int HEIGHT = (int) (WIDTH * 1.7);

    final int START_X = 5;
    final int START_Y = 5;


    for (int i = 0; i < GamePanel.player.getHealth(); i++) {
      if (i % 2 == 0) {
        g2.drawImage(left_heart, START_X + i * WIDTH, START_Y, WIDTH, HEIGHT, null);
      } else {
        g2.drawImage(right_heart, START_X + i * WIDTH, START_Y, WIDTH, HEIGHT, null);
      }
    }


  }

  private static void drawInventory(Graphics2D g2) {
    g2.setColor(Color.RED);
    final int WIDTH = 50;
    final int HEIGHT = 50;
    final int MARGIN = 10;
    final int COUNT = 3;

    final int START_X = GamePanel.screenWidth / 2 - (WIDTH * COUNT + COUNT * MARGIN) / 2;
    final int START_Y = GamePanel.screenHeight - HEIGHT - MARGIN;

    LinkedList<Item> inventoryItems = GamePanel.player.getInventory();

    for (int i = 0; i < COUNT; i++) {
      g2.drawImage(inventory, START_X + i * WIDTH + i * MARGIN, START_Y, WIDTH, HEIGHT, null);
      if (i < inventoryItems.size()) {
        inventoryItems.get(i).draw(g2, new Position(START_X + i * WIDTH + i * MARGIN + 7, START_Y + 7));
      }
    }
  }
}
