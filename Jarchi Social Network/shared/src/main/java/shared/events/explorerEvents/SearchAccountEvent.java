package shared.events.explorerEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class SearchAccountEvent extends Event {
    private final String username;

    public SearchAccountEvent(int authToken, String username) {
        super(authToken);
        this.username = username;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.searchAccount(this);
    }

    public String getUsername() {
        return username;
    }
}
