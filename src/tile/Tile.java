package tile;

import main.GamePanel;
import utils.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile extends Drawable {
	protected abstract BufferedImage getImage();

	public Tile(boolean solid, int x, int y){
		super(solid, x, y);
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getImage(), this.getPosition().getX(), this.getPosition().getY(), GamePanel.tileSize, GamePanel.tileSize, null);
	}
}
