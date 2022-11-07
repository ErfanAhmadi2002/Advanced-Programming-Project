package shared.events.messagingEvents.group;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadMessagesEvent extends Event {
    private final int groupId;

    public LoadMessagesEvent(int authToken, int groupId) {
        super(authToken);
        this.groupId = groupId;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadMessage(this);
    }

    public int getGroupId() {
        return groupId;
    }
}
