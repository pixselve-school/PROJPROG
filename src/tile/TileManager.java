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

public class TileManager {
//	Tile[] tile;
	LinkedList<Tile> tiles;
	int maxTiles = 10;
 	int mapTileType[][];
	int id;
	GamePanel gamePanel;

	public TileManager(GamePanel gamePanel, int id) {
		this.gamePanel = gamePanel;
		this.id = id;
//		tile = new Tile[maxTiles];
//		getTileImage();
		mapTileType = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
		tiles = new LinkedList<>();
		loadMap();
	}
	

	//Mise à jour de la map
	public void updateMap(int id){

	}


	// Cette m�thode charge la map
	public void loadMap() {
		//charger le fichier txt de la map
		try {

			InputStream is = getClass().getResourceAsStream("/maps/map"+id+".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;
			// Parcourir le fichier txt pour r�cup�rer les valeurs
			while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
				String line = br.readLine();
				while (col < gamePanel.maxScreenCol) {
					String numbers[] = line.split(" ");
					for(String s : numbers){
						System.out.println(s);
					}
					int num = Integer.parseInt(numbers[col]);
					mapTileType[col][row] = num;
					int ts = gamePanel.tileSize;
					if(num < 0){
						tiles.add(new Portal(-num,col*ts,row*ts));
					}
					else {
						switch (num) {
							case 0:
								tiles.add(new Ground(col * ts, row * ts));
								break;
							case 1:
								tiles.add(new Wall(col * ts, row * ts));
								break;
							case 2:
								tiles.add(new Liquid(col * ts, row * ts));
								break;
							default:
								tiles.add(new Ground(col * ts, row * ts));
						}
					}
					col++;
				}
				if (col == gamePanel.maxScreenCol) {
					col = 0;
					row ++;
				}

			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		}

	
	public void draw(Graphics2D g2) {


		for (Tile tile : tiles) {
			tile.draw(g2);
		}
		
	}
}
