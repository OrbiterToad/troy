package ch.lebois.troyserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project: Hermann
 **/


@Controller
@RequestMapping(value = {"dashboard/help", "dashboard/help/"})
public class HelpController {


    @RequestMapping(method = RequestMethod.GET)
    public String getHelpPage() {
        return "help";
    }
}
