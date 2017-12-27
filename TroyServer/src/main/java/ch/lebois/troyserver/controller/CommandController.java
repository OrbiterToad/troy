package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "command")
public class CommandController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = "application/json")
    public String getCommands(Model model) {
        FileService fileService = new FileService();
        fileService.setFilePath("commands.properties");

        model.addAttribute("commands", fileService.read());
        return "commands";
    }

    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.GET)
    public String getEdit(Model model) {
        FileService fileService = new FileService();
        fileService.setFilePath("commands.properties");

        model.addAttribute("commands", fileService.read());
        return "commandsEdit";
    }

    @RequestMapping(value = {"/edit", "/edit/"}, method = RequestMethod.POST)
    public String edited(@RequestParam(name = "commands") String commands) {
        FileService propertiesFile = new FileService();
        propertiesFile.setFilePath("commands.properties");
        propertiesFile.clear();
        propertiesFile.write(commands);
        return "redirect:/dashboard";
    }

}
