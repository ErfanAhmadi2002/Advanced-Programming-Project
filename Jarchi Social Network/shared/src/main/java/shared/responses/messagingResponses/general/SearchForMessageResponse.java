package shared.responses.messagingResponses.general;

import shared.models.Message;
import shared.models.UserCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class SearchForMessageResponse extends Response {
    private final int sendingState;
    //state 1: sent successfully
    //state 2: not possible to do
    //state 3: account does not exist
    private final ArrayList<Message> messages;
    private final ArrayList<UserCopy> usersInChat;

    public SearchForMessageResponse(int sendingState, ArrayList<Message> messages, ArrayList<UserCopy> usersInChat) {
        this.sendingState = sendingState;
        this.messages = messages;
        this.usersInChat = usersInChat;
    }

    public int getSendingState() {
        return sendingState;
    }

    public ArrayList<UserCopy> getUsersInChat() {
        return usersInChat;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
