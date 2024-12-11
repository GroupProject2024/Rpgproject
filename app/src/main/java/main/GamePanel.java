package main;


import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // 16 x 16 real TileSize
    final int scale = 3; // 3 sclae

    public final int tileSize = originalTileSize * scale; // 48 x 48 TileSize
    public final int maxScreenCol = 16; //width Size
    public final int maxScreenRow = 12; //Height Size
    //width x heigth 4:3 Ratio
    public final int screenWidth = tileSize * maxScreenCol;//768 px
    public final int screenHeight = tileSize * maxScreenRow;//578 px

    //world setting
    public final int maxWorldCol = 12;
    public final int maxWorldRow = 7;
    public final int worldWidth = tileSize * maxWorldCol; //
    public final int worldHeight = tileSize * maxWorldRow;
    public TileManager tileManager = new TileManager(this);


    int FPS = 60;

    public Player player = new Player(this);
    Thread gameThread;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);//when you use Double Buffer VG more efficient
//        this.addKeyListener(keyH);
        this.setFocusable(true);//main.GamePanel can be "focused" to receive key input

    }

    public void update(){


    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;// 0.0166sec
        double delta = 0; // currentFrmae, lastFrame delta value
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        // start update() -> repaint() -> nextDraw(start)
        //allocated Time is 0.01666sec

        while (gameThread != null) {
            currentTime = System.nanoTime();// currentTime bring nanotime

            delta += (currentTime - lastTime) / drawInterval;// f
            timer += (currentTime - lastTime);
            lastTime = currentTime;// pre time update
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }


        }
    }

    public void setUpGame() {
        TileManager tileM = new TileManager(this);

        }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);

//        player.draw(g2d);

        g2d.dispose();

    }
}
