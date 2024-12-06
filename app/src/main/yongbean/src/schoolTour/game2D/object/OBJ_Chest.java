package schoolTour.game2D.object;

import schoolTour.game2D.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends SuperObject{
    GamePanel gamePanel;
    public OBJ_Chest(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Chest";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
