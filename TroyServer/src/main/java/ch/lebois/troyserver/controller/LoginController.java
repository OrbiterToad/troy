package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.service.CookieService;
import ch.lebois.troyserver.service.EncodingShaImpl;
import ch.lebois.troyserver.service.UserService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Felix
 * @date 08.11.2017
 * <p>
 * Project: login-app
 * Package: ch.felix.loginapp.controller
 **/

@Controller
@RequestMapping(value = {"", "/login"})
public class LoginController {

    private final EncodingShaImpl shaService;
    private final UserService userService;
    private final CookieService cookieService;

    public LoginController(EncodingShaImpl shaService, UserService userService, CookieService cookieService) {
        this.shaService = shaService;
        this.userService = userService;
        this.cookieService = cookieService;
    }


    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String login(@RequestParam(name = "user", defaultValue = "") String user,
                        @RequestParam(name = "password", defaultValue = "") String password,
                        HttpServletResponse response) {

        //If User login exists goto Dashboard else back to Login with error message
        if (userService.userExits(user, shaService.encode(password))) {
            cookieService.setUserCookie(response, user);
            return "redirect:/dashboard";
        } else {
            return "redirect:login/?error=Login+not+valid&user=" + user;
        }
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }
}
