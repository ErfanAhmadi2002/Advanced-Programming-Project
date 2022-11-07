package shared.events.settingEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class LastSeenEvent extends Event {
    private final int lastSeenState;

    public LastSeenEvent(int authToken, int lastSeenState) {
        super(authToken);
        this.lastSeenState = lastSeenState;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.lastSeen(this);
    }

    public int getLastSeenState() {
        return lastSeenState;
    }
}
