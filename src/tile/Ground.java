package tile;

import utils.Drawable;
import utils.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;



public class Ground extends Tile {
    private static final BufferedImage[]image  = new BufferedImage[4];

    static {
        try {
            image[0] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/BRICK_2.png")));
            image[1] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/BRICK_3.png")));
            image[2] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/BRICK_4.png")));
            image[3] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/BRICK.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Ground(int x, int y){
        super(false, x, y);
    }

    @Override
    protected BufferedImage getImage() {
        Random random = new Random();
        int value = random.nextInt(0 + 3);
        return image[value];
    }
}
