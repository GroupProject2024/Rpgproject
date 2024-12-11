import prototype.main.GamePanel;
import prototype.tile.Tile;

import java.io.*;

public class TileManager {
    GamePanel gamePanel;

    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(){
    tile = new Tile[10];
    mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxScreenRow];


    }
    public void loadMap(){


    }


    public void setup(String filePath){
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));



    }
}
