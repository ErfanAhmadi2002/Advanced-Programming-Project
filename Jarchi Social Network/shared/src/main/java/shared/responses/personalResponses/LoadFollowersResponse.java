package shared.responses.personalResponses;

import shared.models.UserCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class LoadFollowersResponse extends Response {
    private final ArrayList<UserCopy> followers;

    public LoadFollowersResponse(ArrayList<UserCopy> followers) {
        this.followers = followers;
    }

    public ArrayList<UserCopy> getFollowers() {
        return followers;
    }
}
