package ch.lebois.troyserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Project: Hermann
 **/
@Controller
@RequestMapping(value = "error")
public class ErrorController {
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Page not found")
    String get404() {
        return "redirect:error?error=Page+not+found";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Server error")
    String get500() {
        return "redirect:error?error=Server+error";
    }

    @RequestMapping(method = RequestMethod.GET)
    String getErrorPage() {
        return "error";
    }
}
