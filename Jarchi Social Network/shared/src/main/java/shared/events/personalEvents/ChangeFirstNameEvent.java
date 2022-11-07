package shared.events.personalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.formEvents.NewNameFormEvent;
import shared.responses.Response;

public class ChangeFirstNameEvent extends Event {
    private final NewNameFormEvent newNameFormEvent;

    public ChangeFirstNameEvent(int authToken, NewNameFormEvent newNameFormEvent) {
        super(authToken);
        this.newNameFormEvent = newNameFormEvent;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.changeFirstName(this);
    }

    public NewNameFormEvent getNewNameFormEvent() {
        return newNameFormEvent;
    }

}
