package schoolTour.game2D.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {  // To put all kinds of convenient funcs

    public BufferedImage scaleImage(BufferedImage original, int w, int h) {

        BufferedImage scaledImage = new BufferedImage(w, h, original.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(original, 0, 0, w, h, null);
        g2d.dispose();
        return scaledImage;
    }
}
