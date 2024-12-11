package schoolTour.game2D.main;

import schoolTour.game2D.entity.Player;
import schoolTour.game2D.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS

    final int originalTileSize = 16;    // Single tile = 16x16 -> standard size for any ritual 2D                                       game
    final int scale = 3;    // rescale the tile size into appropriate scale

    public final int tileSize = originalTileSize * scale;  // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;    // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow;   // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

//    FPS
    int fps = 60;

    // to start the loop of the game
    Thread gameThread;

    // keyboard handling
    KeyHandler keyHandler = new KeyHandler();

    // player
    public Player player = new Player(this, keyHandler);

    // tileManager class to use
    TileManager tileManager = new TileManager(this);

    // class to check the collision in gamePanel
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);  // passing main.GamePanel class to the thread
        gameThread.start();
    }

    @Override
//    public void run() { // creates the game loop w
//
//        double drawInterval = (double) 1000000000 /fps;   // 0.01666 sec
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread.isAlive()) {
//
//            update();   // 1. Update : update info such as character position
//            repaint();  // 2. Draw : draw the updated info ito screen
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    public void run() { // delta/accumulative method

        double drawInterval = (double) 1000000000 /fps;   // 0.01666 sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while(gameThread.isAlive()) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {    // layers as line moves down
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);

        player.draw(g2d);

        g2d.dispose();
    }
}
