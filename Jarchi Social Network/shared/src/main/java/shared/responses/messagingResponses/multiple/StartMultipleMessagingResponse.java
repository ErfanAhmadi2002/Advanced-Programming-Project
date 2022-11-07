package shared.responses.messagingResponses.multiple;

import shared.models.UserCopy;
import shared.responses.Response;
import shared.responses.messagingResponses.functionalItems.ItemMessage2;

import java.util.ArrayList;
import java.util.HashMap;

public class StartMultipleMessagingResponse extends Response {
    private final ArrayList<ItemMessage2> allCategories;

    public StartMultipleMessagingResponse(ArrayList<ItemMessage2> allCategories) {
        this.allCategories = allCategories;
    }

    public ArrayList<ItemMessage2> getAllCategories() {
        return allCategories;
    }

    public HashMap<String , ArrayList<UserCopy>> convertToMap (){
        HashMap<String , ArrayList<UserCopy>> map = new HashMap<>();
        for (ItemMessage2 itemMessage2:allCategories) {
            map.put(itemMessage2.getCategoryName() , itemMessage2.getUsersInCategory());
        }
        return map;
    }
}
