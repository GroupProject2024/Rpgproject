package schoolTour.game2D.main;

import schoolTour.game2D.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gamePanel;
    Graphics2D g2d;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;    // 0: the first Screen, 1: the second Screen

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }
    public void draw(Graphics2D g2d) {
        this.g2d = g2d;     // to use g2d in other mathods in this class

        g2d.setFont(arial_40);
        g2d.setColor(Color.WHITE);

        // create different methods to pass for each actions
            // -> otherwise
                // -> draw method is too long to read

        // TITLE STATE
        if(gamePanel.gameState == gamePanel.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if(gamePanel.gameState == gamePanel.playState) {
            // do playState stuff
        }
        // PAUSED STATE
        if(gamePanel.gameState == gamePanel.pauseState) {
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gamePanel.gameState == gamePanel.dialogueState) {
            drawDialogueScreen();
        }
    }

    private void drawTitleScreen() {
        g2d.setColor(new Color(105, 155, 175));
        g2d.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        if(titleScreenState == 0) {

            // TITLE NAME
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 60F));
            String text = "BLUE BOY ADVENTURE";

            int x = getXForCenteredText(text);
            int y = gamePanel.tileSize * 2 + gamePanel.tileSize / 2;

            // Shadows
            g2d.setColor(Color.BLACK);
            for(double i = 0.1; i < 5; i += 0.1) {
                g2d.drawString(text, (float) (x + i), (float) (y + i));
            }

            // Main Color
            g2d.setColor(Color.WHITE);
            g2d.drawString(text, x, y);

            // BLUE BOY IMAGE
            x = gamePanel.screenWidth / 2 - gamePanel.tileSize;
            y += gamePanel.tileSize * 2;
            g2d.drawImage(gamePanel.player.down1, x, y, gamePanel.tileSize * 2, gamePanel.tileSize * 2, null);

            // MENU
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 30F));

            text = "NEW GAME";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize * 4;
            g2d.drawString(text, x, y);
            if(commandNum == 0) {
                g2d.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize;
            g2d.drawString(text, x, y);
            if(commandNum == 1) {
                g2d.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "QUIT";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize;
            g2d.drawString(text, x, y);
            if(commandNum == 2) {
                g2d.drawString(">", x - gamePanel.tileSize, y);
            }
        }
        else if(titleScreenState == 1) {

            // CLASS SELECTION SCREEN
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 30F));

            String text = "SELECT YOUR CLASS!";

            int x = getXForCenteredText(text);
            int y = gamePanel.tileSize * 3;

            g2d.drawString(text, x, y);

            text = "Fighter";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize * 2;
            g2d.drawString(text, x, y);
            if(commandNum == 0) {
                g2d.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "Thief";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize;
            g2d.drawString(text, x, y);
            if(commandNum == 1) {
                g2d.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "Sorcerer";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize;
            g2d.drawString(text, x, y);
            if(commandNum == 2) {
                g2d.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "Back";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize * 2;
            g2d.drawString(text, x, y);
            if(commandNum == 3) {
                g2d.drawString(">", x - gamePanel.tileSize, y);
            }
        }


    }

    public void drawPauseScreen() {

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
        String txt = "PAUSED";
        int x = getXForCenteredText(txt);
        int y = gamePanel.screenHeight/2;

        g2d.drawString(txt, x, y);
    }

    public void drawDialogueScreen() {

        // 1. Display dialogue window
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 24F));
        x += gamePanel.tileSize - 10;
        y += gamePanel.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2d.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color color = new Color(0, 0, 0, 180);
        g2d.setColor(color);
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXForCenteredText(String text) {
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return gamePanel.screenWidth/2 - length/2;
    }
}
