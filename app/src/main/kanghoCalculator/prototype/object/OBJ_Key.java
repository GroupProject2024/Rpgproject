package prototype.object;

import prototype.entity.Entity;
import prototype.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp){
        super(gp);
        name="Key";
        down1 = setup("/object/key");
    }
}
