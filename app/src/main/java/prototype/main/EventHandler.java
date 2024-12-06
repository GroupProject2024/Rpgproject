package prototype.main;

import jdk.jfr.Event;
import org.w3c.dom.css.Rect;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;

    }

    public void checkEvent() {
        if (hit(27, 16, "right")) {
            //event happens
            damagePit(gp.dialogueState);
            teleport(gp.dialogueState);
        }
        if (hit(23, 12, "up")){
            healingPool(gp.dialogueState);
        }

    }
    public void teleport(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "TelePort!";
        gp.player.worldX = gp.tileSize * 37;
        gp.player.worldY = gp.tileSize * 10;

    }
    public void healingPool(int gameState){
        if (gp.keyH.EnterPressed){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water \nYour life has been recovered.";
            gp.player.life = gp.player.maxLife;
        }
    }

    private void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "you fail to a pit!";
        gp.player.life-=1;
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;
        if (gp.player.solidArea.intersects(eventRect)) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        return hit;
    }
}

