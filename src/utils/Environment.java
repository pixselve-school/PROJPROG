package utils;

import entity.Entity;
import entity.Player;
import main.GamePanel;
import tile.Tile;

import java.awt.*;
import java.util.LinkedList;

public class Environment {
    private static Environment instance;
    private LinkedList<Tile> tiles;
    private LinkedList<Entity> entities;

    private Environment() {
        tiles = new LinkedList<>();
        entities = new LinkedList<>();
    }

    public Environment(String pathToMap) {
//        TODO:
    }

    public void draw(Graphics2D g2) {
        tiles.forEach(tile -> tile.draw(g2));
        entities.forEach(tile -> tile.draw(g2));
    }
}
