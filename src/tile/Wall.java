package tile;

import utils.Drawable;
import utils.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Wall extends Tile {
    private static final BufferedImage image;

    static {
        try {
            image = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/BRICK2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Wall(int x, int y){
        super(true, x, y);
    }

    @Override
    protected BufferedImage getImage() {
        return image;
    }
}
