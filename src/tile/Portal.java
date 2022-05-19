package tile;

import utils.Drawable;
import utils.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Portal extends Tile {
    private BufferedImage fond;
    private int tp;

    public Portal(int map, BufferedImage image, int x, int y){
        super(false, x, y);
        tp = map;
        fond = image;

    }

    @Override
    protected BufferedImage getImage() {
        return fond;
    }
}
