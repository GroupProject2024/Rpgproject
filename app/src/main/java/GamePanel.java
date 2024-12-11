import javax.swing.*;
import java.awt.*;

public class GamePanel extends JFrame implements Runnable {

    final int originalTile = 16;
    final int scale = 3;
    public int screenCol = 16;
    public int screenRow = 12;
    public int tileSize = originalTile * scale; // 48
    public int maxScreenWidth = tileSize * screenCol; // 48 * 16
    public int maxScreenHeight = tileSize * screenRow; // 48 * 12
    int FPS = 60;

    Thread gameThread;


    public GamePanel() {


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

}
