package tile;

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

    @Override
    protected BufferedImage getImage() {
        return image;
    }
}
