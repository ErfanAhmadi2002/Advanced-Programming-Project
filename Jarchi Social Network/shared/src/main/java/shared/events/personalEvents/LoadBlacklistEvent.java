package shared.events.personalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadBlacklistEvent extends Event {

    public LoadBlacklistEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadBlacklist(this);
    }

}
