package prototype.object;

import prototype.entity.Entity;
import prototype.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePanel gp){
        super(gp);
        down1 = setup("/object/chest");
        name = "Chest";
        collision = true;

    }
}
