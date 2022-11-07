package shared.responses.messagingResponses.group;

import shared.models.Group;
import shared.responses.Response;

public class CreateNewGroupResponse extends Response {
    private final Group group;

    public CreateNewGroupResponse(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
}
