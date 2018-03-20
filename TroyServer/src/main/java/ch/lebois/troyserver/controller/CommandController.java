package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.entity.Client;
import ch.lebois.troyserver.data.repository.ClientRepository;
import ch.lebois.troyserver.model.CommandModel;
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
    private CookieService cookieService;

    public CommandController(ClientRepository clientRepository, CookieService cookieService) {
        this.clientRepository = clientRepository;
        this.cookieService = cookieService;
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getCommands(@PathVariable(value = "client") String clientParam, CommandModel commandModel,
                              Model model) {

        Client client = clientRepository.findOne(clientParam);
        if (client == null) {
            createClient(clientParam);
            return "dashboard";
        }

        commandModel.setCommands(client.getCommands());
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
            String user = cookieService.getCurrentUser(request);

            Client client = clientRepository.findOne(clientParam);
            client.setCommands(commands);
            clientRepository.save(client);
        } catch (NullPointerException e) {
            return "redirect:/login";
        }

        return "redirect:/dashboard/" + clientParam;
    }

}
