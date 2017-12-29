package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.service.ClientService;
import ch.menthe.io.FileHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "command/")
public class CommandController {

    private ClientService clientService;

    public CommandController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getCommands(@PathVariable(value = "client") String client, Model model) {
        clientService.addClient(client);

        FileHandler fileService = new FileHandler("commands\\command_" + client + ".properties");

        model.addAttribute("commands", fileService.read());
        return "commands";
    }

    @RequestMapping(value = {"/edit/{client}"}, method = RequestMethod.POST)
    public String edited(@PathVariable(value = "client") String client,
                         @RequestParam(name = "commands") String commands) {
        FileHandler propertiesFile = new FileHandler("commands\\command_" + client + ".properties");
        propertiesFile.clear();
        propertiesFile.write(commands);
        return "redirect:/dashboard/" + client;
    }

}
