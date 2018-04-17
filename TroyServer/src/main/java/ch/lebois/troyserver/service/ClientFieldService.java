package ch.lebois.troyserver.service;

import ch.lebois.troyserver.data.entity.Client;
import ch.lebois.troyserver.data.entity.ClientField;
import ch.lebois.troyserver.data.repository.ClientFieldRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Felix
 * @date 11.04.2018
 * <p>
 * Project: ServerControl
 * Package: ch.lebois.troyserver.service
 **/

@Service
public class ClientFieldService {

    private ClientFieldRepository clientFieldRepository;

    public ClientFieldService(ClientFieldRepository clientFieldRepository) {
        this.clientFieldRepository = clientFieldRepository;
    }

    public void setFieldValue(Client client, String field, String value) {
        ClientField clientField = clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), field);

        if (clientField == null) {
            clientField = new ClientField();
            clientField.setClient(client.getPcName());
            clientField.setField(field);
            clientField.setValue(value);
        } else {
            clientField.setValue(value);
        }
        clientFieldRepository.save(clientField);
    }

    private List<ClientField> getClientField(Client client) {
        return clientFieldRepository.findClientFieldsByClient(client.getPcName());
    }

    private ClientField getClientFieldByField(Client client, String field) {
        return clientFieldRepository.findClientFieldByClientAndField(client.getPcName(), field);
    }

}
