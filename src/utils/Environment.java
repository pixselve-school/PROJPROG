package utils;

import entity.Entity;
import entity.Player;
import main.GamePanel;
import tile.Tile;

import java.awt.*;
import java.util.LinkedList;

public class Environment {
    private LinkedList<Tile> tiles;
    private LinkedList<Entity> entities;

    private Environment() {
        tiles = new LinkedList<>();
        entities = new LinkedList<>();
    }

    public Environment(String pathToMap) {
//        TODO:
    }

    public void update(Player player) {
        for (Tile tile : tiles) {
            if (tile.isSolid()) {
                if (tile.isColliding(player)) {
                    player.setPosition(player.getOldPosition());
                }
            }
        }
        for (Entity entity : entities) {
            if (entity.isSolid()) {
                if (entity.isColliding(player)) {
                    player.setPosition(player.getOldPosition());
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        tiles.forEach(tile -> tile.draw(g2));
        entities.forEach(tile -> tile.draw(g2));
    }
}
