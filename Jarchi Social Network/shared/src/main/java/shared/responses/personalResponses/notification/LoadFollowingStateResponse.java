package shared.responses.personalResponses.notification;

import shared.models.UserCopy;
import shared.responses.Response;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadFollowingStateResponse extends Response {
    private final ArrayList<ItemNotification2> followOrUnfollow;

    public LoadFollowingStateResponse(ArrayList<ItemNotification2> followOrUnfollow) {
        this.followOrUnfollow = followOrUnfollow;
    }

    public HashMap<UserCopy , Boolean> convertToMap (){
        HashMap<UserCopy , Boolean> map = new HashMap<>();
        for (ItemNotification2 itemNotification2 :followOrUnfollow) {
            map.put(itemNotification2.getKey() , itemNotification2.getValue());
        }
        return map;
    }

    public ArrayList<ItemNotification2> getFollowOrUnfollow() {
        return followOrUnfollow;
    }
}

