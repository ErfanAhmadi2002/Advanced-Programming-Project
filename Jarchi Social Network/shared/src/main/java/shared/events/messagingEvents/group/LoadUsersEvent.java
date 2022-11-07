package shared.events.messagingEvents.group;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadUsersEvent extends Event {
    private final int groupId;

    public LoadUsersEvent(int authToken, int groupId) {
        super(authToken);
        this.groupId = groupId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadUsers(this);
    }

    public int getGroupId() {
        return groupId;
    }
}
