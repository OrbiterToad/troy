package ch.lebois.troyserver.controller;

import ch.menthe.io.FileHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "reciver/")
public class ReciverController {

    private FileHandler fileService;


    ReciverController() {
        this.fileService = new FileHandler("");
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getInfo(@PathVariable(value = "client") String client,
                          @RequestParam(name = "value", defaultValue = "") String value) {
        fileService.setFilePath("logs\\client_" + client + ".log");
        fileService.write(value);
        return "receiver";
    }


}
