package ch.lebois.TroyServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "reciver")
public class ReciverController {

    @RequestMapping(value = {"/{user}", "/{user}/"}, method = RequestMethod.GET)
    public String getInfo(@PathVariable String user,
                          @RequestParam(name = "type", defaultValue = "") String type,
                          @RequestParam(name = "value", defaultValue = "") String value){

        System.out.println("user  - " + user);
        System.out.println("type  - " + type);
        System.out.println("value - " + value);

        return "test";
    }



}
