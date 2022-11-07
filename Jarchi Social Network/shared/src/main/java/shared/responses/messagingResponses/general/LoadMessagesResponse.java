package shared.responses.messagingResponses.general;

import shared.models.MessageCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class LoadMessagesResponse extends Response {
    private final ArrayList<MessageCopy> allMessages;

    public LoadMessagesResponse(ArrayList<MessageCopy> allMessages) {
        this.allMessages = allMessages;
    }

    public ArrayList<MessageCopy> getAllMessages() {
        return allMessages;
    }
}
