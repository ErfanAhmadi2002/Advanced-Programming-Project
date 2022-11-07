package shared.responses.personalResponses;

import shared.models.UserCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class LoadBlacklistResponse extends Response {
    private final ArrayList<UserCopy> blackList;

    public LoadBlacklistResponse(ArrayList<UserCopy> blackList) {
        this.blackList = blackList;
    }

    public ArrayList<UserCopy> getBlackList() {
        return blackList;
    }
}
