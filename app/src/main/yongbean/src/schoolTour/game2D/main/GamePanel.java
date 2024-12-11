package schoolTour.game2D.main;

import schoolTour.game2D.entity.Entity;
import schoolTour.game2D.entity.NPC_OldMan;
import schoolTour.game2D.entity.Player;
import schoolTour.game2D.object.SuperObject;
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

//    FPS
    int fps = 60;

            // SYSTEM

    // tileManager class to use
    TileManager tileManager = new TileManager(this);
    // keyboard handling
    public KeyHandler keyHandler = new KeyHandler(this);
    // sound class
    Sound music = new Sound();
    Sound se = new Sound();
    // class to check the collision in gamePanel
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    // AssetSetting
    public AssetSetter assetSetter = new AssetSetter(this);
    // UI
    public UI ui = new UI(this);
    // to start the loop of the game
    Thread gameThread;

            //ENTITY AND OBJECT

    // player
    public Player player = new Player(this, keyHandler);
    // superObject
    public SuperObject[] superObject = new SuperObject[10];
    // NPC
    public Entity[] npc = new Entity[10];

            // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
//        playMusic(0);
//        stopMusic();
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);  // passing GamePanel class to the thread
        gameThread.start();
    }

    @Override
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
        if(gameState == playState) {
            // player
            player.update();
            // npc
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
        }
        if(gameState == pauseState) {
            //
        }
    }

    public void paintComponent(Graphics g) {    // layers as line moves down
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if(keyHandler.checkDebugTime) drawStart = System.nanoTime();

        // TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2d);
        }
        // others
        else {
            // tile
            tileManager.draw(g2d);

            // object (enhanced for loop)
            for (SuperObject object : superObject) {
                if (object != null) {
                    object.draw(g2d, this);
                }
            }

            // NPC
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.draw(g2d);
                }
            }

            // player
            player.draw(g2d);

            ui.draw(g2d);
        }

        // DEBUG
        if(keyHandler.checkDebugTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2d.setColor(Color.WHITE);
            g2d.drawString("Draw time : " + passed, 10, 400);
            System.out.println("Draw time : " + passed);
        }

        g2d.dispose();
    }

    // background music
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    // sound effect
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
