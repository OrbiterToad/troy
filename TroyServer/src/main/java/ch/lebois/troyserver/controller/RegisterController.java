package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.entity.Message;
import ch.lebois.troyserver.data.entity.User;
import ch.lebois.troyserver.data.repository.MessageRepository;
import ch.lebois.troyserver.data.repository.UserRepository;
import ch.lebois.troyserver.service.ShaService;
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

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    private ShaService shaService;

    public RegisterController(MessageRepository messageRepository, UserRepository userRepository, ShaService shaService) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.shaService = shaService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRegister() {
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registered(@RequestParam(name = "user") String user,
                             @RequestParam(name = "email") String mail) {
        Message message = new Message();
        message.setPcNameFk("HERMANN");
        message.setText("User: " + user + " / " + "Mail: " + mail);
        message.setType("newUser");
        messageRepository.save(message);
        return "register";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(@RequestParam(name = "user") String username,
                             @RequestParam(name = "password") String password) {
        User user = new User();
        user.setName(username);
        user.setPasswordSha(shaService.encode(password));
        userRepository.save(user);

        return "redirect:../dashboard";
    }


}
