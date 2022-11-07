package shared.responses.messagingResponses.general;

import shared.models.Message;
import shared.models.UserCopy;
import shared.responses.Response;
import shared.responses.messagingResponses.functionalItems.ItemMessage1;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadUsernamesAndUnreadMessagesResponse extends Response {
    private final ArrayList<ItemMessage1> allMessages;

    public LoadUsernamesAndUnreadMessagesResponse(ArrayList<ItemMessage1> allMessages) {
        this.allMessages = allMessages;
    }

    public ArrayList<ItemMessage1> getAllMessages() {
        return allMessages;
    }

    public HashMap<UserCopy , ArrayList<Message>> convertToMap (){
        HashMap<UserCopy , ArrayList<Message>> map = new HashMap<>();
        for (ItemMessage1 itemMessage1:allMessages) {
            map.put(itemMessage1.getUserCopy() , itemMessage1.getMessages());
        }
        return map;
    }
}
