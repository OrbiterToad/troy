package ch.lebois.troyserver.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Felix
 * @date 07.02.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyserver.data
 **/
public class ClientTest {

    private Client client;

    private ClientRepository clientRepository;

    public ClientTest(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Before
    public void setUp() {
        client = new Client();
        client.setPcName("Test PC");
        client.setLastseen("13:01:59");
        client.setOs("Ops");
        client.setCommands("ls -la");
        client.setInfo(":D");
        clientRepository.save(client);
    }

    @Test
    public void getPcName() throws Exception {
        assertEquals(client.getPcName(), clientRepository.findOne("Test PC").getPcName());
    }

    @Test
    public void getOs() throws Exception {
    }

    @Test
    public void getInfo() throws Exception {
    }

    @Test
    public void getCommands() throws Exception {
    }

    @Test
    public void getLastseen() throws Exception {
    }

}