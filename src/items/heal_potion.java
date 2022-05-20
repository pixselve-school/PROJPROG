package items;

import tile.Wall;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class heal_potion extends Item{

    private static BufferedImage image;

    static {
        try {
            image = ImageIO.read(Objects.requireNonNull(Wall.class.getResource("/items/heal_potion.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public heal_potion (){
        super();

    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}
