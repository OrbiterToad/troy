package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.repository.ImageRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Project: Hermann
 **/

@Controller
@RequestMapping(value = "dashboard/img/")
public class ScreenshotController {

    private ImageRepository imageRepository;

    public ScreenshotController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @RequestMapping(value = "{file}", method = RequestMethod.GET)
    public void getImg(@PathVariable(value = "file") String filePath, HttpServletResponse response) throws IOException {
//                TODO: Change Path
        InputStream in = null;
        try {
            File file = new File("C:\\Users\\Felix\\Documents\\_Projekte\\Herman\\game-dev\\TroyServer\\src\\main\\"
                                 + "resources\\screenshots\\" + filePath + ".jpg");
            in = new FileInputStream(file);
            response.setContentType("image/jpeg");
            IOUtils.copy(in, response.getOutputStream());
            System.out.println(file.getTotalSpace());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    @RequestMapping(value = "{file}/remove", method = RequestMethod.GET)
    public String removeImg(@PathVariable(value = "file") String filePath,
                            @RequestParam(value = "client") String clientParam) throws IOException {
        imageRepository.delete(imageRepository.findImageByName(filePath));

        return "redirect:/dashboard/" + clientParam;
    }


}
