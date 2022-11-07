package shared.events.personalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadFollowersEvent extends Event {

    public LoadFollowersEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadFollowers(this);
    }

}
