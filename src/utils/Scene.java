package utils;

import entity.Player;

import java.awt.*;

public abstract class Scene {
  public abstract void update(Player player);
  public abstract void draw(Graphics2D g2);
}
