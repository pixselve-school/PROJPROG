package utils;

import entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Main_Menu extends Scene {

    private static final BufferedImage image;

    static {
        try {
            image = ImageIO.read(Objects.requireNonNull(Player.class.getResource("/menu/Menu_principal.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Main_Menu(String pathToMusic) {
        super(pathToMusic);
    }

    public void update(Player player){

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, 0, 0, GamePanel.screenWidth, GamePanel.screenHeight, null);
    }
}
