package ch.lebois.TroyServer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "reciver")
public class ReciverController {


    final Logger log = LoggerFactory.getLogger(ReciverController.class);

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String getInfo(@RequestParam(name = "type", defaultValue = "") String type,
                          @RequestParam(name = "value", defaultValue = "") String value){

        log.info("type  - " + type);
        log.info("value - " + value);

        return "test";
    }



}
