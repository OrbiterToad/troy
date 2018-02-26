package ch.lebois.troyserver.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;

/**
 * @PROJECT Hermann
 */

@Service
public class ImageService {

    private byte[] toByteArray(List<Byte> in) {
        final int n = in.size();
        byte ret[] = new byte[n];
        for (int i = 0; i < n; i++) {
            ret[i] = in.get(i);
        }
        return ret;
    }

    public String getImage(ArrayList<Byte> list, String user) {
        try {
            String imgPath = new SimpleDateFormat("dd-MM hh-mm").format(new Date()) + "-" + user + ".jpg";
            //TODO: Change Path crete Folder screenshots
            ImageIO.write(getBufferedImage(list), "jpg",
                    new File("C:\\Users\\Felix\\Documents\\_Projekte\\Herman\\game-dev\\TroyServer\\src\\"
                             + "main\\resources\\screenshots\\" + imgPath));
            return imgPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BufferedImage getBufferedImage(ArrayList<Byte> list) throws IOException {
        return ImageIO.read(new ByteArrayInputStream(toByteArray(list)));
    }


}
