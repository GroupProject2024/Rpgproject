package tile;

import main.GamePanel;
import main.Utool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {
    GamePanel gp;

    public Tile[] tile;
    public int mapTileNum[][];
    public Utool utool = new Utool();

    public TileManager(GamePanel gp){
        this.gp = gp;
    tile = new Tile[50];
    mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
    getTileImage();

    loadMap("/map/world01.txt");

    }
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/grass01.png")));
            tile[0].image = utool.scaleImage(tile[0].image, gp.tileSize, gp.tileSize);

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/wall.png")));
            tile[1].collision = true;
            tile[1].image = utool.scaleImage(tile[1].image, gp.tileSize, gp.tileSize);

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water01.png")));
            tile[2].collision = true;
            tile[2].image = utool.scaleImage(tile[2].image, gp.tileSize, gp.tileSize);

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/earth.png")));
            tile[3].image = utool.scaleImage(tile[3].image, gp.tileSize, gp.tileSize);

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/table01.png")));
            tile[4].image = utool.scaleImage(tile[4].image, gp.tileSize, gp.tileSize);

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road05.png")));
            tile[5].image = utool.scaleImage(tile[5].image, gp.tileSize, gp.tileSize);

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void loadMap(String filepath){

        InputStream is = getClass().getResourceAsStream(filepath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int Col = 0;
        int Row = 0;

        try {
            while(Row < gp.maxWorldRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");// 0 0 0 0 0 ....
                for (Col = 0; Col < numbers.length; Col++) {
                    int num = Integer.parseInt(numbers[Col]);
                    mapTileNum[Col][Row] = num;
                    System.out.print(num+ " ");
                }
                Row++;
                System.out.println("");
            }
            br.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D g2) {
        int Col = 0;
        int Row = 0;

        while (Col < gp.maxWorldCol && Row < gp.maxWorldRow) {
            int num = mapTileNum[Col][Row];

            int worldX = Col * gp.tileSize;
            int worldY = Row * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
//            if ()
            g2.drawImage(tile[num].image, screenX, screenY, null);
            Col++;
            if (Col == gp.maxWorldCol) {
                Row++;
                Col = 0;
            }
        }
    }




    public void setup(){




    }
}
