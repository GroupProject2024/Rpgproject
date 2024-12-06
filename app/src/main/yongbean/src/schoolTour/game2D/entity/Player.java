package schoolTour.game2D.entity;


import schoolTour.game2D.main.GamePanel;
import schoolTour.game2D.main.KeyHandler;
import schoolTour.game2D.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
//    public int hasKey = 0;
    int standCounter = 0;
    boolean moving = false;
    int pixelCounter = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight  / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();    // 12, 24, 24, 14
        solidArea.x = 1;
        solidArea.y = 1;
        solidArea.width = 46;
        solidArea.height = 46;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        // from Entity parent class ; location of character/player
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

//        try{
//            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/walkingSprites/boy_up_1.png")));
//            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/walkingSprites/boy_up_2.png")));
//            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/walkingSprites/boy_down_1.png")));
//            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/walkingSprites/boy_down_2.png")));
//            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/walkingSprites/boy_left_1.png")));
//            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/walkingSprites/boy_left_2.png")));
//            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/walkingSprites/boy_right_1.png")));
//            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/walkingSprites/boy_right_2.png")));
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }

    public BufferedImage setup(String imageName){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/walkingSprites/" + imageName + ".png")));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {
        if (!moving) {
            if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
                if (keyHandler.upPressed) {
                    direction = "up";
                } else if (keyHandler.downPressed) {
                    direction = "down";
                } else if (keyHandler.leftPressed) {
                    direction = "left";
                } else {
                    direction = "right";
                }

                moving = true;

                // CHECK TILE COLLISION
                collisionOn = false;
                gamePanel.collisionChecker.checkTile(this);

                // CHECK OBJECT COLLISION
                int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
                pickUpObject(objectIndex);
            } else {
                standCounter++;

                if (standCounter > 20) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
        }
        if (moving) {
            // IF COLLISION == FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                switch (direction) {
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
            if (spriteCounter >= 10) {   // this part can be changed into many different ways
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            pixelCounter += speed;

            if(pixelCounter >= 48) {
                moving = false;
                pixelCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
         if(i != 999) {


             // for treasure hunting demo -> beginning

//             String objectName = gamePanel.superObject[i].name;
//
//             switch (objectName) {
//                 case "Key":
//                     gamePanel.playSE(1);
//                     hasKey++;
//                     gamePanel.superObject[i] = null;
//                     gamePanel.ui.showMessage("You Got a Key!");
//                     break;
//                 case "Door":
//                     if(hasKey > 0) {
//                         gamePanel.playSE(3 );
//                         gamePanel.superObject[i] = null;
//                         hasKey--;
//                         gamePanel.ui.showMessage("You Opened the Door!");
//                     }
//                     break;
//                 case "Boots":
//                     gamePanel.playSE(2);
//                     speed += 2;
//                     gamePanel.superObject[i] = null;
//                     gamePanel.ui.showMessage("Speed Up!");
//                     break;
//                 case "Chest":
//                     gamePanel.ui.gameFinished = true;
//                     gamePanel.stopMusic();
//                     gamePanel.playSE(4);
//                     break;
//             }
         }
    }

    public void draw(Graphics2D g2d) {
//        g2d.setColor(Color.WHITE);
//        g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
                break;
            case "down":
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
                break;
            case "left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                break;
            case "right":
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                break;
        }
        g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

        // to check the collision
//        g2d.setColor(Color.RED);
//        g2d.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
