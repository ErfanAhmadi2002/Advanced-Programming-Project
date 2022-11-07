package shared.responses.messagingResponses.group;

import shared.models.UserCopy;
import shared.responses.Response;

import java.util.ArrayList;

public class LoadUsersResponse extends Response {
    private final ArrayList<UserCopy> allUsers;

    public LoadUsersResponse(ArrayList<UserCopy> allUsers) {
        this.allUsers = allUsers;
    }

    public ArrayList<UserCopy> getAllUsers() {
        return allUsers;
    }
}
