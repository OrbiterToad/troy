package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.service.ClientService;
import ch.lebois.troyserver.service.CookieService;
import ch.lebois.troyserver.service.FileService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final ClientService clientService;
    private FileService fileService;
    private CookieService cookieService;


    DashboardController(FileService fileService, CookieService cookieService, ClientService clientService) {
        this.fileService = fileService;
        this.cookieService = cookieService;
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model, HttpServletRequest request) {
        try {
            cookieService.getCurrentUser(request);

            model.addAttribute("clients", clientService.getClients());
            return "homepage";
        } catch (NullPointerException e) {
            return "redirect:/login";
        }

    }


    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getClientControlPanel(@PathVariable(value = "client") String client,
                                        Model model, HttpServletRequest request) {
        try {
            cookieService.getCurrentUser(request);

            fileService.setFilePath("commands\\command_" + client + ".properties");
            model.addAttribute("commands", fileService.read());

            fileService.setFilePath("logs\\client_" + client + ".log");
            model.addAttribute("log", fileService.read());
            model.addAttribute("client", client);
            return "dashboard";
        } catch (NullPointerException e) {
            return "redirect:/login";
        }

    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.POST)
    public String clearClientLogs(@PathVariable(value = "client") String client) {
        fileService.setFilePath("logs\\client_" + client + ".log");
        fileService.clear();
        return "redirect:/dashboard/" + client;
    }

}
