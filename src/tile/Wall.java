package tile;

import utils.Drawable;
import utils.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Wall extends Tile {
    public Wall(int x, int y){
        super(true, x, y);
    }
}
