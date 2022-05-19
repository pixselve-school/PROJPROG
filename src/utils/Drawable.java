package utils;

import java.awt.*;

/**
 * Class that represents a drawable thing
 */
public abstract class Drawable {
    /**
     * The position on the map
     */
    private Position position;

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
