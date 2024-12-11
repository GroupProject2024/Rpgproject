package entity;

import main.GamePanel;

public class Player extends Entity{



    public Player(GamePanel gp) {
        super(gp);
        worldX = gp.screenWidth/2 - gp.tileSize/2;
        worldY = gp.screenHeight/2 - gp.tileSize/2;
        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

    }

}
