package utils;

import entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Win_Menu extends Scene{
    private static final BufferedImage image;

    static {
        try {
            image = ImageIO.read(Objects.requireNonNull(Player.class.getResource("/Menu/Win_Menu.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Win_Menu(String pathToMusic) {
        super(pathToMusic);
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
