package shared.events.messagingEvents.general;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class SearchForMessageEvent extends Event {
    private final String userName;

    public SearchForMessageEvent(int authToken, String userName) {
        super(authToken);
        this.userName = userName;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.searchForMessage(this);
    }

    public String getUserName() {
        return userName;
    }

}
