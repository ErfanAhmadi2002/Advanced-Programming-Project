package shared.events.personalEvents.notification;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadFollowingStateEvent extends Event {

    public LoadFollowingStateEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadFollowingState(this);
    }

}
