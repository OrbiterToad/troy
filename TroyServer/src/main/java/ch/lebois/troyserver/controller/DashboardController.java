package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.model.DashboardModel;
import ch.lebois.troyserver.model.HomepageModel;
import ch.lebois.troyserver.service.ClientService;
import ch.lebois.troyserver.service.CookieService;
import ch.menthe.io.FileHandler;
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
    private FileHandler fileService;
    private CookieService cookieService;


    DashboardController(CookieService cookieService, ClientService clientService) {
        this.fileService = new FileHandler("");
        this.cookieService = cookieService;
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHomepage(HomepageModel homepageModel, Model model, HttpServletRequest request) {
        try {
            cookieService.getCurrentUser(request);

            homepageModel.setClients(clientService.getClients());
            model.addAttribute("model", homepageModel);
            return "homepage";
        } catch (NullPointerException e) {
            return "redirect:/login";
        }

    }


    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getClientControlPanel(@PathVariable(value = "client") String client, DashboardModel dashboardModel,
                                        Model model, HttpServletRequest request) {
        try {
            cookieService.getCurrentUser(request);

            fileService.setFilePath("commands\\command_" + client + ".properties");
            dashboardModel.setCommands(fileService.read());

            fileService.setFilePath("logs\\client_" + client + ".log");
            dashboardModel.setLog(fileService.read());
            dashboardModel.setClient(client);
            model.addAttribute("model", dashboardModel);
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
