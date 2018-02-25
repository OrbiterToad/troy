package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.ImageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @USER Felix
 * @DATE 25.02.2018
 * @PROJECT Hermann
 */

@Controller
@RequestMapping(value = "screenshot")
public class ScreenshotController {

    private ImageRepository imageRepository;

    public ScreenshotController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @RequestMapping(value = "/{name}")
    public String getScreenshot(@PathVariable(value = "name") String name, Model model) {
        model.addAttribute("name", name);

        return "screenshot";
    }
}
