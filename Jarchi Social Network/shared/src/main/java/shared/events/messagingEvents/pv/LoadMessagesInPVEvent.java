package shared.events.messagingEvents.pv;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.models.UserCopy;
import shared.responses.Response;

public class LoadMessagesInPVEvent extends Event {
    private final UserCopy contact;

    public LoadMessagesInPVEvent(int authToken, UserCopy contact) {
        super(authToken);
        this.contact = contact;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.loadMessagesInPV(this);
    }

    public UserCopy getContact() {
        return contact;
    }
}
