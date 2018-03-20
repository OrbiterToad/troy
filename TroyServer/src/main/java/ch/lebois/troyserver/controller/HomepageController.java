package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.entity.Client;
import ch.lebois.troyserver.data.entity.Message;
import ch.lebois.troyserver.data.repository.ClientRepository;
import ch.lebois.troyserver.data.repository.MessageRepository;
import ch.lebois.troyserver.model.HomepageModel;
import ch.lebois.troyserver.service.CookieService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project: Hermann
 **/

@Controller
@RequestMapping("dashboard")
public class HomepageController {

    private CookieService cookieService;
    private ClientRepository clientRepository;
    private MessageRepository messageRepository;

    public HomepageController(CookieService cookieService, ClientRepository clientRepository,
                              MessageRepository messageRepository) {
        this.cookieService = cookieService;
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHomepage(Model model, HttpServletRequest request) {
        try {
            model.addAttribute("user", cookieService.getCurrentUser(request));
            List<HomepageModel> list = new ArrayList<>();

            for (Client client : clientRepository.findAll()) {
                list.add(getHomepageModel(client));
            }

            model.addAttribute("model", list);
            return "homepage";
        } catch (NullPointerException e) {
            return "redirect:/login";
        }

    }

    private HomepageModel getHomepageModel(Client client) {
        HomepageModel homepageModel = new HomepageModel();
        homepageModel.setClient(client.getPcName());
        homepageModel.setLogcount(0);
        homepageModel.setErrorcount(0);
        homepageModel.setOs(client.getOs());
        homepageModel.setLastseen(client.getLastseen());
        homepageModel.setArch(client.getArch());

        for (Message m : messageRepository.findAll()) {
            if (m.getPcNameFk().equals(client.getPcName())) {
                homepageModel.setLogcount(homepageModel.getLogcount() + 1);
                if (m.getType().equals("errorout")) {
                    homepageModel.setErrorcount(homepageModel.getErrorcount() + 1);
                }
            }
        }
        return homepageModel;
    }


}
