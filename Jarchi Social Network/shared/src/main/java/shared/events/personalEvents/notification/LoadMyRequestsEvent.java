package shared.events.personalEvents.notification;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadMyRequestsEvent extends Event {

    public LoadMyRequestsEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadMyRequests(this);
    }

}
