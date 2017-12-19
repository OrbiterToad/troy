package ch.lebois.TroyServer.controller;

import ch.lebois.TroyServer.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Felix
 * @date 19.12.2017
 * <p>
 * Project: GameDev
 * Package: ch.lebois.TroyServer.controller
 **/


@Controller
@RequestMapping(value = {"", "/"})
public class DashboardController {

    private FileService fileService;


    DashboardController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model) {

        fileService.setFilePath("commands.properties");
        model.addAttribute("commands", fileService.read());

        fileService.setFilePath("log.log");
        model.addAttribute("log", fileService.read());
        return "dashboard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String clear() {
        fileService.setFilePath("log.log");
        fileService.clear();
        return "redirect:/";
    }

}
