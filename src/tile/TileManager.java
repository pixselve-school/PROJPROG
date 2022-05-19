package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import main.GamePanel;
import utils.Drawable;

public class TileManager extends Drawable {
//	Tile[] tile;
	LinkedList<Tile> tiles;
//	int maxTiles = 10;
//	int mapTileNum[][];
	
	public TileManager() {
		
//		tile = new Tile[maxTiles];
//		getTileImage();
		loadMap();
	}
	
//	public void getTileImage() {
//		// Charge les diff�rentes tuiles dans le vecteur tile[]
//
//		try {
//			tile[0] = new Tile();
//			tile[0].image = ImageIO.read(getClass().getResource("/tiles/GRASS.png"));
//
//			tile[1] = new Tile();
//			tile[1].image = ImageIO.read(getClass().getResource("/tiles/BRICK2.png"));
//
//			tile[2] = new Tile();
//			tile[2].image = ImageIO.read(getClass().getResource("/tiles/WATER.png"));
//
//			tile[3] = new Tile();
//			tile[3].image = ImageIO.read(getClass().getResource("/tiles/LAVA.png"));
//
//			tile[4] = new Tile();
//			tile[4].image = ImageIO.read(getClass().getResource("/tiles/SAND.png"));
//
//			tile[5] = new Tile();
//			tile[5].image = ImageIO.read(getClass().getResource("/tiles/SNOW.png"));
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	//Mise à jour de la map
	public void updateMap(int id){

	}


	// Cette m�thode charge la map
	public void loadMap() {
		//charger le fichier txt de la map
//		try {
//
//			InputStream is = getClass().getResourceAsStream("/maps/map0.txt");
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//			int col = 0;
//			int row = 0;
//			// Parcourir le fichier txt pour r�cup�rer les valeurs
//			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
//				String line = br.readLine();
//				while (col < gp.maxScreenCol) {
//					String numbers[] = line.split(" ");
//					int num = Integer.parseInt(numbers[col]);
//					mapTileNum [col][row] = num;
//					col++;
//					}
//					if (col == gp.maxScreenCol) {
//						col = 0;
//						row ++;
//					}
//
//			}
//
//				br.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	
	
	public void draw(Graphics2D g2, GamePanel gamePanel) {

//		int col = 0;
//		int row = 0;
//		int x = 0;
//		int y = 0;


		for (Tile tile : tiles) {
			tile.draw(g2, gamePanel);
		}

//		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
//
//
//
//
//			int tileNum = mapTileNum[col][row];
//
//			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
//			col ++;
//			x += gp.tileSize;
//			if (col == gp.maxScreenCol) {
//				col = 0;
//				row ++;
//				x = 0;
//				y += gp.tileSize;
//			}
//		}
		
	}
}
