package shared.events.generalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.models.UserCopy;
import shared.responses.Response;

public class UpdateProfileDetailsEvent extends Event {
    private final UserCopy selected;

    public UpdateProfileDetailsEvent(int authToken, UserCopy selected) {
        super(authToken);
        this.selected = selected;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.updateProfile(this);
    }

    public UserCopy getSelected() {
        return selected;
    }
}
