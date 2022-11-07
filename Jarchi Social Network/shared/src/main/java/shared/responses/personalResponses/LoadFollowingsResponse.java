package shared.responses.personalResponses;

import shared.models.UserCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class LoadFollowingsResponse extends Response {
    private final ArrayList<UserCopy> followings;

    public LoadFollowingsResponse(ArrayList<UserCopy> followings) {
        this.followings = followings;
    }

    public ArrayList<UserCopy> getFollowings() {
        return followings;
    }
}
