package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.entity.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Felix
 * @date 26.03.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyserver.controller
 **/

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @RequestMapping(method = RequestMethod.GET)
    public String getRegister() {
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registered(@RequestParam(name = "user") String user,
                             @RequestParam(name = "mail") String mail) {

        Message message = new Message();
        message.setPcNameFk("HERMANN");
        message.setText("User: " + user + "\n"
                        + "Mail: " + mail);
        message.setType("newUser");

        return "../";
    }


}
