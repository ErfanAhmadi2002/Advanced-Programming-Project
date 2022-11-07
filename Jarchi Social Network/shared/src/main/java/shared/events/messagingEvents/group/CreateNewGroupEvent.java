package shared.events.messagingEvents.group;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class CreateNewGroupEvent extends Event {
    private final String groupName;

    public CreateNewGroupEvent(int authToken, String groupName) {
        super(authToken);
        this.groupName = groupName;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.createNewGroup(this);
    }

    public String getGroupName() {
        return groupName;
    }
}
