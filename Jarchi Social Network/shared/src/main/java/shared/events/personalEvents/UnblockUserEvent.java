package shared.events.personalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.models.UserCopy;
import shared.responses.Response;

public class UnblockUserEvent extends Event {
    private final UserCopy user;

    public UnblockUserEvent(int authToken, UserCopy user) {
        super(authToken);
        this.user = user;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.unblock(this);
    }

    public UserCopy getUser() {
        return user;
    }
}
