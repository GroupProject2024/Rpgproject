package schoolTour.game2D.main;

import schoolTour.game2D.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gamePanel;
    Graphics2D g2d;
    Font arial_40, arial_80B;
//    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat df = new DecimalFormat("0.0");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
//        OBJ_Key key = new OBJ_Key(gamePanel);
//        keyImage = key.image;
    }

    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }
    public void draw(Graphics2D g2d) {
        this.g2d = g2d;     // to use g2d in other mathods in this class

        g2d.setFont(arial_40);
        g2d.setColor(Color.WHITE);

        // create different methods to pass for each actions -> otherwise-> draw method is too long to read
        if(gamePanel.gameState == gamePanel.playState) {
            // do playState stuff
        }
        if(gamePanel.gameState == gamePanel.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
        String txt = "PAUSED";
        int x = getXForCenteredText(txt);
        int y = gamePanel.screenHeight/2;

        g2d.drawString(txt, x, y);
    }

    public int getXForCenteredText(String text) {
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return gamePanel.screenWidth/2 - length/2;
    }


//    public void draw(Graphics2D g2d) {        // For previous drawing map -> changed to different version as we change the map
//
//        if(gameFinished) {
//
//            g2d.setFont(arial_40);
//            g2d.setColor(Color.WHITE);
//
//            String txt;
//            int txtLength;
//            int x;
//            int y;
//
//            txt = "You Found the Treasure!";
//            txtLength = (int)g2d.getFontMetrics().getStringBounds(txt, g2d).getWidth();
//            x = gamePanel.screenWidth/2 - txtLength/2;
//            y = gamePanel.screenHeight/2 - (gamePanel.tileSize * 3);
//            g2d.drawString(txt, x, y);
//            txt = "Your Time is : " + df.format(playTime) + " seconds!";
//            txtLength = (int)g2d.getFontMetrics().getStringBounds(txt, g2d).getWidth();
//            x = gamePanel.screenWidth/2 - txtLength/2;
//            y = gamePanel.screenHeight/2 + (gamePanel.tileSize * 4);
//            g2d.drawString(txt, x, y);
//
//            g2d.setFont(arial_80B);
//            g2d.setColor(Color.YELLOW);
//            txt = "Congratulations!";
//            txtLength = (int)g2d.getFontMetrics().getStringBounds(txt, g2d).getWidth();
//            x = gamePanel.screenWidth/2 - txtLength/2;
//            y = gamePanel.screenHeight/2 + (gamePanel.tileSize * 2);
//            g2d.drawString(txt, x, y);
//
//
//            gamePanel.gameThread = null;
//        }
//        else {
////            g2d.setFont(new Font("Arial", Font.PLAIN, 40));   // can eat-up all the data & time when we instantiate it every time
//            g2d.setFont(arial_40);
//            g2d.setColor(Color.WHITE);
//            g2d.drawImage(keyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
//            g2d.drawString("X " + gamePanel.player.hasKey, 74, 65);
//
//            // TIME
//            playTime += (double) 1/60;
//            g2d.drawString("Time: " + df.format(playTime) + "s", gamePanel.tileSize * 11, 65);
//
//            // MESSAGE
//            if (messageOn) {
//                g2d.setFont(g2d.getFont().deriveFont(20f));
//                g2d.drawString(message, gamePanel.tileSize/2, gamePanel.tileSize * 5);
//
//                messageCounter++;
//                if (messageCounter > 120) {
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//        }
//    }
}
