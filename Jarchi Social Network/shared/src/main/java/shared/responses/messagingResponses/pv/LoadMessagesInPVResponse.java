package shared.responses.messagingResponses.pv;

import shared.models.MessageCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class LoadMessagesInPVResponse extends Response {
    private final ArrayList<MessageCopy> messages;

    public LoadMessagesInPVResponse(ArrayList<MessageCopy> messages) {
        this.messages = messages;
    }

    public ArrayList<MessageCopy> getMessages() {
        return messages;
    }
}
