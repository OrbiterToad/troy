package ch.lebois.TroyServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "command")
public class Commands {

    @RequestMapping(value = {"", "/"},method = RequestMethod.GET)
    public String getCommands(){
        return "commands";
    }



    @RequestMapping(value = {"", "/"},method = RequestMethod.GET)
    public String getCommandEdit(){
        return "command";
    }

}
