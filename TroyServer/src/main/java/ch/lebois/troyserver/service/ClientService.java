package ch.lebois.troyserver.service;

import ch.menthe.io.FileHandler;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Felix
 * @date 28.12.2017
 * <p>
 * Project: GameDev
 * Package: ch.lebois.troyserver.service
 **/

@Service
public class ClientService {


    private final FileHandler fileService;

    public ClientService() {
        fileService = new FileHandler("clients.txt");
    }

    public void addClient(String client) {
        for (String c : getClients()) {
            if (c.equals(client)) {
                return;
            }
        }
        fileService.write(client);
    }


    public List<String> getClients() {
        return Arrays.asList(fileService.read().split("\\n"));
    }
}
