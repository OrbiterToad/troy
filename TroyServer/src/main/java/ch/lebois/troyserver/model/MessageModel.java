package ch.lebois.troyserver.model;

/**
 * @author Felix
 * @date 17.04.2018
 * <p>
 * Project: ServerControl
 * Package: ch.lebois.troyserver.model
 **/
public class MessageModel {

    private String client;
    private String text;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
