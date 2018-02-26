package ch.lebois.troyserver.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;

/**
 * @USER Felix
 * @DATE 24.02.2018
 * @PROJECT Hermann
 */

@Service
public class ImageService {

    private static byte[] toByteArray(List<Byte> in) {
        final int n = in.size();
        byte ret[] = new byte[n];
        for (int i = 0; i < n; i++) {
            ret[i] = in.get(i);
        }
        return ret;
    }

    public String getImage(ArrayList<Byte> list, String user) {
        try {
            InputStream in = new ByteArrayInputStream(toByteArray(list));
            BufferedImage bImageFromConvert = ImageIO.read(in);

            String imgPath = new SimpleDateFormat("dd-MM hh-mm").format(new Date()) + "-" + user + ".jpg";
            //TODO: Change Path crete Folder screenshots
            ImageIO.write(bImageFromConvert, "jpg",
                    new File("C:\\Users\\Felix\\Documents\\_Projekte\\Herman\\game-dev\\TroyServer\\src\\"
                             + "main\\resources\\screenshots\\" + imgPath));
            return imgPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
