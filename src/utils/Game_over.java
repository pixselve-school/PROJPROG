package utils;

import entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Game_over extends Scene {
    private static final BufferedImage image;

    static {
        try {
            image = ImageIO.read(Objects.requireNonNull(Player.class.getResource("/menu/Game_over.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Game_over(String pathToMusic) {
        super(pathToMusic);
        //playMusic();
    }

    @Override
    public void initialize() {

    }

    public void update(Player player) {

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, 0, 0, GamePanel.screenWidth, GamePanel.screenHeight, null);
    }
}
