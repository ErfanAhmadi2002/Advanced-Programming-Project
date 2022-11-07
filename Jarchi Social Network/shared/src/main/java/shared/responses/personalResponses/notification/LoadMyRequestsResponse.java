package shared.responses.personalResponses.notification;

import shared.models.UserCopy;
import shared.responses.Response;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadMyRequestsResponse extends Response {
    private final ArrayList<ItemNotification1> myRequests;

    public LoadMyRequestsResponse(ArrayList<ItemNotification1> myRequests) {
        this.myRequests = myRequests;
    }

    public HashMap<UserCopy , Integer> convertToMap (){
        HashMap<UserCopy , Integer> map = new HashMap<>();
        for (ItemNotification1 itemNotification1 :myRequests) {
            map.put(itemNotification1.getKey() , itemNotification1.getValue());
        }
        return map;
    }

    public ArrayList<ItemNotification1> getMyRequests() {
        return myRequests;
    }
}

