package entity;

import main.GamePanel;

public class Entity {
    GamePanel gp;

    public int worldX, worldY;
    public int screenX, screenY;
    public int speed;
    public Entity(GamePanel gp){
        this.gp = gp;
    }
}
