package ch.lebois.troyserver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix
 * @date 17.04.2018
 * <p>
 * Project: ServerControl
 * Package: ch.lebois.troyserver.model
 **/
public class ChatModel {

    private List<MessageModel> messages = new ArrayList<>();

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

}
