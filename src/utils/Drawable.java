package utils;

import main.GamePanel;

import java.awt.*;

/**
 * Class that represents a drawable thing
 */
public abstract class Drawable {
    public Drawable() {
        isSolid = false;
        position = new Position(0, 0);
    }

    public Drawable(boolean solid, int x, int y, int width, int height) {
        isSolid = solid;
        position = new Position(x, y);
    }

    /**
     * The position on the map
     */
    private Position position;
    private final boolean isSolid;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Draw the thing
     */
    public abstract void draw(Graphics2D g2);

}
