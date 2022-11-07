package shared.responses.messagingResponses.pv;

import shared.models.Tweet;
import shared.responses.Response;

import java.util.ArrayList;

public class StartSavedMessagesResponse extends Response {
    private final ArrayList<Tweet> allMessages;

    public StartSavedMessagesResponse(ArrayList<Tweet> allMessages) {
        this.allMessages = allMessages;
    }

    public ArrayList<Tweet> getAllMessages() {
        return allMessages;
    }
}
