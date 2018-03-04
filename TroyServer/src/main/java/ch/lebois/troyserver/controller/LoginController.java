package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.service.CookieService;
import ch.lebois.troyserver.service.ShaService;
import ch.lebois.troyserver.service.UserService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = {"", "/login"})
public class LoginController {
    private final ShaService shaService;
    private final UserService userService;
    private final CookieService cookieService;

    public LoginController(ShaService shaService, UserService userService, CookieService cookieService) {
        this.shaService = shaService;
        this.userService = userService;
        this.cookieService = cookieService;
    }


    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public String login(@RequestParam(name = "user", defaultValue = "") String userParam,
                        @RequestParam(name = "password", defaultValue = "") String passwordParam,
                        HttpServletResponse response) {

        if (userService.userAllowedLogin(userParam, shaService.encode(passwordParam))) {
            cookieService.setUserCookie(response, userParam);
            System.out.println(userParam + " loged in");
            return "redirect:/dashboard";
        } else {
            System.out.println("Could not Log in with username " + userParam + " and password " + passwordParam);
            return "redirect:login/?error=Login+not+valid&user=" + userParam;
        }
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }
}
