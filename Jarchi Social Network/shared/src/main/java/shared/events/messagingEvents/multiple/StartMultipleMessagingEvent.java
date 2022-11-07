package shared.events.messagingEvents.multiple;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class StartMultipleMessagingEvent extends Event {

    public StartMultipleMessagingEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.startMultipleMessaging(this);
    }

}
