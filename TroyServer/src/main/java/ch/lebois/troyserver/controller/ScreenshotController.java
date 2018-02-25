package ch.lebois.troyserver.controller;

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

    @RequestMapping(value = "/{name}")
    public String getScreenshot(@PathVariable(value = "name") String name, Model model) {
        try {
            model.addAttribute("name", name);
        } catch (NullPointerException e) {
            return "redirect: ../../login";
        }
        return "screenshot";
    }
}
