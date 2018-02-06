package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.Client;
import ch.lebois.troyserver.data.Message;
import ch.lebois.troyserver.model.DashboardModel;
import ch.lebois.troyserver.model.HomepageModel;
import ch.lebois.troyserver.repository.ClientRepository;
import ch.lebois.troyserver.repository.MessageRepository;
import ch.lebois.troyserver.service.CookieService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project: Hermann
 * Package: ch.lebois.TroyServer.controller
 **/


@Controller
@RequestMapping(value = {"dashboard", "dashboard/"})
public class DashboardController {

    private CookieService cookieService;

    private ClientRepository clientRepository;
    private MessageRepository messageRepository;

    DashboardController(CookieService cookieService, ClientRepository clientRepository,
                        MessageRepository messageRepository) {
        this.clientRepository = clientRepository;
        this.cookieService = cookieService;
        this.messageRepository = messageRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHomepage(Model model, HttpServletRequest request) {
        try {
            cookieService.getCurrentUser(request);

            List<HomepageModel> list = new ArrayList<>();

            for (Client c : clientRepository.findAll()) {
                HomepageModel h = new HomepageModel();
                h.setClient(c.getPcName());
                h.setLogcount(0);
                h.setErrorcount(0);
                for (Message m : messageRepository.findAll()) {
                    if (m.getPcNameFk().equals(c.getPcName())) {
                        h.setLogcount(h.getLogcount() + 1);
                        if (m.getType().equals("errorout")) {
                            h.setErrorcount(h.getErrorcount() + 1);
                        }
                    }
                }
                list.add(h);
            }


            model.addAttribute("model", list);
            return "homepage";
        } catch (NullPointerException e) {
            return "redirect:/login";
        }

    }


    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getClientControlPanel(@PathVariable(value = "client") String clientParam, Model model,
                                        DashboardModel dashboardModel, HttpServletRequest request) {
        try {
            cookieService.getCurrentUser(request);

            Client client = clientRepository.findOne(clientParam);
            if (client == null) {
                return "redirect:/dashboard";
            }
            dashboardModel.setClient(client);

            Object logs = "";
            Iterable<Message> logList = messageRepository.findAll();
            for (Message m : logList) {
                if (m.getPcNameFk().equals(clientParam)) {
                    logs = logs + m.getText() + "\n";
                }
            }

            model.addAttribute("model", dashboardModel);
            model.addAttribute("logs", logs);
            return "dashboard";
        } catch (NullPointerException e) {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.POST)
    public String clearClientLogs(@PathVariable(value = "client") String clientParam) {
        Iterable<Message> logList = messageRepository.findAll();
        for (Message m : logList) {
            if (m.getPcNameFk().equals(clientParam)) {
                messageRepository.delete(m);
            }
        }
        return "redirect:/dashboard/" + clientParam;
    }

}
