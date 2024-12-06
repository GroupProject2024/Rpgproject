package prototype.entity;

import prototype.main.GamePanel;
import prototype.main.KeyHandler;
import prototype.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public int hasKey = 0;
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2); // show camera view for main character
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
     //load player image in res file
            up1 = setup("/player/boy_up_1");
            up2 = setup("/player/boy_up_2");
            down1 = setup("/player/boy_down_1");
            down2 = setup("/player/boy_down_2");
            left1 = setup("/player/boy_left_1");
            left2 = setup("/player/boy_left_2");
            right1 = setup("/player/boy_right_1");
            right2 = setup("/player/boy_right_2");


    }
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);


        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public void update(){
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true ||keyH.rightPressed == true) {// when key pushed
            //location information update
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }
            //check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            //check object Collision
            int ObjIndex =  gp.cChecker.checkObject(this, true);
            pickUpObject(ObjIndex);
            ///Check NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK event
            gp.eHandler.checkEvent();
            gp.keyH.EnterPressed = false;


            //when pass not solid area
            if (collisionOn == false){

                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void pickUpObject(int i) {
        if (i != 999) {
//
        }
    }
    public void interactNPC(int i){
        if (i != 999) {
            if (gp.keyH.EnterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }
    public void draw(Graphics2D g2){
        //describe walking
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case  "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            case  "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}
