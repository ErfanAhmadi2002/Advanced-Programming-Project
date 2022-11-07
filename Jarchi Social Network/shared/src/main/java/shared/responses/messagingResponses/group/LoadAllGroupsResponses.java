package shared.responses.messagingResponses.group;

import shared.models.Group;
import shared.responses.Response;

import java.util.ArrayList;

public class LoadAllGroupsResponses extends Response {
    private final ArrayList<Group> allGroups;

    public LoadAllGroupsResponses(ArrayList<Group> allGroups) {
        this.allGroups = allGroups;
    }

    public ArrayList<Group> getAllGroups() {
        return allGroups;
    }
}
