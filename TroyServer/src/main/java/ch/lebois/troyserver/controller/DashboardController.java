package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.service.CookieService;
import ch.lebois.troyserver.service.FileService;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value = {"dashboard", "dashboard/"})
public class DashboardController {

    private FileService fileService;
    private CookieService cookieService;


    DashboardController(FileService fileService, CookieService cookieService) {
        this.fileService = fileService;
        this.cookieService = cookieService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model, HttpServletRequest request) {
        try {
            cookieService.getCurrentUser(request);

            fileService.setFilePath("commands.properties");
            model.addAttribute("commands", fileService.read());

            fileService.setFilePath("log.log");
            model.addAttribute("log", fileService.read());
            return "dashboard";
        } catch (NullPointerException e) {
            return "redirect:/login";
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public String clear() {
        fileService.setFilePath("log.log");
        fileService.clear();
        return "redirect:/dashboard";
    }

}
