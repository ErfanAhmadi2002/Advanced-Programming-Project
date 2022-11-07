package shared.events.messagingEvents.general;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LoadUsernamesAndUnreadMessagesEvent extends Event {

    public LoadUsernamesAndUnreadMessagesEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadUsernamesAndUnreadMessages(this);
    }

}
