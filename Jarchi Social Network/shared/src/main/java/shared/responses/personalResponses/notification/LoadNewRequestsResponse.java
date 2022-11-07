package shared.responses.personalResponses.notification;

import shared.models.UserCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class LoadNewRequestsResponse extends Response {
    private final ArrayList<UserCopy> userCopies;

    public LoadNewRequestsResponse(ArrayList<UserCopy> userCopies) {
        this.userCopies = userCopies;
    }

    public ArrayList<UserCopy> getUserCopies() {
        return userCopies;
    }
}
