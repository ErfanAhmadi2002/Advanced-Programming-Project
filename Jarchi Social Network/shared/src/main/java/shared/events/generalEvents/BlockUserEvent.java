package shared.events.generalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.models.UserCopy;
import shared.responses.Response;

public class BlockUserEvent extends Event {
    private final UserCopy userCopy;

    public BlockUserEvent(int authToken, UserCopy userCopy) {
        super(authToken);
        this.userCopy = userCopy;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.blockUser(this);
    }

    public UserCopy getUserCopy() {
        return userCopy;
    }
}
