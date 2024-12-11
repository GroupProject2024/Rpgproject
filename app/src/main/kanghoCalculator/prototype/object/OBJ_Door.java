package prototype.object;

import prototype.entity.Entity;
import prototype.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends Entity{
    public OBJ_Door(GamePanel gp){
        super(gp);
        name = "Door";
        collision = true;
        down1 = setup("/object/door");
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


    }
}
