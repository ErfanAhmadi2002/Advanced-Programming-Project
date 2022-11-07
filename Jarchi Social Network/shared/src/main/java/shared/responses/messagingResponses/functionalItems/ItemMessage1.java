package shared.responses.messagingResponses.functionalItems;

import shared.models.Message;
import shared.models.UserCopy;

import java.util.ArrayList;

public class ItemMessage1 {
    private final UserCopy userCopy;
    private final ArrayList<Message> messages;

    public ItemMessage1(UserCopy userCopy, ArrayList<Message> messages) {
        this.userCopy = userCopy;
        this.messages = messages;
    }

    public UserCopy getUserCopy() {
        return userCopy;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
