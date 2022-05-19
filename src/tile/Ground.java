package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;



public class Ground extends Tile {
    private static final BufferedImage[] images = new BufferedImage[4];

    private BufferedImage image;

    static {
        try {
            images[0] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/BRICK_2.png")));
            images[1] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/BRICK_3.png")));
            images[2] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/BRICK_4.png")));
            images[3] = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/tiles/BRICK.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Ground(int x, int y){
        super(false, x, y);
        Random r = new Random();
        image = images[r.nextInt(4)];

    }

    @Override
    protected BufferedImage getImage() {
        return image;
    }
}
