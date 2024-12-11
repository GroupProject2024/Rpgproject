package prototype.entity;

import prototype.main.GamePanel;
import prototype.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity {
    public NPC_OldMan(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        setDialogue();
        getImage();
    }
    public void setDialogue(){
        dialogue[0] = "Hello, Student. I am a professor OldMan.";
        dialogue[1] = "Now, you are a HGU student";
        dialogue[2] = "You should graduate this school!";
        dialogue[3] = "Find Bag and graduate!";


    }



    public void getImage() {
        //load player image in res file
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }

    @Override
    public void speak() {
        //do this character specific dialogue
        super.speak();
    }

    public void setAction(){
        actionLockCounter ++;
        if (actionLockCounter == 120 ) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i <= 100 && i > 75) {
                direction = "right";
            }
        actionLockCounter = 0;
    }
}

}