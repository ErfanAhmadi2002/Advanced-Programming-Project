package shared.events.personalEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.formEvents.NewNameFormEvent;
import shared.responses.Response;

public class ChangeLastNameEvent extends Event {
    private final NewNameFormEvent newNameFormEvent;

    public ChangeLastNameEvent(int authToken, NewNameFormEvent newNameFormEvent) {
        super(authToken);
        this.newNameFormEvent = newNameFormEvent;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.changeLastName(this);
    }

    public NewNameFormEvent getNewNameFormEvent() {
        return newNameFormEvent;
    }

}
