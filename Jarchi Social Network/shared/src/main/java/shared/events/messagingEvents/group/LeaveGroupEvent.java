package shared.events.messagingEvents.group;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LeaveGroupEvent extends Event {
    private final int groupId;

    public LeaveGroupEvent(int authToken, int groupId) {
        super(authToken);
        this.groupId = groupId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.leaveGroup(this);
    }

    public int getGroupId() {
        return groupId;
    }

}
