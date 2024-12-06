package schoolTour.game2D.main;

import schoolTour.game2D.entity.NPC_OldMan;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {

            // for treasure hunting game -> demo

//        gamePanel.superObject[0] = new OBJ_Key(gamePanel);
//        gamePanel.superObject[0].worldX = 23 * gamePanel.tileSize;
//        gamePanel.superObject[0].worldY = 7 * gamePanel.tileSize;
//
//        gamePanel.superObject[1] = new OBJ_Key(gamePanel);
//        gamePanel.superObject[1].worldX = 23 * gamePanel.tileSize;
//        gamePanel.superObject[1].worldY = 40 * gamePanel.tileSize;
//
//        gamePanel.superObject[2] = new OBJ_Key(gamePanel);
//        gamePanel.superObject[2].worldX = 38 * gamePanel.tileSize;
//        gamePanel.superObject[2].worldY = 8 * gamePanel.tileSize;
//
//        gamePanel.superObject[3] = new OBJ_Door(gamePanel);
//        gamePanel.superObject[3].worldX = 10 * gamePanel.tileSize;
//        gamePanel.superObject[3].worldY = 11 * gamePanel.tileSize;
//
//        gamePanel.superObject[4] = new OBJ_Door(gamePanel);
//        gamePanel.superObject[4].worldX = 8 * gamePanel.tileSize;
//        gamePanel.superObject[4].worldY = 28 * gamePanel.tileSize;
//
//        gamePanel.superObject[5] = new OBJ_Door(gamePanel);
//        gamePanel.superObject[5].worldX = 12 * gamePanel.tileSize;
//        gamePanel.superObject[5].worldY = 22 * gamePanel.tileSize;
//
//        gamePanel.superObject[6] = new OBJ_Chest(gamePanel);
//        gamePanel.superObject[6].worldX = 10 * gamePanel.tileSize;
//        gamePanel.superObject[6].worldY = 7 * gamePanel.tileSize;
//
//        gamePanel.superObject[7] = new OBJ_Boots(gamePanel);
//        gamePanel.superObject[7].worldX = 37 * gamePanel.tileSize;
//        gamePanel.superObject[7].worldY = 41 * gamePanel.tileSize;
    }

    public void setNPC() {
         gamePanel.npc[0] = new NPC_OldMan(gamePanel);
         gamePanel.npc[0].worldX = gamePanel.tileSize*21;
         gamePanel.npc[0].worldY = gamePanel.tileSize*21;
    }
}
