package shared.events.generalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.models.UserCopy;
import shared.responses.Response;

public class FollowUserEvent extends Event {
    private final UserCopy userCopy;

    public FollowUserEvent(int authToken, UserCopy userCopy) {
        super(authToken);
        this.userCopy = userCopy;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.followUser(this);
    }

    public UserCopy getUserCopy() {
        return userCopy;
    }

}
