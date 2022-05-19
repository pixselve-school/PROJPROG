package tile;

import utils.Drawable;
import utils.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Ground extends Tile {
    private static final BufferedImage image;

    static {
        try {
            image = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/GRASS.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Ground(int x, int y){
        super(false, x, y);
    }

    @Override
    protected BufferedImage getImage() {
        return image;
    }
}
