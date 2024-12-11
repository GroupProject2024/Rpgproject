package prototype.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, EnterPressed;

    boolean checkDrawTime = false;
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_UP) {
              gp.ui.commandNum --;
              if (gp.ui.commandNum < 0){
                  gp.ui.commandNum = 2;
              }
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum ++;
                if (gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if (gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1){

                }
                if (gp.ui.commandNum ==2){
                    System.exit(0);
                }
            }
        }
        if (gp.gameState == gp.playState){
            if(code == KeyEvent.VK_UP){
                //when UP button pushed
                upPressed = true;
            }
            if(code == KeyEvent.VK_DOWN){
                //when down button pushed
                downPressed = true;
            }
            if(code == KeyEvent.VK_LEFT){
                //when left button pushed
                leftPressed = true;
            }
            if(code == KeyEvent.VK_RIGHT){
                //when right button pushed
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER){
                EnterPressed = true;
            }
        }
    else if(gp.gameState == gp.pauseState){
        if (code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }
    else if (gp.gameState == gp.dialogueState){
        if (code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_E){
            ePressed = false;
        }

        if(code == KeyEvent.VK_UP){
            //when UP button not pushed
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN){
            //when Down button not pushed
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT){
            //when left button not pushed
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            //when right button not pushed
            rightPressed = false;
        }
    }
}
