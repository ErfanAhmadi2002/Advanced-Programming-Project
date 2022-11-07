package shared.events.messagingEvents.general;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadAllGroupsEvent extends Event {

    public LoadAllGroupsEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadAllGroups(this);
    }

}
