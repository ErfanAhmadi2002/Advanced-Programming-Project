package shared.responses.messagingResponses.group;

import shared.responses.Response;

public class AddMemberToGroupResponse extends Response {
    private final int state;
    //state1 : successful;
    //state2 : failed;
    //state3 : groupExist;

    public AddMemberToGroupResponse(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
