package entity;

import main.GamePanel;

public class Player extends Entity{



    public Player(GamePanel gp) {
        super(gp);
        worldX = 23 * gp.tileSize;
        worldY = 21 * gp.tileSize;
        screenX = gp.maxScreenCol/2 - gp.tileSize/2;
        screenY = gp.maxScreenRow/2 - gp.tileSize/2;

    }

}
