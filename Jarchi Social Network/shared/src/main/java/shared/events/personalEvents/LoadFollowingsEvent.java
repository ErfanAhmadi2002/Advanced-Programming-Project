package shared.events.personalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadFollowingsEvent extends Event{

    public LoadFollowingsEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadFollowings(this);
    }

}
