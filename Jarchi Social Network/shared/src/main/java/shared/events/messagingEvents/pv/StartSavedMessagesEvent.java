package shared.events.messagingEvents.pv;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class StartSavedMessagesEvent extends Event {

    public StartSavedMessagesEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.startSavedMessages(this);
    }

}
