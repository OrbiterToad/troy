package ch.lebois.troyserver.model;

import ch.lebois.troyserver.data.Client;

/**
 * @author Felix
 * @date 31.01.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyserver.model
 **/
public class DashboardModel {

    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
