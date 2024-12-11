package prototype.object;

import prototype.entity.Entity;
import prototype.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_heart extends Entity {

    public OBJ_heart(GamePanel gp){

        super(gp);
        name="Heart";

        image = setup("/object/heart_full");
        image2 = setup("/object/heart_half");
        image3 = setup("/object/heart_blank");





    }
}
