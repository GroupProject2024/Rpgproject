package schoolTour.game2D.object;

import schoolTour.game2D.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends SuperObject {
    GamePanel gamePanel;

    public OBJ_Key(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        name = "Key";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
